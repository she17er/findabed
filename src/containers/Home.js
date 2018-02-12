import React, { Component } from "react";
import "./Home.css";

export default class Home extends Component {
  constructor(props) {
    super(props);
    this.state={login: false};
  }
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
