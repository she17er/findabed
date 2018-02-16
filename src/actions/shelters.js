import * as types from './types.js';
import axios from 'axios';
import { push } from 'react-router-redux';

export function updateCurrentShelter(id) {
    return dispatch => {
        const body = {'currCapacity' : currCapacity};
        axios.post('https://she17er.herokuapp.com/api/shelter/updateCapacity/:_id', body).then(resp => {
            dispatch()
        })

}
