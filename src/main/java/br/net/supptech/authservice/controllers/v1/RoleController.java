package br.net.supptech.authservice.controllers.v1;

import br.net.supptech.authservice.dtos.PermissionDto;
import br.net.supptech.authservice.dtos.RoleDto;
import br.net.supptech.authservice.models.PermissionModel;
import br.net.supptech.authservice.models.RoleModel;
import br.net.supptech.authservice.services.PermissionService;
import br.net.supptech.authservice.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;

    @GetMapping
    public ResponseEntity<?> getAllRoles(
            @PageableDefault(sort = "roleId", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(roleService.getAllRoles(pageable));
    }

    @PostMapping
    public ResponseEntity<?> addNewRole(@Validated(RoleDto.View.CreateAndUpdate.class) @RequestBody RoleDto roleDto) {
        RoleModel roleModel = new RoleModel();
        Set<PermissionModel> permissionModelSet = getPermissionModels(roleDto);
        roleModel.getPermissions().addAll(permissionModelSet);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(roleService.saveRole(roleDto.toModel(roleModel)));
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<?> findRoleById(@PathVariable(value = "roleId") UUID roleId) {
        Optional<RoleModel> optionalRoleModel = roleService.findRoleById(roleId);
        return optionalRoleModel
                .<ResponseEntity<Object>>map(roleEntity -> ResponseEntity.status(HttpStatus.OK).body(roleEntity))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Role not found!"));
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<?> updateRole(@PathVariable(value = "roleId") UUID roleId,
                                        @Validated(RoleDto.View.CreateAndUpdate.class)
                                        @RequestBody RoleDto roleDto) {
        Optional<RoleModel> optionalRoleModel = roleService.findRoleById(roleId);
        if (optionalRoleModel.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Role not found!");
        var curruntRoleModel = roleDto.toModel(optionalRoleModel.get());
        curruntRoleModel.getPermissions().clear();
        curruntRoleModel.getPermissions().addAll(getPermissionModels(roleDto));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(roleService.updateRole(curruntRoleModel));
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<?> deleteRoleById(@PathVariable(value = "roleId") UUID roleId) {
        Optional<RoleModel> optionalRoleModel = roleService.findRoleById(roleId);
        if (optionalRoleModel.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Role not found!");
        roleService.deleteRole(optionalRoleModel.get());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Role deleted successfully!");
    }

    private Set<PermissionModel> getPermissionModels(RoleDto roleDto) {
        Set<PermissionModel> permissionModelSet = new HashSet<>();
        for (PermissionDto permissionDto : roleDto.getPermissions()) {
            Optional<PermissionModel> permissionModel = permissionService.findPermissionById(permissionDto.getPermissionId());
            if (permissionModel.isEmpty()) throw new RuntimeException("Permission not found!");
            permissionModelSet.add(permissionModel.get());
        }
        return permissionModelSet;
    }
}
