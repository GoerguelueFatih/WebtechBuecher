<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useAuth } from '@okta/okta-vue';

const auth = useAuth();
const userOrders = ref([]);

async function fetchAllOrders() {
  try {
    const token = await auth.getAccessToken();
    if (!token) {
      throw new Error("No authentication token found.");
    }
    const response = await axios.get('http://localhost:8080/orders/orderDTO', {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    userOrders.value = response.data;
  } catch (error) {
    console.error("Error fetching all orders:", error);
  }
}

onMounted(fetchAllOrders);
</script>

<template>
  <div>
    <h2>Your Orders</h2>
    <table v-if="userOrders.length > 0">
      <thead>
      <tr>
        <th>Order ID</th>
        <th>Total</th>
        <th>Status</th>
        <th>Books in Order</th>
        <th>Order Date</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="order in userOrders" :key="order.id">
        <td>{{ order.id }}</td>
        <td>{{ order.total }}€</td>
        <td>{{ order.status }}</td>
        <td>
          <ul>
            <li v-for="bookTitle in order.bookTitles" :key="bookTitle">
              {{ bookTitle }}
              ----------------
            </li>
          </ul>
        </td>
        <td>{{ new Date(order.localDateTime).toLocaleString() }}</td>
      </tr>
      </tbody>
    </table>
    <p v-else>You have no orders placed.</p>
  </div>
</template>

<style scoped>

div {
  margin-top: 75px;
}
table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 30px;
}

th, td {
  border: 1px solid #ffffff;
  padding: 8px;
}

th {
  background-color: #3b3b3b;
}

tr:nth-child(even) {
  background-color: #000000;
}

ul {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

ul li {
  padding: 2px 0;
}
</style>

