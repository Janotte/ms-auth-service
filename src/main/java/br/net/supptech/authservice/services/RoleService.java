package br.net.supptech.authservice.services;

import br.net.supptech.authservice.models.RoleModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleService {

    List<RoleModel> getAllRoles();

    RoleModel saveRole(RoleModel roleModel);

    Optional<RoleModel> findRoleById(UUID roleId);

    void deleteRole(RoleModel roleModel);

    RoleModel updateRole(RoleModel roleModel);
}
