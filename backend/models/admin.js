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

module.exports = mongoose.model('admin', adminSchema);
