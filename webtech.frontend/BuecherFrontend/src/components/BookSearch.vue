<script setup lang="ts">
import { ref, onMounted, defineEmits } from 'vue';
import axios from 'axios';

const books = ref([]);
const searchTerm = ref('');
const emit = defineEmits(['add-book-to-cart']);

function onSearchSubmit() {
  searchBooks();
}

function searchBooks() {
  if (searchTerm.value.length >= 3) {
    axios.get(`http://localhost:8080/books/search?title=${encodeURIComponent(searchTerm.value)}`)
        .then(response => {
          books.value = response.data;
        })
        .catch(error => {
          console.error("Error searching books:", error);
          books.value = [];
        });
  } else {
    books.value = [];
  }
}

function addToCart(book) {
  emit('add-book-to-cart', book);
}

</script>


<template>
  <div class="book-search">
    <form @submit.prevent="onSearchSubmit">
      <input
          type="text"
          v-model="searchTerm"
          @input="searchBooks"
          placeholder="Search books..."
      />
      <div class="search-results">
        <ul v-if="books.length">
          <li v-for="book in books" :key="book.id">
            {{ book.title }} by {{ book.author }}
            <button @click="addToCart(book)">Add to Cart</button>
          </li>
        </ul>
        <p v-else>No books found.</p>
      </div>
    </form>
  </div>
</template>


<style scoped>
.book-search input {
  width: 100%;
  padding: 0.5rem;
  margin-bottom: 1rem;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.search-results {
  max-height: 300px;
  overflow-y: auto;
}

.book-search ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.book-search li {
  padding: 0.5rem 0;
  border-bottom: 1px solid #eee;
}
</style>
