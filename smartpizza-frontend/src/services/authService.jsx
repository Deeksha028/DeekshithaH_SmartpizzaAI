
import axios from "axios";
 
const API_URL = "http://localhost:8080/api/users";
 
export const login = (data) => {
    return axios.post(`${API_URL}/login`, data);
};
 
export const register = (data) => {
    return axios.post(`${API_URL}/register`, data);
};
export const forgotPassword = (email) => {
    return axios.post(
        `http://localhost:8080/api/users/forgot-password?email=${email}`
    );
};
export const resetPassword = (
    email,
    otp,
    newPassword
) => {
    return axios.post(
        `http://localhost:8080/api/users/reset-password?email=${email}&otp=${otp}&newPassword=${newPassword}`
    );
};
 