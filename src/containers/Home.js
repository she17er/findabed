import React, { Component } from "react";
import "./Home.css";
import { Link } from "react-router-dom";
import { Nav, Navbar } from "react-bootstrap";
import Routes from "./Routes";
import RouteNavItem from "../components/RouteNavItem";

export default class Home extends Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
    <div className = "NOTLoggedIn">
        {/* <Navbar.Collapse> */}
          <Nav pullRight>
              <RouteNavItem href="/signup">Signup</RouteNavItem>
              <RouteNavItem href="/Login">Login</RouteNavItem>
          </Nav>
      <div className="Home">
        <div className="lander">
          <h1>She17er</h1>
          <p>We help you find shelters</p>
        </div>
      </div>
  </div>
    );
  }
}
