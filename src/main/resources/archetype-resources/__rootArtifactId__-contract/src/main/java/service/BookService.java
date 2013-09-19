#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.entity.BookEntity;

public interface BookService extends CrudService<BookEntity, Long> {
}
