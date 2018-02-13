import fetch from 'isomorphic-fetch';
import FormData from 'form-data';

import * as types from './types';

export function logoutUser() {
    return {
        type: types.LOGOUT_USER
    }
}
