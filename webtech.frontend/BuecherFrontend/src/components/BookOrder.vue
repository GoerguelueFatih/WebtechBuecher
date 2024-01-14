<script setup lang="ts">
import { ref } from 'vue';
import { useAuth } from '@okta/okta-vue';
import { placeOrder , deleteCart } from './CartHandler';

const user = ref(null);
const isLoading = ref(false);
const showConfirmation = ref(false);

async function handlePlaceOrder() {
  isLoading.value = true;
  const auth = useAuth();
  user.value = await auth.getUser();
  if (!user.value) {
    isLoading.value = false;
    return;
  }

  await placeOrder(user.value.sub);
  await deleteCart();
  showConfirmation.value = true;
  setTimeout(() => showConfirmation.value = false, 3000);

  isLoading.value = false;
}
</script>

<template>
  <div>
    <button @click="handlePlaceOrder" :disabled="isLoading">
      {{ isLoading ? 'Placing Order...' : 'Place Order' }}
    </button>
    <transition name="fade">
      <p v-if="showConfirmation" class="confirmation-popup">Order Placed Successfully</p>
    </transition>
  </div>
</template>

<style scoped>

.confirmation-popup {

  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #dff0d8;
  color: #3c763d;
  padding: 20px;
  border-radius: 5px;
  text-align: center;
  z-index: 1000;
}

</style>