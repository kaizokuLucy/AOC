var fs = require('fs');
var input = fs.readFileSync('Day2/input.txt').toString().split("\n");

console.log(input.length);
var sum = 0;
for (var i = 0; i < input.length; i++) {
    var dim = input[i].split("x");
    dim.sort((a, b) => a - b);
    sum += 2 * dim[0] + 2 * dim[1];
    sum += dim.reduce((a, b) => a * b);
}
console.log(sum);
