//NPM Packages
var mongoose = require('mongoose');


var Schema = mongoose.Schema;

var adminSchema = Schema({
    username: {
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

module.exports = mongoose.model('admin', adminSchema);