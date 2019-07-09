console.log('Hello');

var fs = require('fs');
var input = fs.readFileSync('Day1/input.txt').toString().split("");
count = 0;
for (var i = 0; i < input.length; i++) {
    if (input[i] === '(') {
        count++;
    } else {
        count--;
    }
}

console.log(count);