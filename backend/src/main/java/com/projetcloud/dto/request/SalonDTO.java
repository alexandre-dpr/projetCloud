package com.projetcloud.dto.request;

import com.projetcloud.util.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class SalonDTO {
    private String id;
    private ArrayList<User> listeJoueur;
}
