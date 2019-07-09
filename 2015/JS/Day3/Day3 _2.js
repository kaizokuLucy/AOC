var fs = require('fs');
var input = fs.readFileSync('Day3/input.txt').toString().split("");

var input2 = [];
var input3 = [];
for (var i = 0; i < input.length; i++) {
    if (i % 2 == 0) {
        input2.push(input[i]);
    } else {
        input3.push(input[i]);
    }
}

console.log(JSON.stringify(input2));
console.log(JSON.stringify(input3));

var grid = {};
grid[[0, 0]] = 2;

move(grid, input2);
move(grid, input3);

console.log(JSON.stringify(grid));
console.log(Object.keys(grid).length);

function move(grid, input) {
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
}





