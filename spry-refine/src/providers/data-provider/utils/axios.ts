import type { HttpError } from "@refinedev/core";
import axios from "axios";
import Cookies from "js-cookie";

const axiosInstance = axios.create();

axiosInstance.interceptors.response.use(
  (response) => {
    return { ...response, ...response.data };
  },
  (error) => {
    const customError: HttpError = {
      ...error,
      message: error.response?.data?.message,
      statusCode: error.response?.status,
    };

    return Promise.reject(customError);
  },
);


axiosInstance.interceptors.request.use(
  request => {
    const token = Cookies.get("token");
    if (token) {
      request.headers["Authorization"] = `Bearer ${token}`;
    }
    return request;
  },
);

export { axiosInstance };
