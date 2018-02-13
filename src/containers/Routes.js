import React from "react";
import { Route, Switch } from "react-router-dom";

import Home from "./Home";
import Login from "./Login";
import NotFound from "./NotFound";
import Signup from "./Signup";
import LoggedIn from "./LoggedIn";

export default () =>
  <Switch>
    <Route path="/" exact component={Home} />
    <Route path="/login" exact component={Login} />
    <Route path="/signup" exact component={Signup}/>
    <Route path="/LoggedIn" exact component={LoggedIn}/>
    { /* Finally, catch all unmatched routes */ }
    <Route component={NotFound} />
  </Switch>;
