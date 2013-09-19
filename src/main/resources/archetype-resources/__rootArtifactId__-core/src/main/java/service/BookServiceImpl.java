#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.entity.BookEntity;
import ${package}.repository.BookRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named("bookService")
public class BookServiceImpl extends AbstractCrudService<BookEntity, Long, BookRepository> implements BookService {

    @Override @Inject
    public void setRepository(BookRepository repository) {
        this.repository = repository;
    }
}
