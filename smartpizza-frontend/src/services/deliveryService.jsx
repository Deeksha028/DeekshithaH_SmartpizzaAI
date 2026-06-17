import axios from "axios";
 
const API_URL = "http://localhost:8080/api/delivery";
 
export const getDeliveryByOrderId = async (orderId) => {
  const token = localStorage.getItem("token");
 
  const response = await axios.get(
    `${API_URL}/order/${orderId}`,
    {
      headers: {
        Authorization: `Bearer ${token}`
      }
    }
  );
 
  return response.data;
};
 
export const getDeliveryRoute = async (deliveryId) => {
  const token = localStorage.getItem("token");
 
  const response = await axios.get(
    `${API_URL}/route/${deliveryId}`,
    {
      headers: {
        Authorization: `Bearer ${token}`
      }
    }
  );
 
  return response.data;
};