package com.cvg.msvc_usuarios.msvcusuarios.service.user;

import com.cvg.msvc_usuarios.msvcusuarios.dto.UserDto;
import com.cvg.msvc_usuarios.msvcusuarios.models.entity.User;
import com.cvg.msvc_usuarios.msvcusuarios.models.enums.UserStatusEnum;
import com.cvg.msvc_usuarios.msvcusuarios.models.repository.UserRepository;
import com.cvg.msvc_usuarios.msvcusuarios.service.rol.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final RolService rolService;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder, RolService rolService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.rolService = rolService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) this.repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return this.repository.findByEmail(email);
    }

    @Override
    @Transactional
    public User save(User user) {
        return this.repository.save(user);
    }

    @Override
    @Transactional
    public User update(UserDto user) {
        Optional<User> optional = this.findByEmail( user.getEmail() );

        if ( optional.isPresent() ) {
            return null;
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<User> user = this.findById(id);
        if (user.isPresent()){
            this.repository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public Boolean deleteByEmail(String email) {
        Optional<User> user = this.findByEmail(email);
        if (user.isPresent()){
            this.repository.deleteById( user.orElseThrow().getId() );
            return true;
        }
        return false;
    }

    @Override
    public Boolean existsByEmail(String email) {
        return this.repository.existsByEmail(email);
    }

    @Override
    @Transactional
    public Boolean userActivation(String email) {
        Optional<User> userOptional = this.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.orElseThrow();
            user.setStatus( UserStatusEnum.ACTIVE );

            User updated = this.repository.save( user );
            if ( updated.getStatus().equals( UserStatusEnum.ACTIVE ) ) return true;
        }
        return false;
    }
}
