var fs = require('fs');
var input = fs.readFileSync('Day3/input.txt').toString().split("");

var grid = {};

grid[[0, 0]] = 1;

var currentX = 0;
var currentY = 0;

for (var i = 0; i < input.length; i++) {
    if (input[i] === '<') {
        currentX -= 1;
    } else if (input[i] === '>') {
        currentX += 1;
    } else if (input[i] === '^') {
        currentY += 1;
    } else if (input[i] === 'v') {
        currentY -= 1;
    }
    //  console.log(currentX, currentY);
    grid[[currentX, currentY]] = (grid[[currentX, currentY]] || 0) + 1;;
}

console.log(JSON.stringify(grid));
console.log(Object.keys(grid).length);