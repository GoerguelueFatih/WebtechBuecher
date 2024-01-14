import axios from 'axios';
import { ref } from 'vue';
import { useAuth } from '@okta/okta-vue';


const selectedBooks = ref([]);
const cartId = ref(null);


async function getAuthHeader() {
    const auth = useAuth();
    const user = await auth.getUser();
    const token = await auth.getAccessToken();

    if (!user || !token) {
        throw new Error("User is not authenticated or no token found");
    }

    return {
        headers: {
            Authorization: `Bearer ${token}`
        }
    };
}


export async function handleAddToCart(book) {
    try {
        const authHeader = await getAuthHeader();

        if (!cartId.value) {
            const user = await useAuth().getUser();
            const newCartResponse = await axios.post(`http://localhost:8080/carts/${user.sub}`, {}, authHeader);
            cartId.value = newCartResponse.data.id;
        }

        await axios.post(`http://localhost:8080/carts/${cartId.value}/books`, book, authHeader);
        const updatedCartResponse = await axios.get(`http://localhost:8080/carts/${cartId.value}`, authHeader);
        selectedBooks.value = updatedCartResponse.data.books;
    } catch (error) {
        console.error("Error handling add to cart:", error);
    }
}


export async function clearCart() {
    try {
        const authHeader = await getAuthHeader();

        if (cartId.value) {
            await axios.delete(`http://localhost:8080/carts/${cartId.value}/clear`, authHeader);
            selectedBooks.value = [];
            cartId.value = null;
        }
    } catch (error) {
        console.error("Error clearing the cart:", error);
    }
}

export async function deleteCart() {
    try {
        const authHeader = await getAuthHeader();

        if (cartId.value) {
            await axios.delete(`http://localhost:8080/carts/${cartId.value}`, authHeader);
            selectedBooks.value = [];
            cartId.value = null;
        }
    } catch (error) {
        console.error("Error clearing the cart:", error);
    }
}


export function getSelectedBooks() {
    return selectedBooks;
}

export async function CartId(userId: string) {
    try {
        const authHeader = await getAuthHeader();

        if (!cartId.value) {
            const newCartResponse = await axios.post(`http://localhost:8080/carts/${userId}`, {}, authHeader);
            cartId.value = newCartResponse.data.id;
        }
    } catch (error) {
        console.error("Error initializing cartId:", error);
        throw error;
    }
}

export async function placeOrder(userId: string) {
    try {

        await CartId(userId);

        const authHeader = await getAuthHeader();
        const response = await axios.post(`http://localhost:8080/orders/${cartId.value}/purchase?userId=${userId}`, {}, authHeader);
        return response.data;
    } catch (error) {
        console.error("Error placing the order:", error);
        throw error;
    }
}



