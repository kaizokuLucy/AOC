var fs = require('fs');
var input = fs.readFileSync('Day2/input.txt').toString().split("\n");

console.log(input.length);
var sum = 0;
var min = 0;
for (var i = 0; i < input.length; i++) {
    min = 0;
    var [l, w, h] = input[i].split("x");
    var [a, b, c] = [(l * w), (w * h), (h * l)];
    sum += (a + b + c) * 2;

    min = Math.min(a, b, c);
    sum += min;
}
console.log(sum);
