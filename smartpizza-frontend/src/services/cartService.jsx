import axios from "axios";
 
const API_URL = "http://localhost:8080/api/cart";
 
const getAuthHeader = () => {
 
    const token = localStorage.getItem("token");
 
    return {
        headers: {
            Authorization: `Bearer ${token}`
        }
    };
};
 
export const addToCart = (cartData) => {
 
    return axios.post(
        API_URL,
        cartData,
        getAuthHeader()
    );
};
 
export const getCart = (email) => {
 
    return axios.get(
        `${API_URL}/${email}`,
        getAuthHeader()
    );
};
 
export const removeCartItem = (id) => {
 
    return axios.delete(
        `${API_URL}/${id}`,
        getAuthHeader()
    );
};
export const clearCart = (email) => {
 
    return axios.delete(
        `${API_URL}/clear/${email}`,
        getAuthHeader()
    );
 
};
 