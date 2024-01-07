import axios from 'axios';
import {base_api} from "@/utils/constant";

export class UserRequest {

  async login(user:string, password:string){
    try {
      return await axios.post(base_api + "auth/login",
        {
          "username" : user,
          "password" : password
        });
    } catch (error) {
      throw error;
    }
  }

  async register(name: string, password:string) {
    try {
      return await axios.post(base_api + "auth/register",
        {
          "username" : name,
          "password" : password
        });

    } catch (error) {
      throw error;
    }
  }
}

