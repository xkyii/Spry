"use client";

import { Header } from "./header";
import { ThemedLayoutV2 } from "./layout";
import { ThemedTitleV2 } from "./title";
import React from "react";

export const ThemedLayout = ({ children }: React.PropsWithChildren) => {
  return (
    <ThemedLayoutV2
      Header={() => <Header sticky />}
      Title={({collapsed}) => <ThemedTitleV2 collapsed={collapsed} text="Spry" />}
    >
      {children}
    </ThemedLayoutV2>
  );
};
