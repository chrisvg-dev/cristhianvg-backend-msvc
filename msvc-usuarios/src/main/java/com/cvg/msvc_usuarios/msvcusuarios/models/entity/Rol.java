package com.cvg.msvc_usuarios.msvcusuarios.models.entity;

import com.cvg.msvc_usuarios.msvcusuarios.models.enums.RolName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "rol")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private RolName name;
    @NotBlank
    private String roleDescription;
}
