package br.net.supptech.authservice.services;

import br.net.supptech.authservice.models.RoleModel;
import br.net.supptech.authservice.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Page<RoleModel> getAllRoles(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public RoleModel saveRole(RoleModel roleModel) {
        return roleRepository.save(roleModel);
    }

    @Override
    public Optional<RoleModel> findRoleById(UUID roleId) {
        return roleRepository.findById(roleId);
    }

    @Override
    public Optional<RoleModel> findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }

    @Transactional
    @Override
    public RoleModel updateRole(RoleModel roleModel) {
        return roleRepository.save(roleModel);
    }

    @Transactional
    @Override
    public void deleteRole(RoleModel roleModel) {
        roleRepository.delete(roleModel);
    }
}
