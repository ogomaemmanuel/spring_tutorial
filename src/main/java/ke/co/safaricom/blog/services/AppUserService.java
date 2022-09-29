package ke.co.safaricom.blog.services;

import ke.co.safaricom.blog.dto.UserCreateRequest;
import ke.co.safaricom.blog.dto.UserRoleAssigmentRequest;
import ke.co.safaricom.blog.entities.AppUser;
import ke.co.safaricom.blog.entities.Role;
import ke.co.safaricom.blog.repositories.AppUserRepository;
import ke.co.safaricom.blog.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {


    private final AppUserRepository appUserRepository;

    private final RolesRepository rolesRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, RolesRepository rolesRepository) {
        this.appUserRepository = appUserRepository;
        this.rolesRepository = rolesRepository;
    }

    public AppUser createUser(UserCreateRequest createRequest) {
        AppUser user = new AppUser();
        user.setUsername(createRequest.getUsername());
        user.setPassword(createRequest.getPassword());
        appUserRepository.save(user);
        return user;
    }

    public void updateUserRole(Long id,UserRoleAssigmentRequest eserRoleAssigmentRequest) {
        Role role=this.rolesRepository.getReferenceById(eserRoleAssigmentRequest.getRoleId());
       var user= this.appUserRepository.getReferenceById(eserRoleAssigmentRequest.getUserId());
       user.getRoles().add(role);
       this.appUserRepository.save(user);

    }

    public List<AppUser> getUsers() {
        return  this.appUserRepository.findAll();
    }
}
