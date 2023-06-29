package br.net.supptech.authservice.controllers.v1;

import br.net.supptech.authservice.dtos.PermissionDto;
import br.net.supptech.authservice.models.PermissionModel;
import br.net.supptech.authservice.services.PermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/permissions")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @GetMapping
    public ResponseEntity<?> getAllPermissions(
            @PageableDefault(sort = "permissionId", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(permissionService.getAllPermissions(pageable));
    }

    @PostMapping
    public ResponseEntity<?> addNewPermission(@Valid @RequestBody PermissionDto permissionDto) {
        PermissionModel permissionModel = new PermissionModel();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(permissionService.savePermission(permissionDto.toModel(permissionModel)));
    }

    @GetMapping("/{permissionId}")
    public ResponseEntity<?> findPermissionById(@PathVariable(value = "permissionId") UUID permissionId) {
        Optional<PermissionModel> optionalPermissionModel = permissionService.findPermissionById(permissionId);
        return optionalPermissionModel
                .<ResponseEntity<Object>>map(permissionModel -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(permissionModel))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Permission not found!"));
    }

    @PutMapping("/{permissionId}")
    public ResponseEntity<?> updatePermission(@Valid @PathVariable(value = "permissionId") UUID permissionId,
                                              @RequestBody PermissionDto permissionDto) {
        Optional<PermissionModel> optionalPermissionModel = permissionService.findPermissionById(permissionId);
        if (optionalPermissionModel.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Permission not found!");
        var permissionModel = permissionDto.toModel(optionalPermissionModel.get());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(permissionService.updatePermission(permissionModel));

    }

    @DeleteMapping("/{permissionId}")
    public ResponseEntity<?> deletePermissionById(@PathVariable(value = "permissionId") UUID permissionId) {
        Optional<PermissionModel> optionalPermissionModel = permissionService.findPermissionById(permissionId);
        if (optionalPermissionModel.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Permission not found!");
        permissionService.deletePermission(optionalPermissionModel.get());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Permission deleted successfully!");
    }
}
