package ke.co.safaricom.blog.api;

import ke.co.safaricom.blog.entities.Role;
import ke.co.safaricom.blog.services.RolesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/roles")
public class RolesController {
    private final RolesService rolesService;

    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody @Valid Role roleCreateRequest) {
        Role role = this.rolesService.createRole(roleCreateRequest);
        return ResponseEntity.ok(role);
    }
}
