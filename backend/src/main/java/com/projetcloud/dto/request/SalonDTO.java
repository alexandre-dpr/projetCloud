package com.projetcloud.dto.request;

import com.projetcloud.util.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class SalonDTO {
    @Schema(description = "Id", example = "1")
    private String id;
    @Schema(description = "User")
    private ArrayList<User> listeJoueur;
}
