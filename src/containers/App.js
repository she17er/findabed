import React, { Component } from "react";
import { Link } from "react-router-dom";
import { Nav, Navbar } from "react-bootstrap";
import Routes from "./containers/Routes";
import RouteNavItem from "./components/RouteNavItem";
import "./App.css";

class App extends Component {
  constructor(props) {
      super(props);
  }

  // componentDidMount() {
  //     fetch('https://she17er.herokuapp.com/api/users/getUsers').then(results => {return results.json();}).then(data => {this.setState({login: data.login});});
  //   }

  render() {
    return (
      <div className="App container">
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
                <RouteNavItem href="/login">Login</RouteNavItem>
            </Nav>
          </Navbar.Collapse>
            </Navbar>
        <Routes />
      </div>
    );
  }
}

export default App;
