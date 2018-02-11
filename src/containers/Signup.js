import React, { Component } from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import { Link } from 'react-router-dom';
import { RadioGroup, RadioButton} from 'react-radio-buttons';
import "./Signup.css";

export default class Signup extends Component {
  constructor(props) {
    super(props);

    this.state = {
      email: "",
      password: "",
      confirmpassword:"",
      phone:"",
      age:"",
    };
  }

  validatePassWord() {
      return this.state.confirmpassword === this.state.password && this.state.password.length > 8;
  }

  validateForm() {
    return this.state.email.length > 0 && this.validatePassWord() && this.state.phone.length > 0 && this.state.age.length > 0;
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
          window.alert("Please check your inputs");
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
          <FormGroup controlId="phone" bsSize="large">
            <ControlLabel>Phone</ControlLabel>
            <FormControl
              value={this.state.phone}
              onChange={this.handleChange}
              type="phone"
            />
        </FormGroup>
        <FormGroup>
        <ControlLabel>Role</ControlLabel>
        <br></br>
        <RadioGroup onChange={ this.onChange } horizontal>
          <RadioButton value="shelter owner">
            Shelter Owner
          </RadioButton>
          <RadioButton value="user">
            User
        </RadioButton>
        </RadioGroup>
    </FormGroup>
        <FormGroup controlId="age" bsSize="large">
          <ControlLabel>Age</ControlLabel>
          <FormControl
            value={this.state.age}
            onChange={this.handleChange}
            type="age"
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
