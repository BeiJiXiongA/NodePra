var settings = require('../settings');
var Db = require('mongodb').Db;
var Connection = require('mongodb').Connection;
var Server = requite('mongodb').Server;

module.exports = new Db(settings.db, new Server(settings.host, Connection.DEFAULT_PORT,{}));