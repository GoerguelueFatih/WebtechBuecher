<!--<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';

const emit = defineEmits(['userAdded']);
const username = ref('');
const password = ref('');

function onSubmit() {
  const userData = {
    username: username.value,
    password: password.value,
  };

  axios.post('http://localhost:8080/users', userData)
      .then((response) => {
        emit('userAdded', response.data);
        username.value = '';
        password.value = '';
      })
      .catch((error) => {
        console.error("Error creating user:", error);
      });
}
</script>

<template>
  <div class="register-container">
    <h1>Register to Bookstore</h1>
    <form @submit.prevent="onSubmit" class="register-form">
      <div class="input-group">
        <label for="username">Username:</label>
        <input v-model="username" type="text" id="username" required>
      </div>

      <div class="input-group">
        <label for="password">Password:</label>
        <input v-model="password" type="password" id="password" required>
      </div>

      <button type="submit" class="btn-register">Register</button>
      <router-link to="/login" class="btn-signin">Already have an Account?</router-link>
    </form>
  </div>
</template>

<style scoped>
.register-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background-color: #f9f9f9;
  font-family: 'Arial', sans-serif;
}

.register-form {
  background: #ffffff;
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
}

.input-group {
  margin-bottom: 1rem;
}

.input-group label {
  color: #000000;
  font-size: 1rem;
}

input {
  width: 100%;
  padding: 0.5rem;
  margin-top: 0.25rem;
  border: 1px solid #4b4a4a;
  border-radius: 5px;
  background-color: #ffffff;
  color: #333;
}

input:focus {
  outline: 2px solid #4caf50;
}

button, .btn-signin {
  width: 100%;
  padding: 0.75rem;
  margin-top: 0.5rem;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-weight: bold;
}

.btn-register {
  background-color: #4caf50;
  color: white;
}

.btn-signin {
  background-color: #d30909;
  color: white;
  text-decoration: none;
  text-align: center;
  display: block;
  margin-top: 1rem;
}

button:hover, .btn-signin:hover {
  opacity: 0.9;
}

h1 {
  margin-bottom: 2rem;
  color: #333;
}
</style>
-->