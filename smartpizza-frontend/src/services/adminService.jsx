import axios from "axios";
 
const API = "http://localhost:8080/api/admin";
 
const authConfig = () => ({
  headers: {
    Authorization: `Bearer ${localStorage.getItem("token")}`
  }
});
 
export const getAnalytics = () =>
  axios.get(`${API}/analytics`, authConfig());
 
export const getOrderHeatmap = () =>
  axios.get(`${API}/order-heatmap`, authConfig());
 
export const getCustomerTrends = () =>
  axios.get(`${API}/customer-trends`, authConfig());
 
export const getDeliveryPerformance = () =>
  axios.get(`${API}/delivery-performance`, authConfig());

