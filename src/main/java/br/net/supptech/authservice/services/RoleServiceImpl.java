package br.net.supptech.authservice.services;

import br.net.supptech.authservice.models.RoleModel;
import br.net.supptech.authservice.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<RoleModel> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public RoleModel saveRole(RoleModel roleModel) {
        return roleRepository.save(roleModel);
    }

    @Override
    public Optional<RoleModel> findRoleById(UUID roleId) {
        return roleRepository.findById(roleId);
    }

    @Override
    public void deleteRole(RoleModel roleModel) {
        roleRepository.delete(roleModel);
    }

    @Override
    public RoleModel updateRole(RoleModel roleModel) {
        return roleRepository.save(roleModel);
    }
}
