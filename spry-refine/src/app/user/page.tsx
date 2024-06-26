"use client";

import { useCustom, HttpError, useApiUrl } from "@refinedev/core";
import { DataGrid, GridColDef } from "@mui/x-data-grid";
import {
  DeleteButton,
  EditButton,
  List,
  ShowButton,
  useDataGrid,
} from "@refinedev/mui";
import Grid from '@mui/material/Grid';
import { TreeViewBaseItem } from '@mui/x-tree-view/models';
import { RichTreeView } from '@mui/x-tree-view/RichTreeView';
import React from "react";


type IDept<R extends {} = {
  id: string;
  name: string;
}> = R & {
  children?: TreeViewBaseItem<R>[];
};

// 转换函数
function convertArrayToArray(dept: IDept[]): TreeViewBaseItem[] {
  return dept.map(item => {
    const { id, name, children } = item;
    const treeViewItem: TreeViewBaseItem<{ id: string; label: string }> = {
      id,
      label: name,
      children: children ? convertArrayToArray(children) : []
    };
    return treeViewItem;
  });
}

function convertSingleToArray(dept: IDept | undefined): TreeViewBaseItem[] {
  if (dept == undefined) {
    return [];
  }

  const { id, name, children } = dept;
  const treeViewItem: TreeViewBaseItem<{ id: string; label: string }> = {
    id,
    label: name,
    children: children ? convertArrayToArray(children) : []
  };
  return [treeViewItem];
}


export default function UserList() {
  const apiUrl = useApiUrl();
  const { data, isLoading, isError } = useCustom<IDept, HttpError>({
    url: `${apiUrl}/dept/1/tree`,
    method: "get",
  });
  const { dataGridProps } = useDataGrid({});

  const columns = React.useMemo<GridColDef[]>(
    () => [
      {
        field: "id",
        headerName: "ID",
        type: "number",
        minWidth: 50,
      },
      {
        field: "username",
        flex: 1,
        headerName: "用户名",
        minWidth: 200,
      },
      {
        field: "nickname",
        flex: 1,
        headerName: "昵称",
        minWidth: 200,
      },
      {
        field: "actions",
        headerName: "Actions",
        sortable: false,
        renderCell: function render({ row }) {
          return (
            <>
              <EditButton hideText recordItemId={row.id} />
              <ShowButton hideText recordItemId={row.id} />
              <DeleteButton hideText recordItemId={row.id} />
            </>
          );
        },
        align: "center",
        headerAlign: "center",
        minWidth: 80,
      },
    ],
    []
  );

  return (
    <List>
      <Grid container spacing={2}>
        <Grid item xs={3}>
          <RichTreeView items={convertSingleToArray(data?.data)} />
        </Grid>
        <Grid item xs={9}>
          <DataGrid {...dataGridProps} columns={columns} autoHeight />
        </Grid>
      </Grid>
    </List>
  );
}
