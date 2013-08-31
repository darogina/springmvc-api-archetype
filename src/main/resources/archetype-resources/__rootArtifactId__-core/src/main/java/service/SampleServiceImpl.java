#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.entity.SampleEntity;
import ${package}.repository.SampleRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named("sampleService")
public class SampleServiceImpl extends AbstractCrudService<SampleEntity, Long, SampleRepository> implements SampleService {

    @Override @Inject
    public void setRepository(SampleRepository repository) {
        this.repository = repository;
    }
}
