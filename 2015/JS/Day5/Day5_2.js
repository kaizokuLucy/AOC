var fs = require('fs');
var input = fs.readFileSync('Day5/input.txt').toString().split("\n");

var cnt = 0;
for (var i = 0; i < input.length; i++) {
    if (input[i].match(/(..).*\1/g)
        && input[i].match(/(.).\1/)) {
        cnt++;
    }
}

console.log(cnt);