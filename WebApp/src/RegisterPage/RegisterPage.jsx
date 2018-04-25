import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';

import { userActions } from '../_actions';

class RegisterPage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            user: {
                username: '',
                age: '',
                gender: '',
                vet_S: '',
                phone: '',
                email: '',
                account_State:'',
                password: '',
                role: '',
            },
            submitted: false
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        const { name, value } = event.target;
        const { user } = this.state;
        this.setState({
            user: {
                ...user,
                [name]: value
            }
        });
    }

    handleSubmit(event) {
        event.preventDefault();

        this.setState({ submitted: true });
        const { user } = this.state;
        const { dispatch } = this.props;
        if (user.age && user.email && user.phone && user.gender && user.vet_S && user.account_State && user.role && user.username && user.password) {
            dispatch(userActions.register(user));
        }
    }

    render() {
        const { registering  } = this.props;
        const { user, submitted } = this.state;
        return (
            <div className="col-md-6 col-md-offset-3">
                <h2>Register</h2>
                <form name="form" onSubmit={this.handleSubmit}>
                    <div className={'form-group' + (submitted && !user.username ? ' has-error' : '')}>
                        <label htmlFor="username">Username</label>
                        <input type="text" className="form-control" name="username" value={user.username} onChange={this.handleChange} />
                        {submitted && !user.username &&
                            <div className="help-block">username is required</div>
                        }
                    </div>
                    <div className={'form-group' + (submitted && !user.age ? ' has-error' : '')}>
                        <label htmlFor="age">Age</label>
                        <input type="text" className="form-control" name="age" value={user.age} onChange={this.handleChange} />
                        {submitted && !user.age &&
                            <div className="help-block">Age is required</div>
                        }
                    </div>
                    <div className={'form-group' + (submitted && !user.gender ? ' has-error' : '')}>
                        <label htmlFor="gender">Gender</label>
                        <input type="text" className="form-control" name="gender" value={user.gender} onChange={this.handleChange} />
                        {submitted && !user.gender &&
                            <div className="help-block">Gender is required</div>
                        }
                    </div>
                    <div className={'form-group' + (submitted && !user.vet_S ? ' has-error' : '')}>
                        <label htmlFor="vet_S">Vet_S</label>
                        <input type="text" className="form-control" name="vet_S" value={user.vet_S} onChange={this.handleChange} />
                        {submitted && !user.vet_S &&
                            <div className="help-block">vet_S is required</div>
                        }
                    </div>
                    <div className={'form-group' + (submitted && !user.email ? ' has-error' : '')}>
                        <label htmlFor="email">Email</label>
                        <input type="text" className="form-control" name="email" value={user.email} onChange={this.handleChange} />
                        {submitted && !user.email &&
                            <div className="help-block">Email is required</div>
                        }
                    </div>
                    <div className={'form-group' + (submitted && !user.phone ? ' has-error' : '')}>
                        <label htmlFor="phone">Phone</label>
                        <input type="text" className="form-control" name="phone" value={user.phone} onChange={this.handleChange} />
                        {submitted && !user.phone &&
                            <div className="help-block">Phone is required</div>
                        }
                    </div>
                    <div className={'form-group' + (submitted && !user.account_State ? ' has-error' : '')}>
                        <label htmlFor="account_State">Account_State</label>
                        <input type="text" className="form-control" name="account_State" value={user.account_State} onChange={this.handleChange} />
                        {submitted && !user.account_State &&
                            <div className="help-block">Account State is required</div>
                        }
                    </div>
                    <div className={'form-group' + (submitted && !user.password ? ' has-error' : '')}>
                        <label htmlFor="password">Password</label>
                        <input type="password" className="form-control" name="password" value={user.password} onChange={this.handleChange} />
                        {submitted && !user.password &&
                            <div className="help-block">Password is required</div>
                        }
                    </div>
                    <div className={'form-group' + (submitted && !user.role ? ' has-error' : '')}>
                        <label htmlFor="role">Role</label>
                        <input type="text" className="form-control" name="role" value={user.role} onChange={this.handleChange} />
                        {submitted && !user.role &&
                            <div className="help-block">Role is required</div>
                        }
                    </div>
                    <div className="form-group">
                        <button className="btn btn-primary">Register</button>
                        {registering &&
                            <img src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" />
                        }
                        <Link to="/" className="btn btn-link">Cancel</Link>
                    </div>
                </form>
            </div>
        );
    }
}

function mapStateToProps(state) {
    const { registering } = state.registration;
    return {
        registering
    };
}

const connectedRegisterPage = connect(mapStateToProps)(RegisterPage);
export { connectedRegisterPage as RegisterPage };
