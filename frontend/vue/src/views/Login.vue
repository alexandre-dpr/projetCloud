<template>
  <div class="background-menu">
    <div class="d-flex flex-column align-center">
      <h1 class="text-white ">Puissance 4</h1>
      <div class="cardForm mt-16" v-if="isLogin">
        <form>
          <v-text-field
            v-model="pseudo"
            label="pseudo"
          ></v-text-field>
          <v-text-field
            v-model="password"
            label="Mot de passe"
          ></v-text-field>
        <Button btnClass="red" label="Connexion" :handleClick="connexion"></Button>
        <Button btnClass="mt-4 yellow" label="Se créer un compte" :handleClick="setIsLogin"></Button>
        </form>
      </div>
      <div class="cardForm mt-16" v-if="!isLogin">
        <form>
          <v-text-field
          v-model="pseudo"
          label="Pseudo">
          </v-text-field>
          <v-text-field
            v-model="password"
            label="Mot de passe"
          ></v-text-field>
        <Button btnClass="yellow" label="Inscription" :handleClick="inscription"></Button>
        <Button btnClass="mt-4 red" label="Se connecter" :handleClick="setIsLogin"></Button>
        </form>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
import Button from '../components/Button'
import {ref} from "vue";
import router from "@/router";
import {UserRequest} from "@/request/UserRequest";


var isLogin = ref(true);
var password = ref('');
var pseudo = ref('');
var request = new UserRequest();


function setIsLogin(){
  isLogin.value = !isLogin.value;
}

async function connexion(){
  const resp = await request.login(pseudo.value,password.value)
  if (resp.status === 200){
    localStorage.setItem("token",resp.data);
    router.push("menu")
  }
}
async function inscription(){
  const resp = await request.register(pseudo.value,password.value)
  console.log(resp)
  if (resp.status === 201){
    localStorage.setItem("token",resp.data);
    router.push('menu');

  }
}
</script>

<style scoped>
::v-deep .v-input__control{
  background-color: white !important;
}

.background-menu{
  height: 100vh;
  width: 100vw;
  background-color: royalblue;
}
.cardForm{
  min-height: 200px;
  width: 50%;
  border-radius: 20px;
  padding: 20px;
}
</style>
