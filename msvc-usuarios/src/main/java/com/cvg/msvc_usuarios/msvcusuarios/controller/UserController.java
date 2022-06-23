package com.cvg.msvc_usuarios.msvcusuarios.controller;

import com.cvg.msvc_usuarios.msvcusuarios.dto.UserDto;
import com.cvg.msvc_usuarios.msvcusuarios.dto.UserResponseDto;
import com.cvg.msvc_usuarios.msvcusuarios.models.entity.Rol;
import com.cvg.msvc_usuarios.msvcusuarios.models.entity.User;
import com.cvg.msvc_usuarios.msvcusuarios.models.enums.RolName;
import com.cvg.msvc_usuarios.msvcusuarios.models.enums.UserStatusEnum;
import com.cvg.msvc_usuarios.msvcusuarios.service.rol.RolService;
import com.cvg.msvc_usuarios.msvcusuarios.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger( UserController.class );
    private final UserService service;
    private final RolService rolService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService service, PasswordEncoder passwordEncoder, RolService rolService) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
        this.rolService = rolService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok( service.findAll() );
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok( service.findById(id) );
    }

    @GetMapping("/find-email")
    public ResponseEntity<?> findByEmail(@RequestParam("email") String email){
        return ResponseEntity.ok( service.findByEmail(email) );
    }


    /**
     * -----------------------------------------------------------------
     * POST
     */
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody UserDto userDto, BindingResult result){
        LOGGER.info( userDto.getRol().toString() );
        if (result.hasErrors()) return validate(result);

        if (Boolean.TRUE.equals(this.service.existsByEmail(userDto.getEmail() ))) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "El email ya está registrado..."));
        }
        Set<Rol> roles = userDto.getRol().stream()
                .map( item -> {
                    RolName rolName = RolName.valueOf(item);
                    Optional<Rol> rol = this.rolService.findByName(rolName);
                    return rol.orElseThrow();
                }).collect(Collectors.toSet());

        User user = User.builder().rol( roles ).name( userDto.getName() )
                .lastName( userDto.getLastName() ).email( userDto.getEmail() )
                .password( passwordEncoder.encode( userDto.getPassword() ) )
                .enabled( Boolean.TRUE )
                .status( UserStatusEnum.NO_VALIDATED ).created( LocalDateTime.now() ).build();
        User saved = this.service.save(user);

        UserResponseDto responseDto = UserResponseDto.builder()
                .status( HttpStatus.CREATED )
                .user( saved )
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body( responseDto );
    }

    /**
     * -----------------------------------------------------------------
     * DELETE
     */
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        try {
            this.service.deleteById(userId);
            return ResponseEntity.status(HttpStatus.OK).body( Collections.singletonMap("message", "Registro eliminado...") );
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
    /**
     * VALIDATIONS
     * @param result
     * @return
     */
    private ResponseEntity<Map<String, String>> validate(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        } );
        return ResponseEntity.badRequest().body(errores);
    }
}
