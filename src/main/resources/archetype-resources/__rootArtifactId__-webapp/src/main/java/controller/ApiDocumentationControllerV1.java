#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import com.knappsack.swagger4springweb.controller.ApiDocumentationController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * This is an example of how you might extend the ApiDocumentationController in order to set your own RequestMapping
 * (instead of the default "/api") among other possibilities.  Going this route, you do not necessarily have to define
 * the controller in your servlet context.
 */
@Controller
@RequestMapping(value = "/documentation")
public class ApiDocumentationControllerV1 extends ApiDocumentationController {

    public ApiDocumentationControllerV1() {
        setBaseControllerPackage("${package}.controller.api.v1");

        setBaseModelPackage("${package}.model");

        setApiVersion("v1");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String documentation() {
        return "swagger/documentationIndex";
    }
}