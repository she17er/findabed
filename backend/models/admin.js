//NPM Packages
var mongoose = require('mongoose');


var Schema = mongoose.Schema;

var shelterSchema = Schema({
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
    }
}, { timestamps: true });

module.exports = mongoose.model('admin', adminSchema);