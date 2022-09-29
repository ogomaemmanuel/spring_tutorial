package ke.co.safaricom.blog.api;


import ke.co.safaricom.blog.dto.UserCreateRequest;
import ke.co.safaricom.blog.dto.UserRoleAssigmentRequest;
import ke.co.safaricom.blog.entities.AppUser;
import ke.co.safaricom.blog.services.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping
    public ResponseEntity<?>  createUser(@RequestBody @Valid UserCreateRequest  userCreateRequest){
      var user=  this.appUserService.createUser(userCreateRequest);
        return  ResponseEntity.ok(user);
    }

    @PutMapping("/roles/{userId}")
    public ResponseEntity<?> updateUserRole(@PathVariable Long userId,@RequestBody @Valid UserRoleAssigmentRequest eserRoleAssigmentRequest){
        this.appUserService.updateUserRole(userId,eserRoleAssigmentRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AppUser>> getUsers(){
        return ResponseEntity.ok( this.appUserService.getUsers());
    }
}
