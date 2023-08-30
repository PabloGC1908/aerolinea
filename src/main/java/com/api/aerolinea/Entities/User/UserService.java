package com.api.aerolinea.Entities.User;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsuarios() {
        return userRepository.findAll();
    }


    public User getUsuario(UUID id) {
        Optional<User> user = userRepository.findById(id);

        return user.orElse(null);
    }

    public void deleteUsuario(UUID id) {
        userRepository.deleteById(id);
    }
}
