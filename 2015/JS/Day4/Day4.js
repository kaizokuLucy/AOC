var key = "iwrupvqb";

var md5 = require('md5');

//console.log(md5('abcdef609043'));

var i = 0;
var input = key + i;
var hash = md5(input);

while (!hash.startsWith("00000")) {
    i++;
    input = key + i;
    hash = md5(input);
}

console.log(i);