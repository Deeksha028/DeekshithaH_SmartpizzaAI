import axios from "axios";
 
const API_URL = "http://localhost:8080/api/coupons";
 
export const getCouponByCode = async (code) => {
  const token = localStorage.getItem("token");
 
  const response = await axios.get(
    `${API_URL}/code/${code}`,
    {
      headers: {
        Authorization: `Bearer ${token}`
      }
    }
  );
 
  return response.data;
};