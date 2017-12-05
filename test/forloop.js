
var fs = require('fs');
var files = ['a.txt', 'b.txt', 'c.txt'];


// for(var i = 0; i < files.length; i++){
//     fs.readFile(files[i], 'utf-8', function(err, contents) {
//         console.log(files[i] + ': '+contents);
//         // console.log(files);
//         // console.log(i);
//         // console.log(files[i]);
//     });
// }

//函数式变成
// for(var i = 0; i < files.length; i++){
// 	(function(i){
// 		fs.readFile(files[i], 'utf-8', function(err, contents) {
// 			console.log(files[i] + ': ' + contents);
// 		});
// 	})(i);
// }

//forEach
files.forEach(function(filename) {
	fs.readFile(filename, 'utf-8', function(err, contents) {
		console.log(filename + ': ' + contents);
	});
});


