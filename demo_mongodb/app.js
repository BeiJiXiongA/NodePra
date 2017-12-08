var MongoClient = require("mongodb").MongoClient;
var DB_URL = "mongodb://localhost:27017/chm";
var express = require('express');
var bodyParser = require('body-parser');
var path = require('path');
var hbs = require('hbs');
var app = express();

app.set('port', process.env.PORT || '3000');
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'html');
app.engine('html', hbs.__express);

app.get('/', function(req, res){
    MongoClient.connect(DB_URL, function(error, db){
        if (error) {
            console.log('ERROR ' + error);
        }else{
            console.log('连接成功!');
            findRestaurants(db, function(doc){
                db.close();
                res.send(doc);
            });
        }
    });
    // res.send('hello mongodb');
    //数据库连接
    
});

//数据库连接
MongoClient.connect(DB_URL, function(error, db){
    if (error) {
        console.log('ERROR ' + error);
    }else{
        console.log('连接成功!');
        findRestaurants(db, function(doc){
            db.close();
        });
    }
});

//插入
function insertData(db)
{
    var devices = db.collection('chm');
    console.log(devices);
    var data = {"name":"node","age":22,"addr":"nb","addTime":new Date()};
    devices.insert(data,function(error, result){
        if(error)
        {
            console.log('Error:'+ error);
        }else{

            console.log(result.result.n);
            findData(db);
        }
        db.close();
    });
}


//查询
var findRestaurants = function(db, callback) {
   var cursor =db.collection('chm').find( );
   cursor.each(function(err, doc) {
      // assert.equal(err, null);
      if (doc !== null) {
        console.dir(doc);
        callback(doc);
      } else {
        callback();
      }
   });
};

app.listen(app.get('port'));