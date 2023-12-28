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
        <div v-for="item in salon" :key="item.id" class="result">
          <div class="result-div"><p>{{ item.id }}</p></div>
          <div class="result-div"><p>{{ item.joueur}}</p></div>
          <div class="result-div"><Button btnClass="red" label="Rejoindre" @click="join(item.id)"></Button></div>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
import Button from '../components/CldButton.vue'
import router from "@/router";
import {onBeforeMount, ref} from "vue";
import {GameRequest} from "@/request/GameRequest";
import {jwtDecode} from "jwt-decode";
import {Salon} from "@/interface/Salon";

const salon = ref<Array<Salon>>([]);
const gameRequest = new GameRequest();
const token : any =localStorage.getItem("token")
const tokenDecode :any = jwtDecode(token);


onBeforeMount(async () => {
  const token = localStorage.getItem("token")
  if (token) {
    tokenDecode.value = jwtDecode(token)
  }
  const response: any = await gameRequest.getSalons()
  const data = response.data
  console.log(data)
  data.forEach((item : any)=>{
    console.log(item)
    const json: Salon = {
      "id": item.id,
      "joueur": item.listeJoueur[0].username,
    }
    salon.value.push(json);
  })
})

function join(id : any){
  const response : any = gameRequest.joinSalon(id,tokenDecode.sub);
  router.push({ name: 'partie', params: { id: id } })
}


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
  text-align: center;
}
.result-div.header{
  color: black;

}

</style>
