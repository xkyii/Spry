"use client";

import { DataProvider, CreateResponse, UpdateResponse, DeleteOneResponse } from "@refinedev/core";
import type { AxiosInstance } from "axios";
import axios from "axios";
import { stringify } from "query-string";

type MethodTypes = "get" | "delete" | "head" | "options";
type MethodTypesWithBody = "post" | "put" | "patch";

const API_URL = "http://localhost:8080/api";
const httpClient: AxiosInstance = axios.create({ baseURL: API_URL, timeout: 2000 });

httpClient.interceptors.response.use(
    resp => {
      return { ...resp, ...resp.data };
    },
  );

export const dataProvider: DataProvider = {
    getApiUrl: () => API_URL,
    getList: async ({ resource, pagination, sorters, filters, meta }) => {
        const url = `${API_URL}/${resource}`;

        const { current = 1, pageSize = 10, mode = "server" } = pagination ?? {};
        const { headers: headersFromMeta, method } = meta ?? {};
        const requestMethod = (method as MethodTypes) ?? "get";

        const query: {
            page?: number;
            size?: number;
        } = {};

        if (mode === "server") {
            query.page = current - 1;
            query.size = pageSize;
        }

        const combinedQuery = { ...query, /* ...queryFilters */ };
        const urlWithQuery = Object.keys(combinedQuery).length
          ? `${url}?${stringify(combinedQuery)}`
          : url;

        const { data, headers } = await httpClient[requestMethod](urlWithQuery, {
            headers: headersFromMeta,
          });

          const total = +headers["x-total-count"];

          return {
            data,
            total: total || data.length,
          };
    },
    getOne: async ({ resource, id, meta }) => {
        const url = `${API_URL}/${resource}/${id}`;

        const { headers, method } = meta ?? {};
        const requestMethod = (method as MethodTypes) ?? "get";

        const { data } = await httpClient[requestMethod](url, { headers });

        return {
          data,
        };
    },
    create: async ({ resource, variables, meta }) => {
        return {

        } as CreateResponse<any>;
    },
    update: async ({ resource, id, variables, meta }) => {
        return {

        } as UpdateResponse<any>;
    },
    deleteOne: async ({ resource, id, variables, meta }) => {
        return {

        } as DeleteOneResponse<any>;
    },
};

