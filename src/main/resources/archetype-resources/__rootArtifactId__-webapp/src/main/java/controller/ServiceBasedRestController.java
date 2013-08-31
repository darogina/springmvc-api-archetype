#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import ${package}.entity.BaseEntity;
import ${package}.exception.NotFoundException;
import ${package}.exception.NotImplementedException;
import ${package}.model.api.ApiModel;
import ${package}.service.CrudService;
import org.jodah.typetools.TypeResolver;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class ServiceBasedRestController<T, ID extends Serializable, S extends CrudService> implements
        RestController<T, ID> {

    Class<T> resourceClass;
    Class<ID> resourceIdClass;
    Class<S> serviceClass;
    protected ModelMapper modelMapper;
    protected S service;

    public abstract void setService(S service);

    @Override
    public T create(@RequestBody T resource) {
        return mapEntityToResourceClass((BaseEntity) service.create(toEntity(resource)));
    }

    @Override
    public T update(@PathVariable ID id, @RequestBody T resource) {
        Assert.notNull(id, "id cannot be null");

        T retrievedResource = this.findById(id);
        if (retrievedResource == null) {
            throw new NotFoundException();
        }

        return mapEntityToResourceClass((BaseEntity) this.service.update(toEntity(resource)));
    }

    @Override
    public Iterable<T> findAll() {
        List<T> returnValues = new ArrayList<T>();

        for (Object element : service.findAll()) {
            returnValues.add(mapEntityToResourceClass((BaseEntity) element));
        }

        return returnValues;
    }

    @Override
    public Page<T> findPaginated(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                 @RequestParam(value = "direction", required = false, defaultValue = "") String direction,
                                 @RequestParam(value = "properties", required = false) String properties) {
        Page unmappedPage;

        Assert.isTrue(page > 0, "Page index must be greater than 0");
        Assert.isTrue(direction.isEmpty() || direction.equalsIgnoreCase(Sort.Direction.ASC.toString()) || direction.equalsIgnoreCase(Sort.Direction.DESC.toString()), "Direction should be ASC or DESC");
        if(direction.isEmpty()) {
            unmappedPage = this.service.findAll(new PageRequest(page - 1, size));
        } else {
            Assert.notNull(properties);
            unmappedPage = this.service.findAll(new PageRequest(page - 1, size, new Sort(Sort.Direction.fromString(direction.toUpperCase()), properties.split(","))));
        }

        PageRequest pageRequest;
        if(direction.isEmpty()) {
            pageRequest = new PageRequest(page - 1, size);
        } else {
            Assert.notNull(properties);
            pageRequest = new PageRequest(page - 1, size, new Sort(Sort.Direction.fromString(direction.toUpperCase()), properties.split(",")));
        }

        List<T> returnValues = new ArrayList<T>();
        for (Object element : unmappedPage.getContent()) {
            returnValues.add(mapEntityToResourceClass((BaseEntity) element));
        }

        return new PageImpl<T>(returnValues, pageRequest, unmappedPage.getTotalElements());
    }

    @Override
    public T findById(@PathVariable ID id) {
        BaseEntity entity = (BaseEntity)this.service.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        return mapEntityToResourceClass(entity);
    }

    @Override
    public void delete(@PathVariable ID id) {
        this.service.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete() {
        this.service.deleteAllWithCascade();
    }

    protected T mapEntityToResourceClass(BaseEntity entity) {
        return entity == null ? null : modelMapper.map(entity, resourceClass);
    }

    @PostConstruct
    private void init() {
        modelMapper = new ModelMapper();
        Class<?>[] typeArguments = TypeResolver.resolveRawArguments(ServiceBasedRestController.class, getClass());
        this.resourceClass = (Class<T>) typeArguments[0];
        this.resourceIdClass = (Class<ID>) typeArguments[1];
        this.serviceClass = (Class<S>) typeArguments[2];
    }

    public abstract BaseEntity toEntity(T model);
}
