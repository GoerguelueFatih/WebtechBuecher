<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const categories = ref([]);
const books = ref([]);

onMounted(fetchCategories);

function fetchCategories() {
  axios.get('http://localhost:8080/category')
      .then(response => {
        categories.value = response.data;
      })
      .catch(error => {
        console.error("Error fetching categories:", error);
      });
}

function fetchBooksByCategory(selectedCategory) {
  axios.get(`http://localhost:8080/books?category=${encodeURIComponent(selectedCategory.name)}`)
      .then(response => {
        books.value = response.data;
      })
      .catch(error => {
        console.error("Error fetching books by category:", error);
        books.value = [];
      });
}
</script>

<template>
  <div class="category-search">
    <h2>Search by Category</h2>
    <div class="category-list">
      <button
          v-for="category in categories"
          :key="category.id"
          @click="fetchBooksByCategory(category)"
      >
        {{ category.name }}
      </button>
    </div>
    <div class="book-results">
      <ul v-if="books.length">
        <li v-for="book in books" :key="book.isbn">
          {{ book.title }} by {{ book.author }}
        </li>
      </ul>
      <p v-else>No books found in this category.</p>
    </div>
  </div>
</template>

<style scoped>
.category-search {
  /* Style your component */
}

.category-list button {
  /* Style your buttons */
}

.book-results ul {
  /* Style your results list */
}

/* Add more styles as needed */
</style>
