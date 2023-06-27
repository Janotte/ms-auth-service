package br.net.supptech.authservice.services;

import br.net.supptech.authservice.models.UserModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<UserModel> getAllUsers();

    UserModel saveUser(UserModel userModel);

    Optional<UserModel> findUserById(UUID userId);

    void deleteUser(UserModel userModel);

    UserModel updateUser(UserModel userModel);

    boolean existsUserByEmail(String email);
}
