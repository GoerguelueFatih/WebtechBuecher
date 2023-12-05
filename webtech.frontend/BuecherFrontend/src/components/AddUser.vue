<template>
  <form @submit.prevent="onSubmit">
    <label for="username">Username:</label>
    <input v-model="username" type="text" id="username" required>

    <label for="password">Password:</label>
    <input v-model="password" type="password" id="password" required>

    <button type="submit">Add User</button>
  </form>
</template>

<script setup lang="ts">
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

  axios.post('http://localhost:8080/api/user', userData)
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

<style scoped>
form {
  margin-top: 20px;
}

label {
  display: block;
  margin-bottom: 8px;
}

input {
  width: 100%;
  padding: 8px;
  margin-bottom: 16px;
}

button {
  background-color: #4caf50;
  color: white;
  padding: 10px;
  border: none;
  cursor: pointer;
}

button:hover {
  background-color: #45a049;
}
</style>
