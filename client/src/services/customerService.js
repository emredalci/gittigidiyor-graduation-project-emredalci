import fetch from "unfetch";

const HEADERS = {
  "Content-Type": "application/json",
  "Access-Control-Allow-Origin": "*",
  "Access-Control-Allow-Headers": "*",
};

const checkStatus = (response) => {
  if (response.ok) {
    console.log(response);
    return response.json();
  } else {
    console.log(response);
    return response;
  }
};

export const addNewCustomer = (customerDTO) =>
  fetch("customer", {
    headers: HEADERS,
    method: "POST",
    body: JSON.stringify(customerDTO),
  }).then(checkStatus);

export const updateCustomer = (customerUpdateDTO, nationalId) =>
  fetch("http://localhost:9191/customer/$(nationalId)", {
    headers: {
      "Content-Type": "application/json",
    },
    method: "PUT",
    body: customerUpdateDTO,
  }).then(checkStatus);

export const deleteCustomer = (nationalId) =>
  fetch("http://localhost:9191/customer//$(nationalId)", {
    method: "DELETE",
  }).then(checkStatus);

export const applyForLoan = (nationalId) =>
  fetch("http://localhost:9191/customer//$(nationalId)").then(checkStatus);

export const getAllCustomers = () =>
  fetch("customer", {
    headers: HEADERS,
    method: "GET",
  }).then(checkStatus);
