<!--<script setup lang="ts">
import axios from 'axios';
import { ref } from 'vue';
import type { Ref } from 'vue';
import LoginUser from '@/components/LoginUser.vue';

type User = {
  id: string;
  username: string;
  password: string;
}

let users: Ref<User[]> = ref([]);
axios
    .get('http://localhost:8080/users')
    .then((response) => {
      users.value = response.data;
    });

function userAddedByComp(user: User) {
  users.value.push(user);
}

</script>

<template>
  <LoginUser @userAdded="userAddedByComp"></LoginUser>
</template>


<style scoped>
table, th, td {
  border: 1px solid;
}
</style>
-->