var scope = 'global';
var f = function(){
    var scope='f';
    console.log(scope);
 //   var scope = 'f';
};
f();
