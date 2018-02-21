// NPM Import
import axios from 'axios';
import { push } from 'react-router-redux';

// Action Creators
import * as types from './types';

/*
 * Login Async Action Creator
 */
export function login() {
  return (dispatch, getState) => { // using Thunks
    const { username, password } = getState().forms.user;
    axios.post('https://she17er.herokuapp.com/api/users/login', {  email, password })
      .then(resp => {
        dispatch(loginGenerator(resp.data.user));
      })
      .catch(err => dispatch(loginGenerator()));
  };
}

/*
 * Registration Async Action Creator
 */
export function register() {
  return (dispatch, getState) => {
    const { username, age, gender, vet_S, contact, account_State, password, role } = getState().forms.user;
    axios.post('https://she17er.herokuapp.com/api/users/newUsers', { username, age, gender, vet_S, contact, account_State, password, role })
      .then(resp => {
        if (resp.data.user) dispatch(push('/login'));
        dispatch(registerGenerator(resp.data.user));
      })
      .catch(err => dispatch(registerGenerator()));
  };
}

/*
 * Logout Async Action Creator
 */
export function logout() {
  return function(dispatch, getState) {
    sessionStorage.removeItem('state');
    dispatch(push('/'));
    axios.get('/api/logout')
      .then(resp => dispatch(logoutGenerator()));
  };
}


/*
 * Helper Action Creator Generators
 */
function loginGenerator(user) {
  return user ? { type: types.LOGIN_SUCCESS, user } : { type: types.LOGIN_FAILED };
}

function registerGenerator(user) {
  return user ? { type: types.REGISTRATION_SUCCESS } : { type: types.REGISTRATION_FAILED };
}

function logoutGenerator() {
  return { type: types.LOGOUT };
}
