package br.net.supptech.authservice.controllers.v1;

import br.net.supptech.authservice.dtos.UserDto;
import br.net.supptech.authservice.models.RoleModel;
import br.net.supptech.authservice.services.RoleService;
import br.net.supptech.authservice.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(
            @JsonView(UserDto.View.SignUp.class) @RequestBody UserDto userDto) {
        if (userService.existsUserByEmail(userDto.getEmail()))
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("There is already a user with e-mail " + userDto.getEmail() + "!");
        var userModel = userDto.toModel();
        Optional<RoleModel> roleModel = roleService.findRoleByName("USER");
        if (roleModel.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Role not found!");
        userModel.getRoles().add(roleModel.get());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.saveUser(userModel));
    }
}
