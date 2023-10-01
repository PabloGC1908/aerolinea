package com.api.aerolinea.Services;

import com.api.aerolinea.DTOs.UserDTO;
import com.api.aerolinea.Entities.Role;
import com.api.aerolinea.Entities.User;
import com.api.aerolinea.Exceptions.Classes.UserNotFoundException;
import com.api.aerolinea.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Servicio para gestionar operaciones relacionadas con usuarios.
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * Constructor de UserService.
     *
     * @param userRepository Repositorio de usuarios utilizado para acceder a los datos de los usuarios.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Obtiene todos los usuarios disponibles.
     *
     * @return ResponseEntity que contiene la lista de usuarios si existen, o una respuesta de contenido vacío si no existen.
     */
    public ResponseEntity<List<User>> getUsuarios() {
        List<User> users = userRepository.findAll();

        if (!users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(users);
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id ID del usuario a buscar.
     * @return ResponseEntity que contiene el usuario si se encuentra, o una respuesta de error si no se encuentra.
     */
    public ResponseEntity<User> getUsuario(UUID id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("Usuario no encontrado");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(user.get());
    }

    /**
     * Crea un nuevo usuario y lo guarda en el repositorio.
     *
     * @param userDTO Datos del usuario a crear.
     * @return ResponseEntity con un mensaje de éxito si se crea el usuario, o una respuesta de error si falla la creación.
     */
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
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Modifica un usuario existente por su ID.
     *
     * @param userDTO Datos del usuario a modificar.
     * @param uuid    ID del usuario a modificar.
     * @return ResponseEntity con un mensaje de éxito si se modifica el usuario, o una respuesta de error si no se encuentra.
     */
    public ResponseEntity<String> patchUsuario(UserDTO userDTO, UUID uuid) {
        Optional<User> user = userRepository.findById(uuid);

        if (user.isEmpty()) {
            throw new UserNotFoundException("Usuario no encontrado");
        }


        User userModificado = user.get();
        userModificado.setNombre(userDTO.nombre());
        userModificado.setApellido(userDTO.apellido());
        userModificado.setEmail(userDTO.email());
        userModificado.setContrasenia(userDTO.contrasenia());

        userRepository.save(userModificado);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuario modificado exitosamente");
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param id ID del usuario a eliminar.
     */
    public ResponseEntity<String> deleteUsuario(UUID id) {
        userRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado correctamente");
    }
}
