package com.api.aerolinea.Services;

import com.api.aerolinea.DTOs.UserDTO;
import com.api.aerolinea.Entities.Role;
import com.api.aerolinea.Entities.User;
import com.api.aerolinea.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<List<User>> getUsuarios() {
        List<User> users = userRepository.findAll();

        if (!users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(users);
        }

        return ResponseEntity.noContent().build();
    }


    public ResponseEntity<User> getUsuario(UUID id) {
        Optional<User> user = userRepository.findById(id);

        return user.map(value ->
                        ResponseEntity.status(HttpStatus.FOUND).body(value)).
                orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<String> postUsuario(UserDTO userDTO) {
        try {
            User user = User.builder()
                .uuid(UUID.randomUUID())
                .nombre(userDTO.nombre())
                .apellido(userDTO.apellido())
                .email(userDTO.email())
                .numero(userDTO.numero())
                .contrasenia(userDTO.contrasenia())
                .role(Role.USER)
                .build();

            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el usuario: " + e.getMessage());
        }
    }

    public ResponseEntity<String> patchUsuario(UserDTO userDTO, UUID uuid) {
        Optional<User> user = userRepository.findById(uuid);

        if (user.isPresent()){
            User userModificado = user.get();
            userModificado.setNombre(userDTO.nombre());
            userModificado.setApellido(userDTO.apellido());
            userModificado.setEmail(userDTO.email());
            userModificado.setContrasenia(userDTO.contrasenia());

            userRepository.save(userModificado);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuario modificado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    public void deleteUsuario(UUID id) {
        userRepository.deleteById(id);
    }
}
