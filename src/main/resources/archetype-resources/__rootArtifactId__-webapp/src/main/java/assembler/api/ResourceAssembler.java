#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.assembler.api;

import org.springframework.hateoas.ResourceSupport;

public interface ResourceAssembler<T, D extends ResourceSupport> extends org.springframework.hateoas.ResourceAssembler<T, D> {
}
