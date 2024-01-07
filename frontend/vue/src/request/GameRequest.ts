import axios from 'axios';
import {base_api} from "@/utils/constant";

export class GameRequest{
  async creerPartie(nomJoueur: String){
    try {
      const response = await axios.post(base_api +"salon",{
        "username" : nomJoueur,
      },
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        });
      return response.data;
    }catch (error){
      throw error
    }
  }

  async getPartie(idPartie: number){
    try {
      const response = await axios.get(base_api + "partie/"+idPartie,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        });
      return response.data;
    }catch (error){
      throw error
    }
  }

  async jouerCoup(idPartie: number, colonne :number, joueur : string){
    try {
      const response = await axios.post(base_api + "partie/"+idPartie+"/coup",{
          "colonne" : colonne,
          "joueur" : joueur,
        },
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        });
      return response.data;
    }catch (error){
      throw error
    }
  }
  async getSalon(idPartie: number){
    try {
      const response = await axios.get(base_api + "salon/"+idPartie,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        });
      return response.data;
    }catch (error){
      throw error
    }
  }
  async joinSalon(idPartie: number, nomJoueur: String){
    try {
      const response = await axios.post(base_api + "salon/"+idPartie,{
          "username" : nomJoueur,
        },
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        });
      return response.data;
    }catch (error){
      throw error
    }
  }
  async getSalons(){
    try {
      return await axios.get(base_api + "salon",
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        });

    }catch (error){
      throw error
    }
  }
}
