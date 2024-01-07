import axios from 'axios';
import {getApiServer} from "@/utils/constant";

export class GameRequest{
  async creerPartie(nomJoueur: String){
    try {
      const response = await axios.post(getApiServer()+"salon",{
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
      const response = await axios.get(getApiServer()+"partie/"+idPartie,
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
      const response = await axios.post(getApiServer()+"partie/"+idPartie+"/coup",{
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
      const response = await axios.get(getApiServer()+"salon/"+idPartie,
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
      const response = await axios.post(getApiServer()+"salon/"+idPartie,{
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
      return await axios.get(getApiServer()+"salon",
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
