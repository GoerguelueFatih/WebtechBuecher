<script setup lang="ts">
import axios from 'axios';
import { ref } from 'vue';
import type { Ref } from 'vue';
import AddUser from '@/components/AddUser.vue';

type User = {
  id: string;
  username: string;
  password: string;
}

let users: Ref<User[]> = ref([]);
axios
    .get('http://localhost:8080/user')
    .then((response) => {
      users.value = response.data;
    });

function userAddedByComp(user: User) {
  users.value.push(user);
}

</script>

<template>
  <table>
    <tr>
      <th>ID</th>
      <th>Username</th>
      <th>Password</th>

    </tr>
    <tr v-for="user in users" :key="user.id">
      <td>{{ user.id }}</td>
      <td>{{ user.username }}</td>
      <td>{{ user.password }}</td>

    </tr>
  </table>

  <AddUser @userAdded="userAddedByComp"></AddUser>
</template>


<style scoped>
table, th, td {
  border: 1px solid;
}
</style>
