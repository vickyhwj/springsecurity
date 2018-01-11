import org.springframework.security.acls.domain.ObjectIdentityImpl;

import model.User;

public class Main2 {

    public static void main(final String[] args) {
        // TODO Auto-generated method stub
        final User u1=new User(),u2=new User();
        u1.setId(1L);u2.setId(1L);
        final ObjectIdentityImpl o1=new ObjectIdentityImpl(User.class, u1.getId());
        final ObjectIdentityImpl o2=new ObjectIdentityImpl(User.class, u2.getId());
        System.out.println(o1.equals(o2));
    }

}
