package br.net.supptech.authservice.services;

import br.net.supptech.authservice.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Page<UserModel> getAllUsers(Specification<UserModel> specs, Pageable pageable);

    UserModel saveUser(UserModel userModel);

    Optional<UserModel> findUserById(UUID userId);

    void deleteUser(UserModel userModel);

    UserModel updateUser(UserModel userModel);

    boolean existsUserByEmail(String email);
}
