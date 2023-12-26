package com.projetcloud.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class SalonDTO {
    private String id;
    private ArrayList<String> listeJoueur;
}
