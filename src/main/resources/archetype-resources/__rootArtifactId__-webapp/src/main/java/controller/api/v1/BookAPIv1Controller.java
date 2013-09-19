#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller.api.v1;

import ${package}.assembler.api.v1.BookResourceAssembler;
import ${package}.controller.ServiceBasedRestController;
import ${package}.entity.BaseEntity;
import ${package}.entity.BookEntity;
import ${package}.model.api.v1.Book;
import ${package}.service.BookService;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@Controller
@ExposesResourceFor(Book.class)
@RequestMapping(value = "/api/v1/book")
public class BookAPIv1Controller extends ServiceBasedRestController<Book, Long, BookService, BookResourceAssembler> {

    @Override @Inject
    protected void setService(BookService service) {
        this.service = service;
    }

    @Override @Inject
    protected void setResourceAssembler(BookResourceAssembler resourceAssembler) {
        this.resourceAssembler = resourceAssembler;
    }

    @Override
    protected BaseEntity toEntity(Book model) {
        if (model == null) {
            return null;
        }

        return modelMapper.map(model, BookEntity.class);
    }

}
