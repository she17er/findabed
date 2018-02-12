import React from "react";
import { Route, Switch } from "react-router-dom";

import Home from "./containers/Home";
import Login from "./containers/Login";
import NotFound from "./containers/NotFound";
import Signup from "./containers/Signup";
import LoggedIn from "./containers/LoggedIn";

export default () =>
  <Switch>
    <Route path="/" exact component={Home} />
    <Route path="/login" exact component={Login} />
    <Route path="/signup" exact component={Signup}/>
    <Route path="/LoggedIn" exact component={LoggedIn}/>
    { /* Finally, catch all unmatched routes */ }
    <Route component={NotFound} />
  </Switch>;
