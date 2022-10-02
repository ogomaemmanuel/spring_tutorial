package ke.co.safaricom.blog.authentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var username = authentication.getName();
        var password = authentication.getCredentials().toString();
        if(username.equals("Anne") && password.equals("Test3")){
            return new UsernamePasswordAuthenticationToken(username,password, new ArrayList<>());
        }
          throw new BadCredentialsException("invalid login details");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
