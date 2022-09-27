package ke.co.safaricom.blog.api;


import ke.co.safaricom.blog.dto.UserCreateRequest;
import ke.co.safaricom.blog.services.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

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
}
