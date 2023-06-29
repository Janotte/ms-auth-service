package br.net.supptech.authservice.services;

import br.net.supptech.authservice.models.PermissionModel;
import br.net.supptech.authservice.repositories.PermissionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public Page<PermissionModel> getAllPermissions(Pageable pageable) {
        return permissionRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public PermissionModel savePermission(PermissionModel permissionModel) {
        return permissionRepository.save(permissionModel);
    }

    @Override
    public Optional<PermissionModel> findPermissionById(UUID permissionId) {
        return permissionRepository.findById(permissionId);
    }

    @Transactional
    @Override
    public PermissionModel updatePermission(PermissionModel permissionModel) {
        return permissionRepository.save(permissionModel);
    }

    @Transactional
    @Override
    public void deletePermission(PermissionModel permissionModel) {
        permissionRepository.delete(permissionModel);
    }
}
