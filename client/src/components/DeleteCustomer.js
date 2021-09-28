import * as React from "react";
import TextField from "@mui/material/TextField";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { deleteCustomerSchema } from "../utils/schemas.js";
import alertify from "alertifyjs";
import 'alertifyjs/build/css/alertify.css';

function DeleteCustomerForm() {

  const {
    register,
    handleSubmit,
    /*     watch,
     */ formState: { errors },
  } = useForm({
    resolver: yupResolver(deleteCustomerSchema),
  });

  

  const onSubmit = (data) => {
    fetch("customer/" + data.nationalId, {
      method: "DELETE",
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
          alertify.success("Customer deleted..")
        }
        
        
        
        console.log(data)})
      .catch((err) => console.log("err", err));
  };

  return (
    <div className="form_wrapper">
      <h3> Delete Customer </h3>
      <form className="customer_form" onSubmit={handleSubmit(onSubmit)}>
        <TextField
          error={errors.nationalId?.message}
          {...register("nationalId", { required: true })}
          id="outlined-error-helper-text"
          label="Please enter the national Id of customer"
          defaultValue="25400019922"
          helperText={errors.nationalId?.message}
        />

        <button className="form_submit" type="submit">Delete Customer</button>
      </form>
    </div>
  );
}

export default DeleteCustomerForm;
