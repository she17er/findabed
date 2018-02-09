//NPM Packages
const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
const router = express.Router();
// const { check, oneOf, validationResult } = require('express-validator/check');

//Local Imports
const Shelter = require('../models/shelter');
const db = process.env.MONGOLAB_URI;

// Use native ES6 promises
mongoose.Promise = global.Promise;
mongoose.connect(db);


app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
  extended: true
}));

// Promise Example
app.get('/getLocations', (req, res) => {
    console.log('getting all books');
    var locations = [];
    Shelter.find({})
      .exec()
      .then((shelter) => {
          shelter.forEach(shade => {
              locations.push(shade.location);
          });
          res.json(locations);
      })
      .catch((err) => {
        res.send("" + err);
      });
  });

  // app.post('/updateOldShelters', (req, res) => {
  //   var name = req.body.name;
  //   Shelter.find({name: name}).then((shelter) => res.
  // });

  app.post('/newShelters', (req, res) => {
    Shelter.create(req.body, (err, shelter) => {
        if(err) {
          res.send(""+err);
        } else {
          console.log(shelter);
          res.send(shelter);
        }
    })
  });

  app.post('/updateCapacity/:_id', (req, res) => {
      Shelter.findByIdAndUpdate({
          _id: req.params._id
      }, {
          currCapacity: req.body.newCapacity
      }, function(err, docs) {
          if (err) {
              res.json(err);
          } else {
              console.log("successful");
              res.json("capacity changed!")
          }
      })
  });

  app.get('/shelter/:_id', (req, res) => {
    Shelter.find({
        _id: req.params._id
    })
      .then((shelter) => res.send(shelter))
      .catch((err) => res.send("" + err))
  });

module.exports = express.Router(); 