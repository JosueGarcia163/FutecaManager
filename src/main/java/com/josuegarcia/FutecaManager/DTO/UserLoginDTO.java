package com.josuegarcia.FutecaManager.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class UserLoginDTO {

    @NotBlank(message = "El usuario no puede ir vacio")
    private String username;
    @NotBlank(message = "La contraseña no puede ir vacio")
    private String password;
}
