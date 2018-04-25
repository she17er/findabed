//NPM Packages models
var mongoose = require('mongoose');
var bcrypt = require('bcrypt');
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
    }
}, { timestamps: true });


userSchema.methods.verifyPassword = function(password) {
    return bcrypt.compareSync(password, this.password);
  };

module.exports = mongoose.model('users', userSchema);

