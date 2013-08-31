#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-applicationContext.xml")
public abstract class AbstractWebTests {

    public static final String APPLICATION_JSON_UTF_8 = "application/json;charset=UTF-8";

    protected MockMvc mockMvc;

    @Before
    public void setupTest() {
        MockitoAnnotations.initMocks(this);
    }

}
