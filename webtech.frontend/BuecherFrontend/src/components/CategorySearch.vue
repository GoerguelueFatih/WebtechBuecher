<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useAuth } from '@okta/okta-vue';

const auth = useAuth();
const categories = ref([]);
const books = ref([]);

onMounted(fetchCategories);

async function fetchCategories() {
  try {
    const token = await auth.getAccessToken();
    const response = await axios.get('http://localhost:8080/category', {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    categories.value = response.data;
  } catch (error) {
    console.error("Error fetching categories:", error);
  }
}

async function fetchBooksByCategory(selectedCategory) {
  try {
    const token = await auth.getAccessToken();
    const response = await axios.get(`http://localhost:8080/books?category=${encodeURIComponent(selectedCategory.name)}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    books.value = response.data;
  } catch (error) {
    console.error("Error fetching books by category:", error);
    books.value = [];
  }
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
        <li v-for="book in books" :key="book.id">
          {{ book.title }} by {{ book.author }} #Price {{book.price}}€
        </li>
      </ul>
      <p v-else>No books found in this category.</p>
    </div>
  </div>
</template>

<style scoped>
.category-search {

}

.category-list button {

}

.book-results ul {

}


</style>
