#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller.api.v1;

import ${package}.AbstractCrudServiceTest;
import ${package}.model.api.v1.Sample;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SampleAPIv1ControllerIT extends AbstractCrudServiceTest {

    private static final String URL = "/api/v1/sample";

    @Test
    public void createTest() throws Exception {
        Sample sample = new Sample();
        sample.setData("Test Data");

        ResultActions result = create(URL, sample);

        result.andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON_UTF_8))
                .andExpect(jsonPath("${symbol_dollar}.data").value(sample.getData()));
    }
}
