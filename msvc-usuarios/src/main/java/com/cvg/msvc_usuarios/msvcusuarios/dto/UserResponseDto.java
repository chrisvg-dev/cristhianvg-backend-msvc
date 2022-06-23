package com.cvg.msvc_usuarios.msvcusuarios.dto;

import com.cvg.msvc_usuarios.msvcusuarios.models.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private HttpStatus status;
    private User user;
}
