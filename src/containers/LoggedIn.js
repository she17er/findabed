import React, { Component } from "react";
import "./LoggedIn.css";
import { Link } from "react-router-dom";
import { Nav, Navbar } from "react-bootstrap";
import Routes from "./Routes";
import RouteNavItem from "../components/RouteNavItem";

export default class LoggedIn extends Component {
  render() {
    return (
      //   <div className = "LoggedIn">
        <div className="Home">
          <div className="lander">
            <h1>She17er</h1>
            <p>We help you find shelters</p>
          </div>
      </div>
      //       <Navbar fluid collapseOnSelect>
      //         <Navbar.Header>
      //           <Navbar.Brand>
      //             <Link to="/">She17er</Link>
      //           </Navbar.Brand>
      //           <Navbar.Toggle />
      //         </Navbar.Header>
      //         <Navbar.Collapse>
      //           <Nav pullRight>
      //             <RouteNavItem href="/signup">Signup</RouteNavItem>
      //             <RouteNavItem href="/">Logout</RouteNavItem>
      //           </Nav>
      //         </Navbar.Collapse>
      //       </Navbar>
      //       <Routes />
      // </div>
    );
  }
}
