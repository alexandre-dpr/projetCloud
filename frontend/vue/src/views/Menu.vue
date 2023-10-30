<template>
  <div class="background-menu">
    <div class="d-flex flex-column align-center">
      <h1 class="text-white ">Puissance 4</h1>
      <div class="card-border mt-16">
        <div class="card-inside">
          <p @click="creerPartie" class="text-white">Créer une partie</p>
        </div>
      </div>
      <div class="card-border mt-16">
        <div class="card-inside">
          <p @click="rejoindrePartie" class="text-white">Rejoindre une partie</p>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>

import router from "@/router";

function creerPartie(){
  console.log("creerPartie")
  router.push("partie")
}

function rejoindrePartie(){
  console.log("rejoindrePartie")
  router.push("salon")

}


</script>

<style scoped>
.background-menu{
  height: 100vh;
  width: 100vw;
  background-color: royalblue;
}
.card-border{
  background-color: white;
  width: 30vw;
  height: 100px;
  padding: 1vw;
  border-radius: 8px;
  box-shadow: 6px 6px 12px black;
}
.card-inside{
  background-color: royalblue;
  height: 100%;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 8px;
  cursor:pointer;

}
</style>
