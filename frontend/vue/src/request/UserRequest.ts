import axios from 'axios';

export class UserRequest {

  async login(user:string, password:string){
    try {
      return await axios.post("auth/login",
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
      return await axios.post("auth/register",
        {
          "username" : name,
          "password" : password
        });

    } catch (error) {
      throw error;
    }
  }
}

