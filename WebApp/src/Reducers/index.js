import { combineReducers } from 'redux';
import { reducer } from 'react-redux-sweetalert';
import { routerReducer as router } from 'react-router-redux';
import Auth from './Auth'
export default combineReducers({
    Auth,
});
