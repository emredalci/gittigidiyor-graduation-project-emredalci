import * as React from "react";
import TextField from "@mui/material/TextField";
import fetch from "unfetch";
import { appyForLoanSchema } from "../utils/schemas.js";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";

function ApplyForLoanForm() {

  const {
    register,
    handleSubmit,
    /*     watch,
     */ formState: { errors },
  } = useForm({
    resolver: yupResolver(appyForLoanSchema),
  });


  const onSubmit = (data) => {
    fetch("customer/"+data.nationalId, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
      },
    })
      .then((res) => {
        return console.log(res);
      })
      .catch((err) => console.log("err", err));
  };


  return (
    <div className="form_wrapper">
      <h2> Loan </h2>
      <form className="customer_form" onSubmit={handleSubmit(onSubmit)}>
        <TextField
          error={errors.nationalId?.message}
          {...register("nationalId", { required: true })}
          id="outlined-error-helper-text"
          label="Please enter the national Id of customer"
          defaultValue="25400019922"
          helperText={errors.nationalId?.message}
        />
        <button className="form_submit" type="submit"> Apply For Loan</button>
      </form>
    </div>
  );
}

export default ApplyForLoanForm;
