import React, { Component } from "react";
import "./LoggedIn.css";
import { Link } from "react-router-dom";
import { Nav, Navbar } from "react-bootstrap";
import Routes from "./Routes";
import RouteNavItem from "../components/RouteNavItem";

export default class LoggedIn extends Component {
  render() {
    return (
        <div className="Home">
          <div className="lander">
            <h1>She17er</h1>
            <p>We help you find shelters</p>
          </div>
      </div>
    );
  }
}
