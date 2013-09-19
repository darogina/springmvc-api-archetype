#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "AUTHOR")
@Audited
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AuthorEntity extends BaseEntity {

    @Transient
    private static final long serialVersionUID = 3543619003350084825L;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Set<BookEntity> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name =  name;
    }

    public Set<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(Set<BookEntity> books) {
        this.books = books;
    }
}
