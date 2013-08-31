#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.entity;

import ${package}.entity.RevisionEntity;
import org.hibernate.envers.RevisionListener;

public class EntityRevisionListener implements RevisionListener {

    private static final String SYSTEM_USER = "system";

    @Override
    public void newRevision(Object revisionEntity) {
        RevisionEntity cicadaRevisionEntity = (RevisionEntity) revisionEntity;
        cicadaRevisionEntity.setUsername(getUserFromSecurityContext());
    }

    private String getUserFromSecurityContext() {

        //TODO: Implement security context lookup
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            Object user = authentication.getPrincipal();
//            if(user instanceof User) {
//                return ((User) user).getUsername();
//            } else if(user instanceof String) {
//                return (String) user;
//            }
//        }

        return SYSTEM_USER;
    }
}
