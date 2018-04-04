//NPM Packages
const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
const router = express.Router();
// const { check, oneOf, validationResult } = require('express-validator/check');
const MONGO_URL = 'mongodb://krrishdholakia:she17erTeam@ds131258.mlab.com:31258/she17erdb';
//Local Imports
const Shelter = require('../models/shelter');
const db = 'mongodb://she17ers:she17ers@ds131258.mlab.com:31258/she17erdb';

// Use native ES6 promises
mongoose.Promise = global.Promise;
mongoose.connect(db);


router.use(bodyParser.json());
router.use(bodyParser.urlencoded({
  extended: true
}));



router.route('/')
    .get((req, res) => {
      console.log("checking the get request in shelter.js");
    })

// Promise Example
router.get('/getLocations', (req, res) => {
    console.log('getting all books');
    var locations = [];
    Shelter.find({})
      .exec()
      .then((shelter) => {
          console.log("getting them");
          console.log("checking");
          shelter.forEach(shade => {
              locations.push(shade.location);
          });
          res.json(locations);
      })
      .catch((err) => {
        res.send("" + err);
      });
  });


  router.post('/newShelters', (req, res) => {
    Shelter.create(req.body, (err, shelter) => {
        if(err) {
          res.send(""+err);
        } else {
          console.log(shelter);
          res.send(shelter);
        }
    })
  });

  router.post('/updateCapacity/:_id', (req, res) => {
      Shelter.findByIdAndUpdate(req.params._id,
      {
          currCapacity: req.body.currCapacity
      }, function(err, shelter) {
          if (err) {
              res.json(err);
          } else {
              console.log("successful");
              res.json("capacity changed!" + {shelter.currCapacity})
          }
      })
  });

  router.post('/shelter/logout/:_id', (req, res) => {
      Shelter.findByIdAndUpdate({
          _id: req.params._id
      }, {
          login: false
      }, function(err, docs) {
          if (err) {
              res.json(err);
          } else {
              console.log("successful");
              res.json("logged out")
          }
      })
  });

  router.get('/shelter/:_id', (req, res) => {
    Shelter.find({
        _id: req.params._id
    })
      .then((shelter) => res.send(shelter))
      .catch((err) => res.send("" + err))
  });

  router.get('/getShelters', (req, res) => {
    Shelter.find({})
    .exec()
    .then((shelter) => res.send(shelter))
    .catch((err) => {
      res.send("here is the error" + err);
    });
});

module.exports = router;
