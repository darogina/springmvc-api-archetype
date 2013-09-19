#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.entity.AuthorEntity;
import ${package}.repository.AuthorRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named("authorService")
public class AuthorServiceImpl extends AbstractCrudService<AuthorEntity, Long, AuthorRepository> implements AuthorService {

    @Override @Inject
    public void setRepository(AuthorRepository repository) {
        this.repository = repository;
    }
}
