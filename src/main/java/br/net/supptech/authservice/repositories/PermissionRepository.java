package br.net.supptech.authservice.repositories;

import br.net.supptech.authservice.models.PermissionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionModel, UUID> {
}
