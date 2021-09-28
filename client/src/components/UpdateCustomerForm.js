import * as React from "react";
import TextField from "@mui/material/TextField";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { updateCustomerSchema } from "../utils/schemas.js";
import alertify from "alertifyjs";
import 'alertifyjs/build/css/alertify.css';

function UpdateCustomerForm() {
  const {
    register,
    handleSubmit,
    /*     watch,
     */ formState: { errors },
  } = useForm({
    resolver: yupResolver(updateCustomerSchema),
  });

  const onSubmit = (data) => {
    fetch("customer/"+data.nationalId, {
      method: "PUT",
      body: JSON.stringify(data),
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
      },
    })
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        if(data.status === 400 ){
          alertify.error("BAD REQUEST")
        }
        if(data.status !== 400){
          alertify.success("Customer updated..")
        }
        
        console.log(data)})
      .catch((err) => console.log("err", err));
  };

  return (
    <div className="form_wrapper">
      <h2> Update Customer</h2>
      <form className="customer_form" onSubmit={handleSubmit(onSubmit)}>
        <TextField
          error={errors.nationalId?.message}
          {...register("nationalId", { required: true })}
          id="outlined-error-helper-text"
          label="Please enter your national id"
          defaultValue="12345678912"
          helperText={errors.nationalId?.message}
        />
        <TextField
          error={errors.firstName?.message}
          {...register("firstName", { required: true })}
          id="outlined-error-helper-text"
          label="Please enter your first name"
          defaultValue="John"
          helperText={errors.firstName?.message}
        />
        <TextField
          error={errors.lastName?.message}
          {...register("lastName", { required: true })}
          id="outlined-error-helper-text"
          label="Please enter your last name"
          defaultValue="Smith"
          helperText={errors.lastName?.message}
        />

        <TextField
          error={errors.income?.message}
          {...register("income", { required: true })}
          id="outlined-error-helper-text"
          label="Please enter your income"
          defaultValue="1000"
          helperText={errors.income?.message}
        />
        <TextField
          error={errors.phoneNumber?.message}
          {...register("phoneNumber", { required: true })}
          id="outlined-error-helper-text"
          label="Please enter your phoneNumber"
          defaultValue="05555555555"
          helperText={errors.phoneNumber?.message}
        />

        <button className="form_submit"> Update Customer</button>
      </form>
    </div>
  );
}

export default UpdateCustomerForm;
