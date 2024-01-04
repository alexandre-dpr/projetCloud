<template>
  <div v-if="notification !== undefined" class=" d-flex justify-center w-100 bg-red h-auto pa-3">
    <p>{{ notification }}</p>
  </div>
  <div class="background-menu">
    <div class="d-flex flex-column align-center">
      <h1 class="text-white ">Puissance 4</h1>
      <div class="cardForm mt-16" v-if="isLogin">
        <form>
          <v-text-field v-model="pseudo" label="pseudo"></v-text-field>
          <v-text-field type="password" v-model="password" label="Mot de passe"></v-text-field>
          <CldButton btnClass="red" label="Connexion" :handleClick="connexion"></CldButton>
          <CldButton btnClass="mt-4 yellow" label="Se créer un compte" :handleClick="setIsLogin"></CldButton>
        </form>
      </div>
      <div class="cardForm mt-16" v-if="!isLogin">
        <form>
          <v-text-field v-model="pseudo" label="Pseudo">
          </v-text-field>
          <v-text-field type="password" v-model="password" label="Mot de passe"></v-text-field>
          <CldButton btnClass="yellow" label="Inscription" :handleClick="inscription"></CldButton>
          <CldButton btnClass="mt-4 red" label="Se connecter" :handleClick="setIsLogin"></CldButton>
        </form>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: "Login"
}
</script>

<script setup lang="ts">
import CldButton from "@/components/CldButton.vue";
import { onMounted, ref } from "vue";
import router from "@/router";
import { UserRequest } from "@/request/UserRequest";
import { AxiosError } from "axios";
import { jwtDecode } from "jwt-decode";


var isLogin = ref(true);
var password = ref('');
var pseudo = ref('');
var request = new UserRequest();
const notification = ref<String>();
const token: any = localStorage.getItem("token")

onMounted(() => {
  console.log(token);
  if (token !== null) {
    const tokenDecode: any = jwtDecode(token);
    const currentTimestamp = Math.floor(Date.now() / 1000);
    if (tokenDecode.exp > currentTimestamp) {
      router.push("menu")
    }
  }
})


function setIsLogin() {
  isLogin.value = !isLogin.value;
}

async function connexion() {
  try {
    const resp = await request.login(pseudo.value, password.value)
    if (resp.status === 200) {
      localStorage.setItem("token", resp.data);
      router.push("menu")
    }
  } catch (e: any) {
    notification.value = e.response.data.errorMessage
    setTimeout(() => {
      notification.value = undefined;
    }, 2000);
  }
}
async function inscription() {
  try {
    const resp = await request.register(pseudo.value, password.value)
    console.log(resp)
    if (resp.status === 201) {
      localStorage.setItem("token", resp.data);
      router.push('menu');
    }
  } catch (e: any) {
    notification.value = e.response.data.errorMessage
    setTimeout(() => {
      notification.value = undefined;
    }, 2000);
  }
}
</script>

<style scoped>
::v-deep .v-input__control {
  background-color: white !important;
}

.background-menu {
  height: 100vh;
  width: 100vw;
  background-color: royalblue;
}

.cardForm {
  min-height: 200px;
  width: 50%;
  border-radius: 20px;
  padding: 20px;
}
</style>
