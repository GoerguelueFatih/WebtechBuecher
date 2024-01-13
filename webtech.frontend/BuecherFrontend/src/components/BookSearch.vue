<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';
import { useAuth } from '@okta/okta-vue';

const auth = useAuth();
const books = ref([]);
const searchTerm = ref('');
const emit = defineEmits(['add-to-cart']);

async function searchBooks() {
  if (searchTerm.value.length < 3) {
    books.value = [];
    return;
  }

  try {
    const response = await axios.get(
        `http://localhost:8080/books/search?title=${encodeURIComponent(searchTerm.value)}`
    );
    books.value = response.data;
  } catch (error) {
    console.error("Error searching books:", error);
    books.value = [];
  }
}

const emitAddToCart = (book) => {
  emit('add-to-cart', book);
};
</script>

<template>
  <div class="book-search-container">
    <div class="search-bar">
      <input
        type="text"
        v-model="searchTerm"
        @input="searchBooks"
        placeholder="Search books..."
    />
      </div>
    <div class="search-results">
        <ul>
          <li v-for="book in books" :key="book.id" class="book-item">
            <span class="book-title">{{ book.title }}</span> by
            <span class="book-author">{{ book.author }}</span> -
            Price: <span class="book-price">{{ book.price }}</span>€
            <button @click="emitAddToCart(book)" class="add-to-cart-button">Add to Cart</button>
          </li>
        </ul>
      </div>
  </div>
    </template>

<style scoped>

.book-search-container {
  display: flex;
  flex-direction: column;
  height: 75%;
}

.search-bar {
  padding: 1rem;

}

.search-bar input[type="text"] {
  font-size: 1rem;
  padding: 7.5px 10px;
  width: 60%;
  margin-bottom: 1rem;
  border: 2px solid #ccc;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.search-results {
  flex-grow: 1;
  overflow-y: auto;
  max-height: calc(100vh - 0);
}

.book-item {
  padding: 1rem;
  margin-bottom: 0.5rem;
  border-bottom: 1px solid #eaeaea;
}

.book-item:last-child {
  border-bottom: none;
}

.book-item:hover {
  background-color: #494949;
}

.book-title {
  font-size: 1.1rem;
  font-weight: bold;
  color: #a8a9a7;
}

.book-author {
  font-size: 0.9rem;
  color: #727272;
}

.book-price {
  font-size: 0.9rem;

  color: #9efa96;
  font-weight: 500;
}

.add-to-cart-button {
  padding: 0.5rem 1rem;
  background-color: #5CDB95;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.add-to-cart-button:hover {
  background-color: #379683;
}


</style>
