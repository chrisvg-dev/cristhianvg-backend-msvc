package com.cvg.msvc_usuarios.msvcusuarios.service.rol;

import com.cvg.msvc_usuarios.msvcusuarios.dto.RolDto;
import com.cvg.msvc_usuarios.msvcusuarios.models.entity.Rol;
import com.cvg.msvc_usuarios.msvcusuarios.models.enums.RolName;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RolService {
    List<Rol> findAll();
    Rol save(RolDto rol);
    Optional<Rol> findByName(RolName name);
    Set<Rol> findRolesFromList(Set<String> roles);
    boolean existsByName(RolName name);
}
