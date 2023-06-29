package br.net.supptech.authservice.services;

import br.net.supptech.authservice.models.RoleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface RoleService {

    Page<RoleModel> getAllRoles(Pageable pageable);

    RoleModel saveRole(RoleModel roleModel);

    Optional<RoleModel> findRoleById(UUID roleId);

    void deleteRole(RoleModel roleModel);

    RoleModel updateRole(RoleModel roleModel);

    Optional<RoleModel> findRoleByName(String name);
}
