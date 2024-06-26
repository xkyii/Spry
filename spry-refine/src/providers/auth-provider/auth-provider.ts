"use client";

import { AuthProvider } from "@refinedev/core";
import Cookies from "js-cookie";
import axios from "axios";

const API_URL = "http://localhost:5050/api";
const http = axios.create({ baseURL: API_URL, timeout: 2000});

http.interceptors.response.use(
  resp => {
    return { ...resp, ...resp.data };
  },
);

export const authProvider: AuthProvider = {
  login: async ({ email, username, password, remember }) => {
    const response = await http.post("/auth/login", { email, username, password });
    if (response.status != 200) {
      return {
        success: false,
        error: {
          name: "LoginError",
          message: "Invalid username or password",
        },
      };
    }

    const { token, ...auth } = response.data;
    Cookies.set("token", token);
    Cookies.set("auth", JSON.stringify({...auth, name: "xkyii", avatar: "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/841.jpg" }));

    return {
      success: true,
      redirectTo: "/",
    };
  },
  logout: async () => {
    Cookies.remove("auth");
    Cookies.remove("token");
    return {
      success: true,
      redirectTo: "/login",
    };
  },
  check: async () => {
    const auth = Cookies.get("auth");
    const token = Cookies.get("token");
    if (auth && token) {
      return {
        authenticated: true,
      };
    }

    return {
      authenticated: false,
      logout: true,
      redirectTo: "/login",
    };
  },
  getPermissions: async () => {
    const auth = Cookies.get("auth");
    if (auth) {
      const parsedUser = JSON.parse(auth);
      return parsedUser.roles;
    }
    return null;
  },
  getIdentity: async () => {
    const auth = Cookies.get("auth");
    if (auth) {
      const parsedUser = JSON.parse(auth);
      return parsedUser;
    }
    return null;
  },
  onError: async (error) => {
    if (error.response?.status === 401) {
      return {
        logout: true,
      };
    }

    return { error };
  },
};
