<script setup lang="ts">
import OrdersUser from "@/components/OrdersUser.vue";
</script>

<template>
  <aside class ="AllOrders">
    <OrdersUser></OrdersUser>
  </aside>
</template>

<style>
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}
</style>