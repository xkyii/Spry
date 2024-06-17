"use client";

import { Box, TextField } from "@mui/material";
import { Edit } from "@refinedev/mui";
import { useForm } from "@refinedev/react-hook-form";

export default function CategoryEdit() {
  const {
    saveButtonProps,
    register,
    formState: { errors },
  } = useForm({});

  return (
    <Edit saveButtonProps={saveButtonProps}>
      <Box
        component="form"
        sx={{ display: "flex", flexDirection: "column" }}
        autoComplete="off"
      >
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
      </Box>
    </Edit>
  );
}
