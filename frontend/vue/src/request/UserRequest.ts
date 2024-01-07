import axios from 'axios';
import {getApiServer} from "@/utils/constant";

export class UserRequest {

  async login(user:string, password:string){
    try {
      return await axios.post(getApiServer()+"auth/login",
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
      return await axios.post(getApiServer()+"auth/register",
        {
          "username" : name,
          "password" : password
        });

    } catch (error) {
      throw error;
    }
  }
}

