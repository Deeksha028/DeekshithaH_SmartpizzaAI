import axios from "axios";
 
const API =
  "http://localhost:8080/api/payments";
 
export const makePayment = async (data) => {
 
  const token =
    localStorage.getItem("token");
 
  const response = await axios.post(
    API,
    data,
    {
      headers: {
        Authorization: `Bearer ${token}`
      }
    }
  );
 
  return response.data;
};
 
export const getInvoice = async (
  paymentId
) => {
 
  const token =
    localStorage.getItem("token");
 
  const response = await axios.get(
    `${API}/invoice/${paymentId}`,
    {
      headers: {
        Authorization: `Bearer ${token}`
      }
    }
  );
 
  return response.data;
};