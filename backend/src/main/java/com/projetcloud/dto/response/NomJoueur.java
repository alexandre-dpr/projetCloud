package com.projetcloud.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NomJoueur {
    @Schema(description = "Username", example = "username")
    private String username;


}
