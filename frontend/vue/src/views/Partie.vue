<template>
  <div class="background-menu">
    <div class="d-flex flex-column align-center">
      <h1 class="text-white ">Puissance 4</h1>
      <div v-if="isFull">
      <div class="d-flex justify-space-between">
        <div class="d-flex justify-center align-center flex-column">
          <p>{{listeJoueur[0]}}</p>
          <p>Couleur: Rouge</p>
        </div>
      <div class="container-board mt-16">
        <div class="board">
          <div class="cell" v-for="(row, rowIndex) in board" :key="rowIndex">
            <div class="slot" v-for="(col, colIndex) in row" :key="colIndex" @click="dropPiece(colIndex)">
              <div class="piece" :class="getPieceClass(col, rowIndex)"></div>
            </div>
          </div>
        </div>
      </div>
        <div class="d-flex justify-center align-center flex-column">
          <p>{{listeJoueur[1]}}</p>
          <p>Couleur: Jaune</p>
        </div>
      </div>
        <div v-if="partieTerminee">
          <p>WINNER ISSSSSS {{winner}}</p>
          <button>Revenir à l'accueil</button>
        </div>
      </div>
      <div v-if="!isFull">
        <p>En attente</p>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>

import {onMounted, onUnmounted, ref} from "vue";
import {GameRequest} from "@/request/GameRequest";
import {useRouter} from "vue-router";
import {jwtDecode} from "jwt-decode";

const isFull = ref(false);
const partieTerminee = ref(false)
const listeJoueur = ref([])
const winner = ref("")

const gameRequest = new GameRequest();
var route :any  =  useRouter();
const token : any =localStorage.getItem("token")
const tokenDecode :any = jwtDecode(token);

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
    const response: any = await gameRequest.getSalon(route.currentRoute.value.params.id);
    console.log(response)
    if (response) {
      isFull.value = true;
    }
  } else {
    const response : any = await gameRequest.getPartie(route.currentRoute.value.params.id)
    console.log(response)

      listeJoueur.value = response.joueurs;
      for (var i = 0; i < response.matrice.length; i++) {
        for (var j = 0; j < response.matrice[i].length; j++) {
          if (response.matrice[i][j] !== null) {
            board.value[i][j] = response.matrice[i][j];
          }
        }
      }

      winner.value = response.winner;
      partieTerminee.value = response.partieTerminee

    console.log(board.value)

  }
} ,1000)

const getPieceClass = (value :any, index :any) => {
  return value === "ROUGE" ? `red-piece drop-${index}` : value === "JAUNE" ? `yellow-piece drop-${index}` : "";
}


onUnmounted(()=>{
  clearInterval(getJeu);
})


async function dropPiece(colIndex :any) {
  if (!partieTerminee.value) {


    await gameRequest.jouerCoup(route.currentRoute.value.params.id, colIndex, tokenDecode.sub);
  }

}

</script>

<style scoped>
.background-menu{
  height: 100vh;
  width: 100vw;
  background-color: royalblue;
}
.container-board{
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
}</style>
