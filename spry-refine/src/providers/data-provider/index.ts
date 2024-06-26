"use client";

import { dataProvider } from "./rest";
import { DataProvider } from "@refinedev/core";

const API_URL = "http://localhost:5050/api";

export const restDataProvider: DataProvider = dataProvider(API_URL);
export * from "./fake";
