package ke.co.safaricom.blog.services;

import ke.co.safaricom.blog.dto.UserCreateRequest;
import ke.co.safaricom.blog.entities.AppUser;
import ke.co.safaricom.blog.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {


    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository,  PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser createUser(UserCreateRequest createRequest) {
        AppUser user = new AppUser();
        user.setUsername(createRequest.getUsername());
        user.setPassword(createRequest.getPassword());
        appUserRepository.save(user);
        return user;
    }
}
