package securitysecurityservice;

import java.util.Collection;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import model.User;

public class UserService {
    private MutableAclService aclService;
    TransactionTemplate transactionTemplate;
    SessionRegistry sessionRegistry;

    public void setAclService(final MutableAclService aclService) {
        this.aclService = aclService;
    }

    public void setTransactionTemplate(final TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public void setSessionRegistry(final SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    public SessionRegistry getSessionRegistry() {
        return sessionRegistry;
    }

    @Transactional
    public void addUser(final User user) {

        final ObjectIdentity oi = new ObjectIdentityImpl(user);

        transactionTemplate.execute(new TransactionCallback<Object>() {

            @Override
            public Object doInTransaction(final TransactionStatus arg0) {
                // TODO Auto-generated method stub
                final Sid sid = new GrantedAuthoritySid("ROLE_ADMIN");
                final MutableAcl acl = aclService.createAcl(oi);

                acl.insertAce(acl.getEntries().size(), BasePermission.ADMINISTRATION, sid, true);
                aclService.updateAcl(acl);
                return null;
            }
        });

    }

    @Transactional
    public void delUser(final User user) {

        final ObjectIdentity oi = new ObjectIdentityImpl(user);

        transactionTemplate.execute(new TransactionCallback<Object>() {

            @Override
            public Object doInTransaction(final TransactionStatus arg0) {
                // TODO Auto-generated method stub
                final ObjectIdentity oi = new ObjectIdentityImpl(user);
                aclService.deleteAcl(oi, true);
                final MutableAcl acl=(MutableAcl) aclService.readAclById(oi);
                final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                final Set<GrantedAuthority> set=(Set<GrantedAuthority>) authentication.getAuthorities();
                // acl.insertAce(0, BasePermission.ADMINISTRATION, new GrantedAuthoritySid("ROLE_ADMIN"), true);
                // aclService.updateAcl(acl);
                return null;
            }
        });

    }
    public void getUserDetail(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Collection<GrantedAuthority> set= (Collection<GrantedAuthority>) authentication.getAuthorities();
        for(final GrantedAuthority grantedAuthority:set){
            System.out.println(grantedAuthority.getAuthority());
        }
    }

}
