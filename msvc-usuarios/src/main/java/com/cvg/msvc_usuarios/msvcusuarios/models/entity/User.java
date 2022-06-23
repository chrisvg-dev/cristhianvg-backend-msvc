package com.cvg.msvc_usuarios.msvcusuarios.models.entity;

import com.cvg.msvc_usuarios.msvcusuarios.models.enums.UserStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "rol_user",
            joinColumns = @JoinColumn(name = "fk_user", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fk_rol", referencedColumnName = "id"),
            uniqueConstraints = { @UniqueConstraint(columnNames = {"fk_user", "fk_rol"}) }
    )
    private Set<Rol> rol;

    @Column(nullable = false)
    @NotBlank
    private String name;
    @Column(nullable = false)
    @NotBlank
    private String lastName;
    @Column(nullable = false, unique = true)
    @NotBlank
    @Email
    private String email;
    @Column(nullable = false)
    @Size(min = 10)
    @NotBlank
    private String password;
    private Boolean enabled;
    @Enumerated(EnumType.STRING)
    private UserStatusEnum status;
    private LocalDateTime created;
}