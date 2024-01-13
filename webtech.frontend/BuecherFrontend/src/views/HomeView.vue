<script setup>
import BookSearch from "@/components/BookSearch.vue";
import CategorySearch from "@/components/CategorySearch.vue";
</script>


<template>
  <main class="layout">
    <header class="header">
      <BookSearch class="search-bar"></BookSearch>
    </header>
    <aside class="category-search">
      <CategorySearch></CategorySearch>
    </aside>
  </main>
</template>


<style scoped>
.layout {
  display: flex;
  flex-direction: column;
  height: 100vh;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
  height: 60%;
}

.search-bar {
  flex-grow: 1;
  margin-right: 2rem;
}

.user-login {
  margin-left: auto;
  padding: 0.5rem 1rem;
  text-decoration: none;
  background-color: #4caf50;
  color: white;
  border-radius: 5px;
}

.category-search {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  padding: 1rem;
}

</style>
