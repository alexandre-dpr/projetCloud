import axios from 'axios';
import {API_SERVER} from "@/ultils/constant";

export class UserRequest {

  async login(mail:string, password:string){
    try {
      const response = await axios.get(API_SERVER);
      return response.data;
    }catch (error){
      throw error
    }
  }
  async register(mail: string, password:string, name:string) {
    try {
      const response = await axios.post(API_SERVER);
      return response.data;
    } catch (error) {
      throw error;
    }
  }
}

