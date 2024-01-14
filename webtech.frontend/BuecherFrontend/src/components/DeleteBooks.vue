<script lang="ts">
import axios from "axios";
import { useAuth } from '@okta/okta-vue';

export default {
  data() {
    return {
      books: [],
    };
  },
  created() {
    this.fetchBooks();
  },
  methods: {
    async fetchBooks() {
      try {
        const auth = useAuth();
        const token = await auth.getAccessToken();
        const response = await axios.get("http://localhost:8080/books/all", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        this.books = response.data;
      } catch (error) {
        console.error("Error fetching books:", error);
      }
    },
    async deleteBook(isbn) {
      try {
        const auth = useAuth();
        const token = await auth.getAccessToken();
        const response = await axios.delete(`http://localhost:8080/books/${isbn}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        console.log(response.data);
        this.books = this.books.filter((book) => book.isbn !== isbn);
      } catch (error) {
        console.error("Error deleting book:", error);
      }
    },
  },
};
</script>

<template>
  <div class="book-list-container">
    <h2>Existing Books</h2>
    <ul>
      <li v-for="book in books" :key="book.isbn">
        <h3>{{ book.title }}</h3>
        <p><strong>Author:</strong> {{ book.author }}</p>
        <p><strong>Price:</strong> ${{ book.price }}</p>
        <p><strong>Category:</strong> {{ book.category.name }}</p>
        <p><strong>Release Year:</strong> {{ book.releaseDate }}</p>
        <button @click="deleteBook(book.isbn)">Delete</button>
      </li>
    </ul>
  </div>
</template>

<style scoped>

.book-list-container {
  max-height: 400px;
  overflow-y: scroll;
  border: 1px solid #ccc;
  padding: 10px;
}

ul {
  list-style-type: none;
  padding: 0;
}
li {
  margin-bottom: 10px;
}
button {
  background-color: red;
  color: white;
  border: none;
  padding: 5px 10px;
  cursor: pointer;
}
button:hover {
  background-color: darkred;
}
</style>
