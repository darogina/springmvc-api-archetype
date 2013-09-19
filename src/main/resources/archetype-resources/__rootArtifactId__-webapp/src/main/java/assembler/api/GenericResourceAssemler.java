#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.assembler.api;

import ${package}.entity.BaseEntity;
import ${package}.model.api.v1.BaseModel;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import javax.inject.Inject;
import javax.inject.Named;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Generic Resource Assembler for Hateoas.  This class should be extended if a {@link Link}
 * besides 'Self' must be created.
 */
//@Named("genericResourceAssembler")
public class GenericResourceAssemler<C, T extends BaseEntity, D extends BaseModel> extends ResourceAssemblerSupport<T, D> implements ResourceAssembler<T, D> {

    /**
     * Creates a new {@link ResourceAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public GenericResourceAssemler(Class<C> controllerClass, Class<D> resourceType) {
        super(controllerClass, resourceType);
    }

    /**
     * Creates a new resource with a self link to the given id.
     *
     * @param entity must not be {@literal null}.
     * @return
     */
    @Override
    public D toResource(T entity) {
        return createResourceWithId(entity.getId(), entity);
    }

}
