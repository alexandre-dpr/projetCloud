<template>
  <div v-if="notification !== undefined && notification.length === 0" class=" d-flex justify-center w-100 bg-red h-auto pa-3">
      <p>{{ notification }}</p>
  </div>
  <div class="background-menu">
    <div class="d-flex flex-column align-center">
      <h1 class="text-white ">Puissance 4</h1>
      <div v-if="isFull">
        <div class="d-flex justify-space-between align-center mt-16">
          <div class="card-border mr-3">
            <div class="card-inside">
              <p>{{ joueur1 ? joueur1 : "N/A" }}</p>
              <p>Couleur: Rouge</p>
            </div>
          </div>
          <div class="d-flex flex-column">
            <div class="card-border mb-3">
              <div class="card-inside">
                <p>Au tour de:</p>
                <p>{{ joueurTour }}</p>
              </div>
            </div>
            <div class="container-board">
              <div class="board">
                <div class="cell" v-for="(row, rowIndex) in board" :key="rowIndex">
                  <div class="slot" v-for="(col, colIndex) in row" :key="colIndex" @click="dropPiece(colIndex)">
                    <div class="piece" :class="getPieceClass(col, rowIndex)"></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="card-border ml-3">
            <div class="card-inside">
              <p>{{ joueur2 ? joueur2 : "N/A" }}</p>
              <p>Couleur: Jaune</p>
            </div>
          </div>
        </div>
        <div v-if="partieTerminee">
          <div class="d-flex flex-column mt-3">
            <div class="card-border">
              <div class="card-inside">
                <p v-if="winner !== null">Le gagnant est : {{ winner }}</p>
              </div>
            </div>
            <button @click="retourSalon" class="btn mt-3">Revenir à l'accueil</button>
          </div>
        </div>
      </div>
      <div v-if="!isFull">
        <div class="card-border">
          <div class="card-inside">
            <p>En attente</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>

import { onMounted, onUnmounted, ref } from "vue";
import { GameRequest } from "@/request/GameRequest";
import { useRouter } from "vue-router";
import { jwtDecode } from "jwt-decode";
import router from "@/router";
import { Joueur } from "@/interface/Joueur";
import {AxiosError} from "axios";


const isFull = ref(false);
const partieTerminee = ref(false)
const joueur1 = ref<Joueur>();
const joueur2 = ref<Joueur>();
const winner = ref<Joueur>()
const joueurTour = ref<Joueur>();
const notification = ref<String>("");

const gameRequest = new GameRequest();
var route: any = useRouter();
const token: any = localStorage.getItem("token")
const tokenDecode: any = jwtDecode(token);

var board = ref([
  [null, null, null, null, null, null, null],
  [null, null, null, null, null, null, null],
  [null, null, null, null, null, null, null],
  [null, null, null, null, null, null, null],
  [null, null, null, null, null, null, null],
  [null, null, null, null, null, null, null],
])

const getJeu = setInterval(async () => {
  if (!isFull.value) {
    try {
    let response: any = await gameRequest.getSalon(route.currentRoute.value.params.id);
      if (response) {
        isFull.value = true;
      }
    }catch (e : any) {
    notification.value = e.response.data.errorMessage
    setTimeout(() => {
      notification.value= "";
    }, 2000);
  }

  } else {
    try {
      const response: any = await gameRequest.getPartie(route.currentRoute.value.params.id)
      joueur1.value = response.joueurs[0].username;
      joueur2.value = response.joueurs[1].username;
      joueurTour.value = response.numTour % 2 === 0 ? joueur2.value : joueur1.value;

      for (var i = 0; i < response.matrice.length; i++) {
        for (var j = 0; j < response.matrice[i].length; j++) {
          if (response.matrice[i][j] !== null) {
            board.value[i][j] = response.matrice[i][j];
          }
        }
      }

      winner.value = response.winner !== null ? response.winner.username : null;
      partieTerminee.value = response.partieTerminee
    }catch (e : any) {
      notification.value = e.response.data.errorMessage
      setTimeout(() => {
        notification.value= "";
      }, 2000);
    }
  }
}, 1000)

