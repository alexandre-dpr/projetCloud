<template>
  <div class="background-menu">
    <div class="d-flex flex-column align-center">
      <h1 class="text-white ">Puissance 4</h1>
      <div class="result-container mt-16">
        <div class="result header ">
          <div class="result-div header"><p>ID Partie</p></div>
          <div class="result-div header"><p>Joueur</p></div>
          <div class="result-div header"><p>Action</p></div>
        </div>
        <div class="result">
          <div class="result-div"><p>1</p></div>
          <div class="result-div"><p>Damien</p></div>
          <div class="result-div"><Button btnClass="red" label="Rejoindre"></Button></div>
        </div>
        <div class="result">
          <div class="result-div"><p>2</p></div>
          <div class="result-div"><p>Vincent</p></div>
          <div class="result-div"><Button btnClass="red" label="Rejoindre"></Button></div>
        </div>

      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
import Button from '../components/Button.vue'
import router from "@/router";




</script>

<style scoped>
.background-menu{
  height: 100vh;
  width: 100vw;
  background-color: royalblue;
}
.result-container{
  width: 70vw;
  background-color: white;
  padding: 10px;
  display: grid;
  grid-gap: 15px;
  border-radius: 8px;
}
.result{
  height: 10vh;
  background-color: royalblue;
  width: 100%;
  border-radius: 8px;
  display: flex;
  justify-content: space-between;
}
.result.header{
  background-color: white;
  height: 5vh;

}
.result-div{
  width: 30%;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  padding: 5px;
}
.result-div.header{
  color: black;

}

</style>
