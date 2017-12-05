var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.get('/hello', function(req, res, next) {
	// 输出 JSON 格式
   var response = {
       "first_name":req.query.first_name,
       "last_name":req.query.last_name
   };
   // console.log(response);
   // res.end(JSON.stringify(response));
   res.render('hello', { title: 'submit success' });
   res.send(JSON.stringify(response));
});

router.post('/hello', function(req, res, next){
	var response = {
       "first_name":req.body.first_name,
       "last_name":req.body.last_name
   };
   // console.log(response);
   res.render('hello', { title: 'post submit success' });
   // res.end(JSON.stringify(response));
});

module.exports = router;
