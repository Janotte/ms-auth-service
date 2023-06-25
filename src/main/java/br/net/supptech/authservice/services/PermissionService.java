package br.net.supptech.authservice.services;

import br.net.supptech.authservice.models.PermissionModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PermissionService {
    List<PermissionModel> getAllPermissions();

    PermissionModel savePermission(PermissionModel permissionModel);

    Optional<PermissionModel> findPermissionById(UUID permissionId);

    void deletePermission(PermissionModel permissionModel);

    PermissionModel updatePermission(PermissionModel permissionModel);
}
