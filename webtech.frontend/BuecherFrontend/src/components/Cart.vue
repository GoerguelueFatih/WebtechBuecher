<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios from 'axios';

const cartItems = ref([]);
const cartId = ref(null);
onMounted(() => {
});

async function addBookToCart(book) {
  if (!cartId.value) {
    await createCart();
  }
  try {
    await axios.post('http://localhost:8080/carts/${cartId.value}/books, book');
    cartItems.value.push(book);
  } catch (error) {
    console.error("Error adding book to cart:", error);
  }
}

async function removeFromCart(book) {
  try {
    await axios.delete(`http://localhost:8080/carts/${cartId.value

    }/books`, { data: book });
// Update the cartItems by filtering out the removed book
    cartItems.value = cartItems.value.filter(item => item.id !== book.id);
  } catch (error) {
    console.error("Error removing book from cart:", error);
  }
}

async function clearCart() {
  try {
    await axios.delete('http://localhost:8080/carts/${cartId.value}');
    cartItems.value = [];
  } catch (error) {
    console.error("Error clearing cart:", error);
  }
}

async function createCart() {
  try {
    const response = await axios.post('http://localhost:8080/carts');
    cartId.value = response.data.id;
  } catch (error) {
    console.error("Error creating cart:", error);
  }
}

async function fetchCart() {
  if (!cartId.value) return;
  try {
    const response = await axios.get('http://localhost:8080/carts/${cartId.value}');
    cartItems.value = response.data.books;
  } catch (error) {
    console.error("Error fetching cart:", error);
  }
}
</script>

<template>
  <div class="cart">
    <h2>Your Cart</h2>
    <ul v-if="cartItems.length">
      <li v-for="(item, index) in cartItems" :key="index">
        {{ item.title }} by {{ item.author }} - Price: {{ item.price }}€
        <button @click="removeFromCart(item)">Remove</button>
      </li>
    </ul>
    <p v-else>Your cart is empty.</p>
    <button v-if="cartItems.length" @click="clearCart">Clear Cart</button>
  </div>
</template>

<style scoped>
.cart {
  /* Your CSS styles */
}

.cart ul {
  /* Styles for the list */
}

.cart button {
  /* Button styles */
}
</style>
