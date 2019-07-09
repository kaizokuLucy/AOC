var fs = require('fs');
var input = fs.readFileSync('Day5/input.txt').toString().split("\n");

var cnt = 0;
for (var i = 0; i < input.length; i++) {
    if ((input[i].match(/a|e|i|o|u/g) || []).length >= 3
        && input[i].match(/(.)\1/)
        && !input[i].match(/ab|cd|pq|xy/)) {
        cnt++;
    }
}

console.log(cnt);