"use client";

import { DataProvider, GetOneResponse, CreateResponse, UpdateResponse, DeleteOneResponse } from "@refinedev/core";
import axios from "axios";
import { stringify } from "query-string";

const API_URL = "http://localhost:8080/api";
const http = axios.create({ baseURL: API_URL, timeout: 2000 });

export const dataProvider: DataProvider = {
    getApiUrl: () => API_URL,
    getList: async ({ resource, pagination, sorters, filters, meta }) => {

        const { current = 1, pageSize = 10, mode = "server" } = pagination ?? {};

        const query: {
            page?: number;
            size?: number;
        } = {};

        if (mode === "server") {
            query.page = current - 1;
            query.size = pageSize;
        }

        const response = await http.get(`${resource}?${stringify(query)}`);
        const data = response.data.data;
        return {
            data: data,
            total: data.length,
        }
    },
    getOne: async ({ resource, id, meta }) => {
        return {
            data: { id: 0 },
        } as GetOneResponse<any>;
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

