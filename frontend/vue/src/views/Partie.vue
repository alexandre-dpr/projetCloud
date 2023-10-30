<template>
  <div>
    <h1>Puissance 4</h1>
    <div class="board">
      <div
        class="cell"
        v-for="(row, rowIndex) in board"
        :key="rowIndex"
      >
        <div
          class="slot"
          v-for="(col, colIndex) in row"
          :key="colIndex"
          @click="dropPiece(colIndex)"
        >

          <div class="piece" :class="getPieceClass(col,rowIndex)"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>

import {onMounted, ref} from "vue";

var board = ref([
  [null,null,null,null,null,null,null],
  [null,null,null,null,null,null,null],
  [null,null,null,null,null,null,null],
  [null,null,null,null,null,null,null],
  [null,null,null,null,null,null,null],
  [null,null,null,null,null,null,null],
])
var currentPlayer = "red"


const getPieceClass = (value, index) => {
  return value === "red" ? `red-piece drop-${index}` : value === "yellow" ? `yellow-piece drop-${index}` : "";
}
onMounted(() => {

})
function dropPiece(colIndex) {

  for (let rowIndex = 5; rowIndex >= 0; rowIndex--) {
    if (!board.value[rowIndex][colIndex]) {
      board.value[rowIndex][colIndex] = currentPlayer;
      currentPlayer = currentPlayer === "red" ? "yellow" : "red";
      return;
    }
  }
}

</script>

<style scoped>
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
  0%{
    transform: translateY(-50px);
  }
  80% {
    transform: translateY(0px);
  }
  90% {
    transform: translateY(-10px);
  }
  100%{
    transform: translateY(0);
  }
}@keyframes bounce-1 {
   0%{
     transform: translateY(-100px);
   }
   80% {
     transform: translateY(0px);
   }
   90% {
     transform: translateY(-10px);
   }
   100%{
     transform: translateY(0);
   }
 }@keyframes bounce-2 {
    0%{
      transform: translateY(-150px);
    }
    80% {
      transform: translateY(0px);
    }
    90% {
      transform: translateY(-10px);
    }
    100%{
      transform: translateY(0);
    }
  }
@keyframes bounce-3 {
  0%{
    transform: translateY(-200px);
  }
  80% {
    transform: translateY(0px);
  }
  90% {
    transform: translateY(-10px);
  }
  100%{
    transform: translateY(0);
  }
}
@keyframes bounce-4 {
  0%{
    transform: translateY(-250px);
  }
  80% {
    transform: translateY(0px);
  }
  90% {
    transform: translateY(-10px);
  }
  100%{
    transform: translateY(0);
  }
}
@keyframes bounce-5 {
  0%{
    transform: translateY(-300px);
  }
  80% {
    transform: translateY(0px);
  }
  90% {
    transform: translateY(-10px);
  }
  100%{
    transform: translateY(0);
  }
}

</style>
