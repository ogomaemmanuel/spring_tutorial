package ke.co.safaricom.blog.services;

import ke.co.safaricom.blog.entities.Role;
import ke.co.safaricom.blog.repositories.RolesRepository;
import org.springframework.stereotype.Service;

@Service
public class RolesService {

    private final RolesRepository rolesRepository;

    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }


    public Role createRole(Role roleCreateRequest) {
       return this.rolesRepository.save(roleCreateRequest);
    }
}
