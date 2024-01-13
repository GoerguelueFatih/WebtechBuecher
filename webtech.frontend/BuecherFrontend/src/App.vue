<script setup lang="ts">
import { RouterLink, RouterView, useRoute } from 'vue-router'
import { watch, onMounted, ref } from 'vue'
import { useAuth } from '@okta/okta-vue'

const $auth = useAuth()
const $route = useRoute()
const authenticated = ref(false)
async function logout() {
  await $auth.signOut()
}
async function isAuthenticated() {
  authenticated.value = await $auth.isAuthenticated()
}
watch(() => $route.path, async () => {
  await isAuthenticated()
})
onMounted(async () => {
  await isAuthenticated()
  $auth.authStateManager.subscribe(isAuthenticated)
})
</script>

<template>
  <header>
    <nav>
      <RouterLink to="/">Home</RouterLink>
      <RouterLink to="/about">About</RouterLink>
      <RouterLink to="/login" v-if="!authenticated">Login</RouterLink>
      <RouterLink to="/profile" v-if="authenticated">Profile</RouterLink>
      <a href="#" v-if="authenticated" @click.prevent="logout">Logout</a>
    </nav>
  </header>
  <RouterView />
</template>

<style scoped>
html, body, #app {
  padding: 0;
  margin: 0;
}

header {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  position: fixed;
  top: 0;
  left: 0;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  z-index: 1000;
}

nav {
  display: flex;
  padding: 1rem 0;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  text-decoration: none;
}


nav a.router-link-active {
  color: var(--color-text);
}

nav a:hover {
  background-color: transparent;
}

@media (min-width: 1024px) {
  header {
    line-height: 1.5;
  }

  nav {
    font-size: 1rem;
  }
}
</style>