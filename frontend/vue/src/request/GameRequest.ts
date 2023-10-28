import axios from 'axios';
import {API_SERVER} from "@/utils/constant";

export class GameRequest{
  async jouerCoup(colonne: number){
    try {
      const response = await axios.post(API_SERVER);
      return response.data;
    }catch (error){
      throw error
    }
  }

  async getJoueurCourant(idPartie){
    try {
      const response = await axios.get(API_SERVER);
      return response.data;
    }catch (error){
      throw error
    }
  }

  async getPartieFinit(idPartie: number){
    try {
      const response = await axios.get(API_SERVER);
      return response.data;
    }catch (error){
      throw error
    }
  }

  async getPartie(idPartie: number){
    try {
      const response = await axios.get(API_SERVER);
      return response.data;
    }catch (error){
      throw error
    }
  }
}
