package br.net.supptech.authservice.controllers.v1;

import br.net.supptech.authservice.dtos.RoleDto;
import br.net.supptech.authservice.dtos.UserDto;
import br.net.supptech.authservice.models.RoleModel;
import br.net.supptech.authservice.models.UserModel;
import br.net.supptech.authservice.services.RoleService;
import br.net.supptech.authservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<?> addNewUser(@RequestBody UserDto userDto) {
        var userModel = userDto.toModel();
        userModel.getRoles().clear();
        getRoleModels(userDto, userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userModel));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findUserById(@PathVariable(value = "userId") UUID userId) {
        Optional<UserModel> optionalUserModel = userService.findUserById(userId);
        return optionalUserModel
                .<ResponseEntity<Object>>map(userEntity -> ResponseEntity.status(HttpStatus.OK).body(userEntity))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!"));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable(value = "userId") UUID userId,
                                        @RequestBody UserDto userDto) {
        Optional<UserModel> optionalUserModel = userService.findUserById(userId);
        if (optionalUserModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } else {
            UserModel currentUser = userDto.toUpdateModel(optionalUserModel.get());
            currentUser.getRoles().clear();
            getRoleModels(userDto, currentUser);
            return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(currentUser));
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable(value = "userId") UUID userId) {
        Optional<UserModel> optionalUserModel = userService.findUserById(userId);
        if (optionalUserModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        } else {
            userService.deleteUser(optionalUserModel.get());
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully!");
        }
    }

    private void getRoleModels(UserDto userDto, UserModel userModel) {
        Set<RoleModel> roleModelSet = new HashSet<>();
        for (RoleDto roleDto : userDto.getRoles()) {
            Optional<RoleModel> roleModel = roleService.findRoleById(roleDto.getRoleId());
            if (roleModel.isEmpty()) throw new RuntimeException("Role not found!");
            roleModelSet.add(roleModel.get());
        }
        userModel.getRoles().addAll(roleModelSet);
    }

}
