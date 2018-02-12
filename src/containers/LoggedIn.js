import React, { Component } from "react";
import "./LoggedIn.css";
import { Link } from "react-router-dom";
import { Nav, Navbar } from "react-bootstrap";
import Routes from "../Routes";
import RouteNavItem from "../components/RouteNavItem";

export default class LoggedIn extends Component {
  constructor(props) {
      super(props);
      this.state = {login: true};
  }
  render() {
    return (
        <div className = "App container">
            <Navbar fluid collapseOnSelect>
              <Navbar.Header>
                <Navbar.Brand>
                  <Link to="/">She17er</Link>
                </Navbar.Brand>
                <Navbar.Toggle />
              </Navbar.Header>
              <Navbar.Collapse>
                <Nav pullRight>
                  <RouteNavItem href="/signup">Signup</RouteNavItem>
                  <RouteNavItem href="/">Logout</RouteNavItem>
                </Nav>
              </Navbar.Collapse>
            </Navbar>
            <Routes />
      </div>
    );
  }
}
