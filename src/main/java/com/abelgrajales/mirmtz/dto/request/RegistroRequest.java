package com.abelgrajales.mirmtz.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistroRequest {

    @Email(message = "El correo no es válido")
    @NotBlank(message = "El correo no puede estar vacío")
    private String correo;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;

    @NotBlank(message = "Debe seleccionar un centro gestor")
    private Long centroGestorId;

}
