package securitysecurityservice;

import javax.transaction.Transactional;

import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import model.User;

public class UserService {
    private MutableAclService aclService;
    TransactionTemplate transactionTemplate;

    public void setAclService(final MutableAclService aclService) {
        this.aclService = aclService;
    }

    public void setTransactionTemplate(final TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
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
                 acl.insertAce(acl.getEntries().size(), BasePermission.ADMINISTRATION,sid ,false);
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
                // acl.insertAce(0, BasePermission.ADMINISTRATION, new GrantedAuthoritySid("ROLE_ADMIN"), true);
                // aclService.updateAcl(acl);
                return null;
            }
        });

    }

}
