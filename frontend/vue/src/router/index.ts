// Composables
import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Menu from '../views/Menu.vue'


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
    path: '/partie/:id',
    component: Home,
    name: 'partie'
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
