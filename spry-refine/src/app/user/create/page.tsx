"use client";

import { Box, TextField } from "@mui/material";
import { Create } from "@refinedev/mui";
import { useForm } from "@refinedev/react-hook-form";

export default function CategoryCreate() {
  const {
    saveButtonProps,
    refineCore: { formLoading },
    register,
    formState: { errors },
  } = useForm({});

  return (
    <Create isLoading={formLoading} saveButtonProps={saveButtonProps}>
      <Box
        component="form"
        sx={{ display: "flex", flexDirection: "column" }}
        autoComplete="off"
      >
        <TextField
          {...register("username", {
            required: "This field is required",
          })}
          error={!!(errors as any)?.username}
          helperText={(errors as any)?.username?.message}
          margin="normal"
          fullWidth
          InputLabelProps={{ shrink: true }}
          type="text"
          label={"用户名"}
          name="username"
        />
        <TextField
          {...register("nickname", {
            required: "This field is required",
          })}
          error={!!(errors as any)?.nickname}
          helperText={(errors as any)?.nickname?.message}
          margin="normal"
          fullWidth
          InputLabelProps={{ shrink: true }}
          type="text"
          label={"昵称"}
          name="nickname"
        />
        <TextField
          {...register("password", {
            required: "This field is required",
          })}
          error={!!(errors as any)?.password}
          helperText={(errors as any)?.password?.message}
          margin="normal"
          fullWidth
          InputLabelProps={{ shrink: true }}
          type="text"
          label={"密码"}
          name="password"
        />
      </Box>
    </Create>
  );
}
