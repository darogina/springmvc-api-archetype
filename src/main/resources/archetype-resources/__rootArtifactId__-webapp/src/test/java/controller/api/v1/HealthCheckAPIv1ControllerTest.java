#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller.api.v1;

import ${package}.controller.AbstractWebTests;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class HealthCheckAPIv1ControllerTest extends AbstractWebTests {

    @InjectMocks
    private HealthCheckAPIv1Controller controller;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(controller)
                .alwaysExpect(content().contentType(APPLICATION_JSON_UTF_8))
                .build();
    }

    @Test
    public void simple() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/v1/healthCheck").accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF_8))
                .andExpect(jsonPath("${symbol_dollar}.status").value(true));
    }
}
