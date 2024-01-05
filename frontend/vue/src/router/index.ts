// Composables
import { createRouter, createWebHistory } from 'vue-router';
import Partie from '../views/Partie.vue'
import Login from '../views/Login.vue'
import Menu from '../views/Menu.vue'
import Salon from '../views/Salon.vue'


const routes = [
  {
    path: '/',
    component: Login,
    name: 'login',
    meta: {
      title: "Login-Puissance 4"
    }
  },
  {
    path: '/menu',
    component: Menu,
    name: 'menu',
    meta: {
      title: "Menu-Puissance 4"
    }
  },
  {
    path: '/partie/:id',
    component: Partie,
    name: 'partie',
    meta: {
      title: "Partie-Puissance 4"
    }
  },
  {
    path: '/salon',
    component: Salon,
    name: 'salon',
    meta: {
      title: "Salon-Puissance 4"
    }
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: { name: 'login' }
  }
]


const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

router.beforeEach((to, from) => {
  document.title = to.meta?.title ?? "Puissance 4"
})
export default router
