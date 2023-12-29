package com.projetcloud.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    @Schema(description = "Username", example = "username")
    private String username;

    @Schema(description = "Password", example = "password")
    private String password;
}
