import React, { Component } from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import { Link } from 'react-router-dom';
import "./Login.css";

export default class Login extends Component {
  constructor(props) {
    super(props);

    this.state = {
      email: "",
      password: "",
      login: false
    };
  }

  // getUser() {
  //     return fetch('https://she17er.herokuapp.com/api/users/getUsers').then((response)=>response.json()).then((responseJson) => {return responseJson;
  //     }).catch((error) => {
  //         console.error(error);
  //     });
  // }

  validateForm() {
    return this.state.email.length > 0 && this.state.password.length > 0;
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  }

  handleSubmit = event => {
    this.setState({login: true});
    this.props.history.push("LoggedIn");
  }

  // ValidateUser() {
  //     return this.getUser().email.stringify() === this.state.email && this.getUser().password.stringify() === this.state.password;
  // }


  render() {
    return (
      <div className="Login">
        <form onSubmit={this.handleSubmit}>
          <FormGroup controlId="email" bsSize="large">
            <ControlLabel>Email</ControlLabel>
            <FormControl
              autoFocus
              type="email"
              value={this.state.email}
              onChange={this.handleChange}
            />
          </FormGroup>
          <FormGroup controlId="password" bsSize="large">
            <ControlLabel>Password</ControlLabel>
            <FormControl
              value={this.state.password}
              onChange={this.handleChange}
              type="password"
            />
          </FormGroup>
          <Link to="LoggedIn">
          <Button
            block
            bsSize="large"
            disabled={!this.validateForm()}
            type="submit"
            onClick={(e) => this.handleSubmit(e)}
          >
            Login
          </Button>
      </Link>
          <br></br>
          <Link to="/">
          <Button
            block
            bsSize="large"
            type="submit"
          >
              Cancel
          </Button>
      </Link>
        </form>
      </div>
    );
  }
}
