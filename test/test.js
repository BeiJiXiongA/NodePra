/*
这个例 中，通过 f2 调用的 f1 在 找 scope 定 时，找到的是 作用域中定  的 scope 变量，而不是 f2 中定 的 scope 变量。这说 了作用域的  关系不是在调用 时 定的，而是在定 时 定的
定义f1 的时候 scope='top',所以f1输出top
*/

var scope = 'top';
var f1 = function(){
    console.log(scope);
};
f1();

var f2=function(){
    var scope = 'f2';
    f1();
};
f2();
