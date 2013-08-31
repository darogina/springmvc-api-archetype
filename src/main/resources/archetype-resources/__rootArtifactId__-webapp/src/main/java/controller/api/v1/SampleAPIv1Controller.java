#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller.api.v1;

import ${package}.controller.ServiceBasedRestController;
import ${package}.entity.BaseEntity;
import ${package}.entity.SampleEntity;
import ${package}.model.api.v1.Sample;
import ${package}.service.SampleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@Controller
@RequestMapping(value = "/api/v1/sample")
public class SampleAPIv1Controller extends ServiceBasedRestController<Sample, Long, SampleService> {

    @Override @Inject
    public void setService(SampleService service) {
        this.service = service;
    }

    @Override
    public BaseEntity toEntity(Sample model) {
        if (model == null) {
            return null;
        }

        return modelMapper.map(model, SampleEntity.class);
    }
}