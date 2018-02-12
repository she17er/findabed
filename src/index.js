import React from "react";
import ReactDOM from "react-dom";
import { Switch, BrowserRouter as Router } from "react-router-dom";
import App from "./App";
import LoggedIn from "./containers/LoggedIn"
import registerServiceWorker from "./registerServiceWorker";
import "./index.css";

function decide(props) {
  const login = props.login;
  if (login) {
      return <App />;
  } else {
      return <LoggedIn />;
  }
}
ReactDOM.render(
  <Router>
     <LoggedIn/>
  </Router>,
  document.getElementById("root")
);
registerServiceWorker();
