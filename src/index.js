import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter as Router } from "react-router-dom";
import App from "./containers/App";
import LoggedIn from "./containers/LoggedIn"
import { configureStore, history } from './Store/configureStore';
import Routes from './containers/Routes';
import "./index.css";
import Home from "./containers/Home"

ReactDOM.render(
  <Router>
     <App />
  </Router>,
  document.getElementById("root")
);
