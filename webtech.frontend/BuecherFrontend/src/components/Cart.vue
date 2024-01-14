<script setup lang="ts">
import { ref, watch } from 'vue';
import {clearCart, deleteCart, getSelectedBooks} from './CartHandler';


const props = defineProps({
  books: Array
});

const cart = ref({ books: getSelectedBooks().value });

watch(() => props.books, (newBooks, oldBooks) => {
  if (newBooks !== oldBooks) {
    cart.value.books = newBooks;
  }
}, { deep: true });


watch(getSelectedBooks, (newBooks) => {
  cart.value.books = newBooks;
}, { deep: true });
</script>

<template>
  <div class="cart-container">
    <h2>Your Cart</h2>
    <div v-if="cart.books.length">
      <ul>
        <li v-for="book in cart.books" :key="book.id">
          {{ book.title }} by {{ book.author }} - Price: {{ book.price }}€
        </li>
      </ul>
      <button @click="clearCart" class="clear-cart-button">Clear Cart</button>
      <button @click="deleteCart" class="delete-cart-Button">Delete Cart</button>
    </div>
    <div v-else>
      <p>Your cart is empty.</p>
    </div>
  </div>
</template>

<style scoped>
.cart-container {
  max-width: 600px;
  margin: auto;
  padding: 1rem;
  background-color: #020202;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.cart-header {
  font-size: 1.5rem;
  color: #333;
  margin-bottom: 1rem;
}

.cart-list {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

.cart-item {
  background-color: #d72424;
  border: 1px solid #ddd;
  padding: 0.75rem 1rem;
  margin-bottom: 0.5rem;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.cart-item:hover {
  background-color: #f0f0f0;
}

.cart-item-title {
  font-weight: bold;
  color: #444;
}

.cart-item-details {
  font-size: 0.9rem;
  color: #666;
  margin-top: 0.5rem;
}

.clear-cart-button {
  background-color: #e74c3c;
  color: #ffffff;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.clear-cart-button:hover {
  background-color: #c0392b;
}

.delete-cart-Button {
  background-color: #e74c3c;
  color: #ffffff;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.delete-cart-Button:hover {
  background-color: #c0392b;
}
</style>

