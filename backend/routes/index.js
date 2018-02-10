// NPM Packages
const express = require('express');
const router = express.Router();
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
const { check, oneOf, validationResult } = require('express-validator/check');
//Local Imports 
const user = require('./user');
const shelter = require('./shelter');
const db = 'mongodb://she17ers:she17ers@ds131258.mlab.com:31258/she17erdb';

// const admin = require('./admin');

mongoose.Promise = global.Promise;
mongoose.connect(db);


router.use(bodyParser.json());
router.use(bodyParser.urlencoded({
  extended: true
}));


router.route('/')
    .get((req, res) => {
        console.log("reached the '/' "); 
    })

router.use('/users', user);
router.use('/shelter', shelter);
// router.user('/admin', admin);

module.exports = router; 