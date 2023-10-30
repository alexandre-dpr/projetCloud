// Composables
import { createRouter, createWebHistory } from 'vue-router';
import Partie from '../views/Partie.vue'
import Login from '../views/Login.vue'
import Menu from '../views/Menu.vue'
import Salon from '../views/Salon.vue'


const routes = [
  {
    path: '/login',
    component: Login,
    name: 'login'
  },
  {
    path: '/menu',
    component: Menu,
    name: 'menu'
  },
  {
    //path: '/partie/:id',
    path: '/partie',
    component: Partie,
    name: 'partie'
  },
  {
    //path: '/partie/:id',
    path: '/salon',
    component: Salon,
    name: 'salon'
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
