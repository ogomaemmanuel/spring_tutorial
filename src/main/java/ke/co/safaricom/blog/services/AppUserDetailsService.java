package ke.co.safaricom.blog.services;

import ke.co.safaricom.blog.entities.AppUser;
import ke.co.safaricom.blog.repositories.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AppUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public AppUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       AppUser user= this.appUserRepository.findByUsername(username);
       if(user==null) {
           throw new UsernameNotFoundException("User " + username + " does not exist");
       }
       return user;

    }
}
