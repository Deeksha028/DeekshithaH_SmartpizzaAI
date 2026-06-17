import axios from "axios";

const API_URL = "http://localhost:8080/api/orders";
 
const getAuthHeader = () => {
  const token = localStorage.getItem("token");
 
  return {
    headers: {
      Authorization: `Bearer ${token}`
    }
  };
};
 
export const placeOrder = (orderData) => {
  return axios.post(
    API_URL,
    orderData,
    getAuthHeader()
  );
};
 
export const getOrdersByEmail = (email) => {
  return axios.get(
    `${API_URL}/user/${email}`,
    getAuthHeader()
  );
};


export const getAllOrders = () => {
  const token = localStorage.getItem("token");

  return axios.get(API_URL, {
    headers: {
      Authorization: `Bearer ${token}`
    }
  });
};

//  UPDATE STATUS
export const updateOrderStatus = (id, status) => {
  const token = localStorage.getItem("token");

  return axios.put(
    `${API_URL}/${id}/status?status=${status}`,
    {},
    {
      headers: {
        Authorization: `Bearer ${token}`
      }
    }
  );
};


