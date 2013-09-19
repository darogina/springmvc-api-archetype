#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.entity.AuthorEntity;

public interface AuthorService extends CrudService<AuthorEntity, Long> {
}
