import axios from 'axios';

const API_URL = 'http://localhost:8080/api'; // Replace with your Spring Boot backend URL

const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

const getUserId = () => {
  return JSON.parse(localStorage.getItem("user")).id;
}
// Add a request interceptor to include the auth token in requests
// api.interceptors.request.use(
//   (config) => {
//     const token = localStorage.getItem('token');
//     if (token) {
//       config.headers['Authorization'] = `Bearer ${token}`;
//     }
//     return config;
//   },
//   (error) => Promise.reject(error)
// );

export const login = (credentials) => api.post('/auth/login', credentials);
export const register = (userData) => api.post('/auth/register', userData);
export const fetchDashboardData = () => api.get(`/dashboard?id=${getUserId()}`);
export const fetchExpenses = () => api.get(`/expenses?id=${getUserId()}`);
export const addExpense = (expense) => api.post(`/expenses?id=${getUserId()}`, expense);
export const fetchIncomes = () => api.get(`/incomes?id=${getUserId()}`);
export const addIncome = (income) => api.post(`/incomes?id=${getUserId()}`, income);
export const fetchUserProfile = () => api.get(`/user/profile?id=${getUserId()}`);
export const updateUserProfile = (profile) => api.put(`/user/profile?id=${getUserId()}`, profile);
export const fetchUsers = () => api.get('/admin/users');

export default api;