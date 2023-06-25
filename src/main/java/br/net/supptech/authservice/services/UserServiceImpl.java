package br.net.supptech.authservice.services;

import br.net.supptech.authservice.models.UserModel;
import br.net.supptech.authservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserModel saveUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    @Override
    public Optional<UserModel> findUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void deleteUser(UserModel userModel) {
        userRepository.delete(userModel);
    }

    @Override
    public UserModel updateUser(UserModel userModel) {
        return userRepository.save(userModel);
    }
}
