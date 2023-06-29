package br.net.supptech.authservice.services;

import br.net.supptech.authservice.models.PermissionModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface PermissionService {
    Page<PermissionModel> getAllPermissions(Pageable pageable);

    PermissionModel savePermission(PermissionModel permissionModel);

    Optional<PermissionModel> findPermissionById(UUID permissionId);

    void deletePermission(PermissionModel permissionModel);

    PermissionModel updatePermission(PermissionModel permissionModel);
}
