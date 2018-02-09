//NPM Packages
const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
const { check, oneOf, validationResult } = require('express-validator/check');

//Local Imports
const User = require('../models/user');
const db = process.env.MONGOLAB_URI;
const Shelter = require('../models/shelter');

// Use native ES6 promises
mongoose.Promise = global.Promise;
mongoose.connect(db);


app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
  extended: true
}));

app.post('/newUsers', (req, res) => {
    console.log('trying new things');
    User.create(req.body, (err, user) => {
      if(err) {
        res.send(""+err);
      } else {
        console.log(user);
        res.send(user);
  }})
 } );

app.get('/getUsers', (req, res) => {
    User.find({}) 
    .exec()
    .then((user) => res.send(user))
    .catch((err) => {
      res.send("" + err);
    });
})


/**
 * this is very simple searching where keyword has to 
 * equal the ENTIRE shelter name
 * Shelter searching should be more robust to 
 * return all shelters which contain the searched
 * phrase 
 */
app.get('/searchKeyword', (req, res) => {
  var keyword = req.query.keyword
  Shelter.find({
    name : keyword
  })
  .then((user) => res.send(user))
  .catch((err) => {
    res.send("" + err); 
  })
})

app.get('/userStatus/:_id' , (req, res) => {
    User.find({
        _id : req.params._id
    })
    .then((user) => res.send(user.role))
    .catch((err) => res.send("" + err))
})

app.post('/updatePassword/:_id', (req, res) => {
  User.findByIdAndUpdate({
    _id: req.params._id
}, {
    password: req.body.password
}, function(err, docs) {
    if (err) {
        res.json(err);
    } else {
        console.log("successful");
        res.json("password updated!")
    }
})
});

module.exports = express.Router(); 