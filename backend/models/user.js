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
<<<<<<< HEAD
    },
    login: {
        type: Boolean,
=======
    }, 
    login: {
        type: Boolean, 
>>>>>>> 840b98778d728d7383655465f933ff75c62fe027
        required: true
    }
}, { timestamps: true });

module.exports = mongoose.model('users', userSchema);
