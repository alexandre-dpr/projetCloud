<template>
  <div v-if="notification !== undefined" class=" d-flex justify-center w-100 bg-red h-auto pa-3">
    <p>{{ notification }}</p>
  </div>
  <div class="background-menu">
    <div>
      <button class="bg-white pa-3 rounded-lg ml-2 mt-2" v-on:click="router.push('menu')">Retour au menu</button>
    </div>

    <div class="d-flex flex-column align-center">
      <h1 class="text-white ">Puissance 4</h1>
      <div class="result-container mt-16">
        <div class="result header ">
          <div class="result-div header">
            <p>ID Partie</p>
          </div>
          <div class="result-div header">
            <p>Joueur</p>
          </div>
          <div class="result-div header">
            <p>Action</p>
          </div>
        </div>
        <div v-if="salon.length !== 0">
          <div v-for="item in salon" :key="item.id" class="result">
            <div class="result-div">
              <p>{{ item.id }}</p>
            </div>
            <div class="result-div">
              <p>{{ item.joueur }}</p>
            </div>
            <div class="result-div"><Button btnClass="red" label="Rejoindre" @click="join(item.id)"></Button></div>
          </div>
        </div>
        <div v-if="salon.length === 0">
          <div class="result">
            <div class="w-100 d-flex justify-center align-center text-white">
              <p>Il n'y a pas de salons</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
import Button from '../components/CldButton.vue'
import router from "@/router";
import { onBeforeMount, onMounted, ref } from "vue";
import { GameRequest } from "@/request/GameRequest";
import { jwtDecode } from "jwt-decode";
import { Salon } from "@/interface/Salon";
import { AxiosError } from "axios";

const salon = ref<Array<Salon>>([]);
const gameRequest = new GameRequest();
const token: any = localStorage.getItem("token")
const tokenDecode: any = jwtDecode(token);
const notification = ref<String>();

onMounted(() => {
  if (token === null) {
    router.push("login");
  }
  if (token !== null) {
    const tokenDecode: any = jwtDecode(token);
    const currentTimestamp = Math.floor(Date.now() / 1000);
    if (tokenDecode.exp < currentTimestamp) {
      router.push("login")
    }
  }
})

onBeforeMount(async () => {
  try {
    const token = localStorage.getItem("token")
    if (token) {
      tokenDecode.value = jwtDecode(token)
    }
    const response: any = await gameRequest.getSalons()
    const data = response.data
    if (data === "") {
      return;
    }
    console.log(response)
    data.forEach((item: any) => {
      console.log(item)
      const json: Salon = {
        "id": item.id,
        "joueur": item.listeJoueur[0].username,
      }
      salon.value.push(json);
    })
  } catch (e: any) {
    console.log(notification.value)
    notification.value = e.response.data.errorMessage
    console.log(notification.value)
    setTimeout(() => {
      notification.value = undefined;
      console.log("temps ecoule", notification.value)
    }, 2000);
  }
})


function join(id: any) {
  try {
    const response: any = gameRequest.joinSalon(id, tokenDecode.sub);
    router.push({ name: 'partie', params: { id: id } })
  } catch (e: any) {
    notification.value = e.response.data.errorMessage
    setTimeout(() => {
      notification.value = "";
    }, 2000);
  }
}


</script>

<style scoped>
.background-menu {
  height: 100vh;
  width: 100vw;
  background-color: royalblue;
}

.result-container {
  width: 70vw;
  background-color: white;
  padding: 10px;
  display: grid;
  grid-gap: 15px;
  border-radius: 8px;
}

.result {
  height: 10vh;
  background-color: royalblue;
  width: 100%;
  border-radius: 8px;
  display: flex;
  justify-content: space-between;
}

.result.header {
  background-color: white;
  height: 5vh;

}

.result-div {
  width: 30%;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  padding: 5px;
  text-align: center;
}

.result-div.header {
  color: black;

}
</style>
