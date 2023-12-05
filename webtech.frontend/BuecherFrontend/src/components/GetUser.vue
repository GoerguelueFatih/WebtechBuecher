<script setup lang="ts">
import axios from 'axios';
import { ref, onMounted } from 'vue';

type User = {
  id: string;
  username: string;
  password: string;
};

let users = ref<User[]>([]);

onMounted(async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/user');
    users.value = response.data;
  } catch (error) {
    console.error('Error fetching user data:', error);
  }
});

</script>
