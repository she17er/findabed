//NPM Packages
var mongoose = require('mongoose');

var Schema = mongoose.Schema;

var userSchema = Schema({
    username: {
        type: String,
        required: true
    },
    age: {
        type: Number,
        required: true
    },
    gender: {
        type: String,
        required: true
    },
    vet_S: {
        type: String,
        required: true
    },
    contact: {
        phone: {
            type: String,
            required: true
        },
        email: {
            type: String,
            required: false
        }
    },
    account_State: {
        type: String,
        required: true
    },
    password: {
        type: String,
        required: true
    },
    role: {
        type: String,
        required: true
    }, 
    login: {
        type: Boolean, 
        required: true
    }
}, { timestamps: true });

module.exports = mongoose.model('users', userSchema);
