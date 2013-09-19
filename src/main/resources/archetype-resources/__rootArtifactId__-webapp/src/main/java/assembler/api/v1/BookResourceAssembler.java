#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.assembler.api.v1;

import ${package}.assembler.api.GenericResourceAssemler;
import ${package}.controller.api.v1.AuthorAPIv1Controller;
import ${package}.controller.api.v1.BookAPIv1Controller;
import ${package}.entity.BookEntity;
import ${package}.model.api.v1.Author;
import ${package}.model.api.v1.Book;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityLinks;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Named("bookResourceAssembler")
public class BookResourceAssembler extends GenericResourceAssemler<BookAPIv1Controller, BookEntity, Book> {

    private ModelMapper modelMapper;

    public BookResourceAssembler() {
        super(BookAPIv1Controller.class, Book.class);
    }

    @Override
    public Book toResource(BookEntity entity) {
        // will add also a link with rel self pointing itself
        Book resource = createResourceWithId(entity.getId(), entity);

        // adding a link with rel author pointing to the book's author
        resource.add(linkTo(AuthorAPIv1Controller.class).slash(entity.getAuthor().getId()).withRel("author"));

        return resource;
    }

    @Override
    protected Book instantiateResource(BookEntity entity) {
        return entity == null ? null : modelMapper.map(entity, Book.class);
    }

    @PostConstruct
    private void init() {
        modelMapper = new ModelMapper();
    }
}
