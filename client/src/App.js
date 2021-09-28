import "./App.css";
import * as React from "react";
import AddCustomerForm from "./components/AddCustomerForm";
import UpdateCustomerForm from "./components/UpdateCustomerForm";
import DeleteCustomerForm from "./components/DeleteCustomer";
import ApplyForLoan from "./components/ApplyForLoan";
function App() {
  return (
    <div className="App">
      <AddCustomerForm />
      <UpdateCustomerForm />
      <DeleteCustomerForm />
      <ApplyForLoan />
    </div>
  );
}

export default App;
