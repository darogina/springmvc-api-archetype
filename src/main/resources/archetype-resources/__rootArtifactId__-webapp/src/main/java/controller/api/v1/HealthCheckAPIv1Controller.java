#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller.api.v1;

import ${package}.model.api.v1.HealthCheck;
import com.knappsack.swagger4springweb.annotation.ApiExclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/healthCheck")
@ApiExclude
public class HealthCheckAPIv1Controller {

    private static final Logger log = LoggerFactory.getLogger(HealthCheckAPIv1Controller.class);

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    HealthCheck healthCheck() {
        return new HealthCheck();
    }
}
