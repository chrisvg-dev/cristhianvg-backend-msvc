package com.cvg.msvc_usuarios.msvcusuarios.models.repository;

import com.cvg.msvc_usuarios.msvcusuarios.models.entity.Rol;
import com.cvg.msvc_usuarios.msvcusuarios.models.enums.RolName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByName(RolName name);
    boolean existsByName(RolName name);
}
