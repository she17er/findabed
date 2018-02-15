import * as types from './types.js';
import axios from 'axios';
import { push } from 'react-router-redux';


// export function updateCurrentUser(id) {
//   return (dispatch, getState) => {
//     dispatch(push(`/volunteers/${id}`));
//     dispatch(currentUser(id));
//   };
// }

function currentUser(id) {
  return {
    type: types.UPDATE_CURRENT_USERS,
    id
  };
}

export function updateUserStatus(role) {
  return { type: types.USER_STATUS, role};
}


export function loadAllUsers(id) {
  return dispatch => {
    axios.get('https://she17er.herokuapp.com/api/users/getUsers')
      .then(({ data }) => {
        dispatch(allUsers(data.users));
        if (id) dispatch(currentUser(id));
      });
  };
}

function allUsers(all) {
  return {
    type: types.LOAD_ALL_USERS,
    all
  };
}


// export function loadNewVolunteers() {
//   return dispatch => {
//     axios.get('/api/users?type=new')
//       .then(({ data }) => {
//         dispatch(newVolunteers(data.users));
//       });
//   };
// }
// export function loadPendingVolunteers() {
//   return dispatch => {
//     axios.get('/api/users?type=pending')
//       .then(({ data }) => {
//         dispatch(pendingVolunteers(data.users));
//       });
//   };
// }
//
// export function approvePendingVolunteer(id) {
//   return dispatch => {
//     const body = { 'bio': {'role': 'volunteer'} };
//     axios.put(`/api/users/${id}`, body)
//       .then(({ data }) => {
//         if (data.user.bio.role === 'volunteer') {
//           dispatch(approveVolunteer(id));
//         }
//       });
//   };
// }

// export function onLoad() {
//   return dispatch => {
//     axios.get('/api/users?type=pending')
//       .then(({ data }) => {
//         dispatch(loadPendingVolunteers(data.users));
//       });
//     axios.get('/api/users?type=new')
//       .then(({ data }) => {
//         dispatch(loadNewVolunteers(data.users));
//       });
//     axios.get('/api/events?type=new')
//       .then(({ data }) => {
//         dispatch(loadNewEvents(data.events));
//       });
//   };
// }

function pendingUser(pending) {
  return {
    type: types.LOAD_PENDING_USERS,
    pending
  };
}

function newUser(newest) {
  return {
    type: types.LOAD_NEWEST_USERS,
    newest
  };
}

function approveUser(id) {
  return {
    type: types.APPROVE_PENDING_USERS,
    id
  };
}
