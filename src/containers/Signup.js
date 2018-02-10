import React, { Component } from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import { Link } from 'react-router-dom'
import "./Signup.css";

export default class Signup extends Component {
  constructor(props) {
    super(props);

    this.state = {
      email: "",
      password: ""
    };
  }

  validatePassWord() {
      return this.state.confirmpassword === this.state.password && this.state.password.length > 8
  }

  validateForm() {
    return this.state.email.length > 0 && this.state.password.length > 0
    && this.validatePassWord();
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  }

  handleSubmit = event => {
    event.preventDefault();
  }

  handleSignUp = event => {
      if (this.validateForm()) {
          console.log("Sign up Successfully");
          window.alert("Sign up Sucessfully");
      } else {
          window.alert("Please check your passwords");
      }
  }


  render() {
    return (
      <div className="Signup">
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
          <FormGroup controlId="confirmpassword" bsSize="large">
            <ControlLabel>Confirm Password</ControlLabel>
            <FormControl
              value={this.state.confirmpassword}
              onChange={this.handleChange}
              type="password"
            />
          </FormGroup>
          <Button
            block
            bsSize="large"
            disabled={!this.validateForm()}
            type="submit"
            onClick={(e) => this.handleSignUp(e)}
          >
            Sign Up
          </Button>
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
