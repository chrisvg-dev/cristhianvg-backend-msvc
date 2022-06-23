package com.cvg.msvc_usuarios.msvcusuarios.service.user;

import com.cvg.msvc_usuarios.msvcusuarios.dto.UserDto;
import com.cvg.msvc_usuarios.msvcusuarios.models.entity.User;
import com.cvg.msvc_usuarios.msvcusuarios.models.enums.UserStatusEnum;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    User save(User user);
    User update(UserDto user);
    void deleteById(Long id);
    Boolean deleteByEmail(String email);
    Boolean existsByEmail(String email);

    Boolean userActivation(String email);
}
