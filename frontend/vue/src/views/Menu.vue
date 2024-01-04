<template>
  <div v-if="notification !== undefined" class=" d-flex justify-center w-100 bg-red h-auto pa-3">
    <p>{{ notification }}</p>
  </div>
  <div class="background-menu">
    <div class="d-flex flex-column align-center">
      <h1 class="text-white ">Puissance 4</h1>
      <div class="card-border mt-16">
        <div @click="creerPartie" class="card-inside">
          <p class="text-white">Créer une partie</p>
        </div>
      </div>
      <div class="card-border mt-16">
        <div @click="rejoindrePartie" class="card-inside">
          <p class="text-white">Rejoindre une partie</p>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>

import router from "@/router";
import { jwtDecode } from "jwt-decode";
import { GameRequest } from "@/request/GameRequest";
import { ref } from "vue";

const token: any = localStorage.getItem("token")
const tokenDecode: any = jwtDecode(token);
var gameRequest = new GameRequest();
const notification = ref<String>();



async function creerPartie() {
  try {
    const idSalon: any = await gameRequest.creerPartie(tokenDecode.sub);

    router.push({ name: 'partie', params: { id: idSalon } })
  } catch (e: any) {
    notification.value = e.response.data.errorMessage
    setTimeout(() => {
      notification.value = undefined;
    }, 2000);
  }
}

function rejoindrePartie() {
  router.push("salon")
}


</script>

<style scoped>
.background-menu {
  height: 100vh;
  width: 100vw;
  background-color: royalblue;
}

.card-border {
  background-color: white;
  width: 30vw;
  height: 100px;
  padding: 5px;
  border-radius: 8px;
  box-shadow: 6px 6px 12px black;
}

.card-inside {
  background-color: royalblue;
  height: 100%;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 8px;
  cursor: pointer;

}
</style>
