import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import { LoginCallback, navigationGuard } from '@okta/okta-vue'
import Login from '@/views/Login.vue'
import Profile from '@/views/Profile.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/orders',
      name: 'orders',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/OrdersView.vue')
    },
    {
      path: '/addBooks',
      name: 'addBooks',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AddBooksView.vue')
    },
    {
      path: '/deleteBooks',
      name: 'deleteBooks',
      component: () => import('../views/DeleteBooksView.vue')
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/login/callback',
      component: LoginCallback
    },
    {
      path: '/profile',
      component: Profile,
      meta: {
        requiresAuth: true
      }
    },
  ]
})

router.beforeEach(navigationGuard)

export default router
