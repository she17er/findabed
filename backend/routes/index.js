// NPM Packages
const express = require('express');
const router = express.Router();

//Local Imports 
const user = require('./user');
const shelter = require('./shelter');
// const admin = require('./admin');

router.use('/users', user);
router.use('/shelter', shelter);
// router.user('/admin', admin);

module.exports = router; 