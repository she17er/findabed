import { createStore, compose, applyMiddleware } from 'redux';
import thunkMiddleware from 'redux-thunk'
import rootReducer from '../reducers';

export function configureStore(initialState) {
    return createStore(
        rootReducer,
        Object.assign({}, initialState, defaultState),
        compose(
            applyMiddleware(thunkMiddleware),
        ),
    );
}
