// Composables
import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue'


const routes = [
  {
    path: '/',
    component: Home,
    name: 'accueil'

  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
