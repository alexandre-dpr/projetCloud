import axios from 'axios';
import {API_SERVER} from "@/utils/constant";

export class UserRequest {

  async login(user:string, password:string){
    try {
      return await axios.post(API_SERVER+"auth/login",
        {
          "username" : user,
          "password" : password
        });
    } catch (error) {
      throw error;
    }
  }
  async login(user:string, password:string){
    try {
      return await axios.post(API_SERVER+"auth/login",
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
      return await axios.post(API_SERVER+"auth/register",
        {
          "username" : name,
          "password" : password
        });

    } catch (error) {
      throw error;
    }
  }
}

