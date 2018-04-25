import React, { Component } from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import { Link } from 'react-router-dom';
import Routes from "./Routes";
import "./Signup.css";
import {post} from 'form-urlencoded-post';

export default class Signup extends Component {
  constructor(props) {
    super(props);
        this.state = {
          username: "",
          password: "",
          confirmpassword:"",
          phone:"",
          email:"",
          account_State:"",
          vet_S:"",
          age:"",
          role:"",
          gender:"",
          login: false
        };
  }

  // info = {
  //     username: this.state.username,
  //     password: this.state.password,
  //     phone:this.state.phone,
  //     email:this.state.email,
  //     account_State:this.state.account_State,
  //     vet_S:this.state.vet_S,
  //     age:this.state.age,
  //     role:this.state.role,
  //     gender:this.state.gender,
  //     login: true
  // };

  GenerateNewUser() {
      post('https://she17er.herokuapp.com/api/users/newUsers', this.state).then(res => {console.log(res);})
  }

  validatePassWord() {
      return this.state.confirmpassword === this.state.password && this.state.password.length > 0;
  }

  validateForm() {
    return this.state.email.length > 0 && this.validatePassWord() && this.state.phone.length > 0 && this.state.age.length > 0 && this.state.gender.length > 0 && this.state.role.length > 0;
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  }

  handleSubmit = event => {
      if (this.validateForm()) {
          this.setState({login: true});
          this.GenerateNewUser();
          this.props.history.push("LoggedIn");
          window.alert("Sign up Sucessfully");
      } else {
          window.alert("Please check your inputs");
      }
  }

  render() {
    return (
      <div className="Signup">
          <form onSubmit={this.handleSubmit}>
          <FormGroup controlId="username" bsSize="large">
            <ControlLabel>user</ControlLabel>
            <FormControl
              type="username"
              value={this.state.username}
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
          <FormGroup controlId="vet_S" bsSize="large">
            <ControlLabel>vet_S</ControlLabel>
            <FormControl
              value={this.state.vet_S}
              onChange={this.handleChange}
              type="vet_S"
            />
        </FormGroup>
        <FormGroup controlId="email" bsSize="large">
          <ControlLabel>email</ControlLabel>
          <FormControl
            value={this.state.email}
            onChange={this.handleChange}
            type="email"
          />
      </FormGroup>
          <FormGroup controlId="account_State" bsSize="large">
            <ControlLabel>account_State</ControlLabel>
            <FormControl
              value={this.state.account_State}
              onChange={this.handleChange}
              type="account_State"
            />
        </FormGroup>
        <FormGroup controlId="role" bsSize="large">
          <ControlLabel>Role</ControlLabel>
          <FormControl
            value={this.state.role}
            onChange={this.handleChange}
            type="role"
          />
      </FormGroup>
        <FormGroup controlId="age" bsSize="large">
          <ControlLabel>Age</ControlLabel>
          <FormControl
            value={this.state.age}
            onChange={this.handleChange}
            type="age"
          />
      </FormGroup>
      <FormGroup controlId="gender" bsSize="large">
        <ControlLabel>gender</ControlLabel>
        <FormControl
          value={this.state.gender}
          onChange={this.handleChange}
          type="gender"
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
            Sign Up
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
