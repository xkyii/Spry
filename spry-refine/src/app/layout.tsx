import { DevtoolsProvider } from "@providers/devtools";
import { Refine } from "@refinedev/core";
import { RefineKbar, RefineKbarProvider } from "@refinedev/kbar";
import { RefineSnackbarProvider, useNotificationProvider } from "@refinedev/mui";
import routerProvider from "@refinedev/nextjs-router";
import { Metadata } from "next";
import { cookies } from "next/headers";
import React, { Suspense } from "react";

import { ColorModeContextProvider } from "@contexts/color-mode";
import { authProvider } from "@providers/auth-provider";
import { restDataProvider, fakeDataProvider } from "@providers/data-provider";

import GroupIcon from '@mui/icons-material/Group';
import PersonIcon from '@mui/icons-material/Person';
import RowingIcon from '@mui/icons-material/Rowing';
import AdminPanelSettingsIcon from '@mui/icons-material/AdminPanelSettings';
import LibraryBooksIcon from '@mui/icons-material/LibraryBooks';
import MenuBookIcon from '@mui/icons-material/MenuBook';

export const metadata: Metadata = {
  title: "Spry",
  description: "crud dash",
  icons: {
    icon: "/favicon.ico",
  },
};


export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const cookieStore = cookies();
  const theme = cookieStore.get("theme");
  const defaultMode = theme?.value === "dark" ? "dark" : "light";

  return (
    <html lang="en">
      <body>
        <Suspense>
          <RefineKbarProvider>
            <ColorModeContextProvider defaultMode={defaultMode}>
              <RefineSnackbarProvider>
                <DevtoolsProvider>
                  <Refine
                    routerProvider={routerProvider}
                    dataProvider={{ default: restDataProvider, fake: fakeDataProvider }}
                    notificationProvider={useNotificationProvider}
                    authProvider={authProvider}
                    resources={[
                      {
                        name: "dept",
                        list: "/dept",
                        create: "/dept/create",
                        edit: "/dept/edit/:id",
                        show: "/dept/show/:id",
                        meta: {
                          canDelete: true,
                          label: "部门",
                          icon: <GroupIcon />,
                        },
                      },
                      {
                        name: "user",
                        list: "/user",
                        create: "/user/create",
                        edit: "/user/edit/:id",
                        show: "/user/show/:id",
                        meta: {
                          canDelete: true,
                          label: "用户",
                          icon: <PersonIcon />,
                        },
                      },
                      {
                        name: "role",
                        list: "/role",
                        create: "/role/create",
                        edit: "/role/edit/:id",
                        show: "/role/show/:id",
                        meta: {
                          canDelete: true,
                          label: "角色",
                          icon: <RowingIcon />,
                        },
                      },
                      {
                        name: "permission",
                        list: "/permission",
                        create: "/permission/create",
                        edit: "/permission/edit/:id",
                        show: "/permission/show/:id",
                        meta: {
                          canDelete: true,
                          label: "权限",
                          icon: <AdminPanelSettingsIcon />,
                        },
                      },
                      {
                        name: "dict-type",
                        list: "/dict-type",
                        create: "/dict-type/create",
                        edit: "/dict-type/edit/:id",
                        show: "/dict-type/show/:id",
                        meta: {
                          canDelete: true,
                          label: "字典",
                          icon: <LibraryBooksIcon />,
                        },
                      },
                      {
                        name: "dict-data",
                        list: "/dict-data",
                        create: "/dict-data/create",
                        edit: "/dict-data/edit/:id",
                        show: "/dict-data/show/:id",
                        meta: {
                          canDelete: true,
                          hide: true,
                          label: "字典数据",
                          icon: <MenuBookIcon />,
                        },
                      },
                    ]}
                    options={{
                      syncWithLocation: true,
                      warnWhenUnsavedChanges: true,
                      useNewQueryKeys: true,
                      projectId: "JtD2uQ-cegf5Y-hbtHKC",
                    }}
                  >
                    {children}
                    <RefineKbar />
                  </Refine>
                </DevtoolsProvider>
              </RefineSnackbarProvider>
            </ColorModeContextProvider>
          </RefineKbarProvider>
        </Suspense>
      </body>
    </html>
  );
}
