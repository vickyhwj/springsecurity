import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import model.User;
import securitysecurityservice.UserService;

public class Main {

    public static void main(final String[] args) {
        // TODO Auto-generated method stub
        final ClassPathXmlApplicationContext ac=new ClassPathXmlApplicationContext("spring-security.xml");
        final JdbcTemplate jdbcTemplate=(JdbcTemplate) ac.getBean("jdbcTemplate");
        final UserService userService=(UserService) ac.getBean("userService");
        final User user=new User();
        user.setId(99L);
        user.setName("xxx");
        final Authentication authentication=new UsernamePasswordAuthenticationToken("vicky", "pvicky");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        userService.addUser(user);

    }

}
