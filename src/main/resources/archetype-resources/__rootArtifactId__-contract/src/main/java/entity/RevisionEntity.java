#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.entity;

import org.hibernate.envers.DefaultRevisionEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "REVISION_INFO")
@org.hibernate.envers.RevisionEntity(EntityRevisionListener.class)
public class RevisionEntity extends DefaultRevisionEntity {

    private static final long serialVersionUID = 4007251038461648489L;
    @Column(name = "username")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}