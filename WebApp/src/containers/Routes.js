import React from "react";
import { Route, Switch } from "react-router-dom";

import Home from "./Home";
import Login from "./Login";
import Signup from "./Signup";
import LoggedIn from "./LoggedIn";

export default function Routes() {
  return (
  <Switch>
    <Route path="/" exact component={Home} />
    <Route path="/login" exact component={Login} />
    <Route path="/signup" exact component={Signup}/>
    <Route path="/loggedIn" exact component={LoggedIn}/>
  </Switch>
  );
}
