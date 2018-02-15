import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter as Router } from "react-router-dom";
import App from "./App";
import LoggedIn from "./containers/LoggedIn"
import { configureStore, history } from './store/configureStore';
import Root from './containers/Routes';
import "./index.css";

ReactDOM.render(
  <Router>
     <App />
  </Router>,
  document.getElementById("root")
);
