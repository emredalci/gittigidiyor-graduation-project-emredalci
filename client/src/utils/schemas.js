import * as yup from "yup";

/* Add Customer */
export const addCustomerSchema = yup
  .object({
    firstName: yup
      .string()
      .max(40)
      .required(),
    lastName: yup
      .string()
      .max(40)
      .required(),
    nationalId: yup.string().required(),
    income: yup.number().required(),
    phoneNumber: yup
      .string()
  })
  .required();

/* Apply For Loan */
export const appyForLoanSchema = yup
  .object({
    nationalId: yup
      .string().required()
  }
  ).required();

/*Delete customer*/
export const deleteCustomerSchema = yup
  .object({
    nationalId: yup
      .string().required()
  }
  ).required();

/*Update Customer*/
export const updateCustomerSchema = yup
  .object({
    firstName: yup
      .string()
      .max(40)
      .required(),
    lastName: yup
      .string()
      .max(40)
      .required(),
    nationalId: yup.string().required(),
    income: yup.number().required(),
    phoneNumber: yup
      .string()
  }
  ).required();