const getPieceClass = (value: any, index: any) => {
  return value === "ROUGE" ? `red-piece drop-${index}` : value === "JAUNE" ? `yellow-piece drop-${index}` : "";
}


onUnmounted(() => {
  clearInterval(getJeu);
})


async function dropPiece(colIndex: any) {
  if (!partieTerminee.value) {

    try {
      await gameRequest.jouerCoup(route.currentRoute.value.params.id, colIndex, tokenDecode.sub);
    }catch (e : any) {
      notification.value = e.response.data.errorMessage
      setTimeout(() => {
        notification.value= "";
      }, 2000);
    }
    }

}




function retourSalon() {
  router.push({ name: 'menu' });
}

</script>

<style scoped>
.background-menu {
  height: 100vh;
  width: 100vw;
  background-color: royalblue;
}

.container-board {
  width: auto;
  height: auto;
  padding: 10px;
  background-color: white;
  border-radius: 8px;
  border: 2px solid black;
}

.board {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.btn {
  border: 5px solid white;
  box-shadow: 6px 6px 12px black;
  border-radius: 8px;
  padding: 5px;
  color: white;
}

.card-border {
  background-color: white;
  width: auto;
  height: auto;
  padding: 5px;
  border-radius: 8px;
  box-shadow: 6px 6px 12px black;
}

.card-inside {
  background-color: royalblue;
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border-radius: 8px;
  color: white;
  padding: 5px;

}

.cell {
  display: flex;
}


.slot {
  width: 50px;
  height: 50px;
  position: relative;
  background-color: blue;
}

.slot::before {
  content: "";
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 40px;
  height: 40px;
  background-color: white;
  border: 2px solid white;
  border-radius: 50%;
  z-index: 1;
}

.piece {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  margin: 5px;
  position: relative;
  transition: top 1s ease, transform 0.5s ease-in-out;
  z-index: 2;
}



.red-piece {
  background-color: red;
}

.yellow-piece {
  background-color: yellow;
}

.drop-0 {
  animation: bounce-0 0.5s ease-in-out;
}

.drop-1 {
  animation: bounce-1 0.5s ease-in-out;
}

.drop-2 {
  animation: bounce-2 0.5s ease-in-out;
}

.drop-3 {
  animation: bounce-3 0.5s ease-in-out;
}

.drop-4 {
  animation: bounce-4 0.5s ease-in-out;
}

.drop-5 {
  animation: bounce-5 0.5s ease-in-out;
}

@keyframes bounce-0 {
  0% {
    transform: translateY(-50px);
  }

  80% {
    transform: translateY(0px);
  }

  90% {
    transform: translateY(-10px);
  }

  100% {
    transform: translateY(0);
  }
}

@keyframes bounce-1 {
  0% {
    transform: translateY(-100px);
  }

  80% {
    transform: translateY(0px);
  }

  90% {
    transform: translateY(-10px);
  }

  100% {
    transform: translateY(0);
  }
}

@keyframes bounce-2 {
  0% {
    transform: translateY(-150px);
  }

  80% {
    transform: translateY(0px);
  }

  90% {
    transform: translateY(-10px);
  }

  100% {
    transform: translateY(0);
  }
}

@keyframes bounce-3 {
  0% {
    transform: translateY(-200px);
  }

  80% {
    transform: translateY(0px);
  }

  90% {
    transform: translateY(-10px);
  }

  100% {
    transform: translateY(0);
  }
}

@keyframes bounce-4 {
  0% {
    transform: translateY(-250px);
  }

  80% {
    transform: translateY(0px);
  }

  90% {
    transform: translateY(-10px);
  }

  100% {
    transform: translateY(0);
  }
}

@keyframes bounce-5 {
  0% {
    transform: translateY(-300px);
  }

  80% {
    transform: translateY(0px);
  }

  90% {
    transform: translateY(-10px);
  }

  100% {
    transform: translateY(0);
  }
}
</style>
