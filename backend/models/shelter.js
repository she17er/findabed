//NPM Packages
var mongoose = require('mongoose');


var Schema = mongoose.Schema;

var shelterSchema = Schema({
    name: {
        type: String,
        required: true
    },
    location: {
        type: String,
        required: true
    },
    currCapacity: {
        type: Number,
        required: true
    },
    maxCapacity: {
        type: Number,
        required: true
    },
    acceptedTypes: {
        type: Array,
        required: true
    },
    acceptedAge: {
        type: Array,
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

var shelter = module.exports = mongoose.model('shelters', shelterSchema);
