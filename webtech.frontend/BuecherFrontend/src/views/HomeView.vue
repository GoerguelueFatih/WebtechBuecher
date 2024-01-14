<script setup lang="ts">
import { ref } from 'vue';
import BookSearch from "@/components/BookSearch.vue";
import CategorySearch from "@/components/CategorySearch.vue";
import Cart from "@/components/Cart.vue";
import BookOrder from "@/components/BookOrder.vue";

const cartItems = ref([]);

const addToCart = (book) => {
  cartItems.value.push(book);
};
</script>


<template>
  <main class="layout">
    <header class="header">
      <BookSearch class="search-bar" @add-to-cart="addToCart" />
    </header>
    <aside class="category-search">
      <CategorySearch />
    </aside>

    <aside class="cart">
      <Cart :items="cartItems" />
    </aside>
    <aside class="order">
      <BookOrder></BookOrder>
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


.category-search {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  padding: 1rem;
}

</style>
