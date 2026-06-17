import axios from "axios";

const BASE_URL = "http://localhost:8080/api/pizzas";

// ✅ GET ALL PIZZAS
export const getAllPizzas = () => {
  const token = localStorage.getItem("token");

  return axios.get(BASE_URL, {
    headers: {
      Authorization: `Bearer ${token}`
    }
  });
};

// ✅ ADD PIZZA
export const addPizza = (pizza) => {
  const token = localStorage.getItem("token");

  return axios.post(BASE_URL, pizza, {
    headers: {
      Authorization: `Bearer ${token}`
    }
  });
};

// ✅ DELETE PIZZA
export const deletePizza = (id) => {
  const token = localStorage.getItem("token");

  return axios.delete(`${BASE_URL}/${id}`, {
    headers: {
      Authorization: `Bearer ${token}`
    }
  });
};

export const updatePizza = (id, pizza) => {
  const token = localStorage.getItem("token");

  return axios.put(`${BASE_URL}/${id}`, pizza, {
    headers: {
      Authorization: `Bearer ${token}`
    }
  });
};
