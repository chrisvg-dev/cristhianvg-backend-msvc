package com.cvg.msvc_usuarios.msvcusuarios.service.rol;

import com.cvg.msvc_usuarios.msvcusuarios.dto.RolDto;
import com.cvg.msvc_usuarios.msvcusuarios.models.entity.Rol;
import com.cvg.msvc_usuarios.msvcusuarios.models.enums.RolName;
import com.cvg.msvc_usuarios.msvcusuarios.models.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository repository;

    public RolServiceImpl(RolRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Rol> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Rol save(RolDto rol) {
        return null;
    }

    @Override
    public Optional<Rol> findByName(RolName name) {
        return this.repository.findByName(name);
    }

    @Override
    public Set<Rol> findRolesFromList(Set<String> roles) {
        return null;
    }

    @Override
    public boolean existsByName(RolName name) {
        return this.repository.existsByName(name);
    }
}
