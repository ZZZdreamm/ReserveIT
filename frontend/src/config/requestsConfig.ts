import axios, { AxiosHeaders } from "axios";
import { ACCESS_TOKEN } from "./constants";
// require('dotenv').config(); // Load environment variables from a .env file

let apiUrl = "https://api.github.com";

const localUrl = "http://localhost:8080/api/";

if (process.env.NODE_ENV === "development") {
  apiUrl = localUrl;
}

const login = process.env.NEXT_PUBLIC_API_USERNAME;
const password = process.env.NEXT_PUBLIC_API_PASSWORD;

const headers = new AxiosHeaders({
  "Content-Type": "application/json",
});

export const axiosBase = axios.create({
  baseURL: apiUrl,
  headers: headers,
});
