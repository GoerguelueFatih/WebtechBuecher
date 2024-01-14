<script lang="ts">
import axios from "axios";
import { useAuth } from "@okta/okta-vue";

export default {
  data() {
    return {
      newBook: {
        title: "",
        author: "",
        price: null,
        category: {
          name: "",
        },
        releaseYear: null,
      },
      currentYear: new Date().getFullYear(),
      minYear: 1000,
      errorMessage: "",
    };
  },
  methods: {
    async addBook() {
      try {
        const auth = useAuth();
        const token = await auth.getAccessToken();

        if (
            isNaN(this.newBook.releaseYear) ||
            this.newBook.releaseYear < this.minYear ||
            this.newBook.releaseYear > this.currentYear
        ) {
          this.errorMessage = "Invalid Release Year";
          return;
        }

        this.errorMessage = "";

        const response = await axios.post(
            "http://localhost:8080/books",
            {
              ...this.newBook,
              releaseDate: this.newBook.releaseYear.toString(),
            },
            {
              headers: {
                Authorization: `Bearer ${token}`,
              },
            }
        );

        console.log("Book added:", response.data);
        this.resetForm();
      } catch (error) {
        console.error("Error adding book:", error);
        this.errorMessage = "Error adding book";
      }
    },
    resetForm() {
      this.newBook = {
        title: "",
        author: "",
        price: null,
        category: {
          name: "",
        },
        releaseYear: null,
      };
    },
  },
};
</script>

<template>
  <form @submit.prevent="addBook" class="book-form">
    <div class="form-group">
      <label for="title">Title:</label>
      <input type="text" id="title" v-model="newBook.title" required>
    </div>
    <div class="form-group">
      <label for="author">Author:</label>
      <input type="text" id="author" v-model="newBook.author" required>
    </div>
    <div class="form-group">
      <label for="price">Price:</label>
      <input type="number" id="price" v-model="newBook.price" step="0.01" required>
    </div>
    <div class="form-group">
      <label for="category">Category:</label>
      <input type="text" id="category" v-model="newBook.category.name" required>
    </div>
    <div class="form-group">
      <label for="releaseYear">Release Year:</label>
      <input
          type="number"
          id="releaseYear"
          v-model.number="newBook.releaseYear"
          required
          :max="currentYear"
          :min="minYear"
      >
    </div>
    <button type="submit" class="submit-button">Add Book</button>
    <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
  </form>
</template>

<style scoped>
/* Improved form styling */
.book-form {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #000000;
  border-radius: 5px;
  background-color: #000000;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 10px;
}

label {
  display: block;
  font-weight: bold;
}

input[type="text"],
input[type="number"] {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

.submit-button {
  background-color: #2f6c22;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 3px;
  cursor: pointer;
}

.submit-button:hover {
  background-color: #23561b;
}

.error-message {
  color: red;
  margin-top: 10px;
  font-weight: bold;
}
</style>

