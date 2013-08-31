#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.initializer;

import ${package}.entity.SampleEntity;
import ${package}.service.SampleService;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Named("sampleInitializer")
public class SampleInitializer {

    private static final String INITIAL_DATA = "Initial data";
    private static final String UPDATED_DATA = "Updated data";

    @Inject
    @Named("sampleService")
    private SampleService sampleService;

    @PostConstruct
    public void setup() {
        sampleService.deleteAll();

        SampleEntity sampleEntity = new SampleEntity();
        sampleEntity.setData(INITIAL_DATA);

        sampleEntity = sampleService.create(sampleEntity);
        Assert.notNull(sampleEntity);
        Assert.notNull(sampleEntity.getId());

        sampleEntity.setData(UPDATED_DATA);
        SampleEntity updatedEntity = sampleService.update(sampleEntity);

        Assert.notNull(updatedEntity);
        Assert.notNull(updatedEntity.getId());
        Assert.isTrue(updatedEntity.getId().equals(sampleEntity.getId()));
        Assert.isTrue(updatedEntity.getVersion() == sampleEntity.getVersion() + 1);
    }
}
