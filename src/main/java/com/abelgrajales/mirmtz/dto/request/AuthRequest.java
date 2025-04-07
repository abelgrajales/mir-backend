package com.abelgrajales.mirmtz.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthRequest {

    private String correo;
    private String password;

}
