// NPM Imports
const mongoose = require('mongoose');
const express = require('express');
const path = require('path');
const app = express();

// Local Imports & Constants
// load env vars
require('dotenv').config();
const PORT = process.env.PORT || 3000;
const api = require('./backend/routes');

// Connect to MongoDB
mongoose.connect(process.env.MONGOLAB_URI);
mongoose.Promise = global.Promise;
//app.use(express.static(path.join(__dirname, 'public')));


// Route API Calls to seperate router
app.use('/api', api);
app.listen(PORT, error => {
  error
    ? console.error(error)
    : console.info(`==> 🌎 Listening on port ${PORT}. Visit http://localhost:${PORT}/ in your browser.`);
});


module.exports = app;
