import React from "react";
import ReactDOM from "react-dom";
import { Switch, BrowserRouter as Router } from "react-router-dom";
import App from "./App";
import LoggedIn from "./containers/LoggedIn"
import registerServiceWorker from "./registerServiceWorker";
import "./index.css";

ReactDOM.render(
  <Router>
     <App/>
  </Router>,
  document.getElementById("root")
);
registerServiceWorker();
