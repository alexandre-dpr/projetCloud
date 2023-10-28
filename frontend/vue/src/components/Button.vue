<template>
  <v-btn
    block
    :class="btnClass"
    :onclick="handleClick"
  >{{label}}</v-btn>
</template>

<script lang="ts" setup>
import {defineProps} from 'vue'

defineProps({
  label:String,
  btnClass:String,
  handleClick: Function
})
var isLogin = true;

function setIsLogin(){
  console.log(isLogin)
  isLogin = !isLogin;
}
</script>

<style scoped>
/* Styles spécifiques au composant de bouton */
.red{
  background-color: red;
}
.yellow{
  background-color: yellow;
}

/* Vous pouvez ajouter d'autres classes CSS ici */
</style>
