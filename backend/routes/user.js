//NPM Packages routes
const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
const bcrypt = require('bcrypt');
const router = express.Router();
const passport = require('passport');
const morgan = require('morgan');
const LocalStrategy = require('passport-local').Strategy;
const { check, oneOf, validationResult } = require('express-validator/check');

//Local Imports
const User = require('../models/user');
// const db = process.env.MONGOLAB_URI;
const Shelter = require('../models/shelter');

// // Use native ES6 promises
// mongoose.Promise = global.Promise;
// mongoose.connect(db);


// router.use(bodyParser.json());
// router.use(bodyParser.urlencoded({
//   extended: true
// }));
router.use(morgan('dev'));
router.use(bodyParser.json());
router.use(bodyParser.urlencoded({ extended: true }));
router.use(passport.initialize());
router.use(passport.session());

passport.use(new LocalStrategy(
    function(username, password, done) {
      User.findOne({ "username": username }, function (err, user) {
        console.log(user);
        if (err) {
            console.log("error");
            return done(err); }
        if (!user) { return done(null, false); }
        if (!user.verifyPassword(password)) {
            console.log("checking hoexxoxoxoox");
            return done(null, false); }
        return done(null, user);
      });
    }
  ));

  passport.serializeUser((user, done) => {
    return done(null, user._id);
  });

  passport.deserializeUser((id, done) => {
    User.findById(id, (err, user) => {
      return done(err, user);
    });
  });

  // Login Route
router.post('/login', (req, res) => {
    passport.authenticate('local', (errors, user) => {
      req.logIn(user, () => {
        if (errors) return res.status(500).json({ errors });
        return res.status(user ? 200 : 400).json(user ? { user } : { errors: "Login Failed" });
      });
    })(req, res);
  });

  // Logout Route
  router.get('/logout', (req, res) => {
    req.logout();
    return res.status(200).json({ logout: 'success' });
  });

router.post('/newUsers', (req, res) => {
    const newUserModel = req.body;
    newUserModel.password = bcrypt.hashSync(newUserModel.password, 10);
    User.create(req.body, (err, user) => {
      if(err) {
        res.send(""+err);
      } else {
        console.log(user);
        res.send(user);
  }})
 });

 router.route('/')
    .get((req, res) => {
      console.log("checking the get request in user.js");
  });

router.get('/getUsers', (req, res) => {
    User.find({})
    .exec()
    .then((user) => res.send(user))
    .catch((err) => {
      res.send("" + err);
    });
});


/**
 * this is very simple searching where keyword has to
 * equal the ENTIRE shelter name
 * Shelter searching should be more robust to
 * return all shelters which contain the searched
 * phrase
 */
router.get('/searchKeyword', (req, res) => {
  var keyword = req.query.keyword
  Shelter.find({
    name : keyword
  })
  .then((user) => res.send(user))
  .catch((err) => {
    res.send("" + err);
  })
});

router.post('/user/logout/:id', (req, res) => {
    User.findByIdAndUpdate({
        _id: req.params._id
    }, {
        login: false
    }, function(err, docs) {
        if (err) {
            res.json(err);
        } else {
            console.log("successful");
            res.json("logged out!")
        }
    })
});

router.get('/getUserNames', (req, res) => {
  let allUserName = []
  User.find({}, {username: 1, _id: 0})
  .then((user) => res.send(user))
  .catch((err) => res.send(err))
});

router.post('/updatePassword/:_id', (req, res) => {
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

module.exports = router;


//var hash = bcrypt.hashSync(plainPassword, 10);
