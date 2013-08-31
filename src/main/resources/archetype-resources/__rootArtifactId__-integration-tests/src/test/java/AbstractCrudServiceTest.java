#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import ${package}.model.api.ApiModel;
import ${package}.util.PojoMapper;
import ${package}.util.SortDirection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.Serializable;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public abstract class AbstractCrudServiceTest<T extends ApiModel, ID extends Serializable> extends AbstractWebTests {

    public ResultActions create(String baseUrl, T resource) throws Exception {
        return mockMvc.perform(post(baseUrl)
                .content(PojoMapper.toJson(resource, false))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"));
    }

    public ResultActions update(String baseUrl, ID id, T resource) throws Exception {
        return mockMvc.perform(put(baseUrl, id)
                .content(PojoMapper.toJson(resource, false))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    public ResultActions findAll(String baseUrl) throws Exception {
        return mockMvc.perform(get(baseUrl)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"));
    }

    public ResultActions findPaginated(String baseUrl, Integer page, Integer size, SortDirection sortDirection, String properties) throws Exception {
        return mockMvc.perform(get(baseUrl)
                .param("page", page != null ? page.toString() : null)
                .param("size", size != null ? size.toString() : null)
                .param("direction", sortDirection != null ? sortDirection.name() : null)
                .param("properties", properties)
                .accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"));
    }

    public ResultActions findById(String baseUrl, ID id) throws Exception {
        return mockMvc.perform(get(baseUrl, id)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"));
    }

    public ResultActions delete(String baseUrl) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl));
    }

    public ResultActions delete(String baseUrl, ID id) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl, id));
    }
}