var express = require('express');
var path = require('path');
var bodyParser = require('body-parser');
var hbs = require('hbs');
var app = express();

var blogEngine = require('./blog');

app.set('port', process.env.PORT || '3000');
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'html');
app.engine('html', hbs.__express);


// app.use(express.favicon());
// app.use(express.logger('dev'));
app.use(bodyParser.json());
// app.use(express.methodOverride());
// app.use(express.router);

app.use(express.static(path.join(__dirname, 'public')));

// app.get('/', function(req, res) {
//    var body = 'Hello World';
//   res.setHeader('Content-Type', 'text/plain');
//   res.setHeader('Content-Length', body.length);
//   res.end(body);
// });

// app.get('/api', function(request, response) {
//    response.send({name:"张三",age:40});
// });

// var api = require('./routes/api');
// app.get('/api', api.index);


app.get('/', function (req, res) {
    // res.sendFile(__dirname + '/views/index.html');
    // res.render('index');
    res.render('index',{title:"最近文章", entries:blogEngine.getBlogEntries()});
});

app.get('/about', (req, res) => {
    // res.sendFile(__dirname + '/views/about.html');
    // res.render('about');
    res.render('about', {title:"自我介绍"});
});

app.get('/article/:id', (req, res) => {
    // res.sendFile(__dirname + '/views/article.html');
    // res.render('article');
    var entry = blogEngine.getBlogEntry(req.params.id);
   res.render('article',{title:entry.title, blog:entry});
});

app.listen(app.get('port'));

