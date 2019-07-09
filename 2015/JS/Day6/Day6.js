var fs = require('fs');
var input = fs.readFileSync('Day6/input.txt').toString().split("\n");

var grid = Array(1000).fill(false).map(x => Array(1000).fill(false))
var pom = [];
for (var i = 0; i < input.length; i++) {
    pom = input[i].match(/\d+/g).map(i => parseInt(i));
    if (input[i].startsWith("turn off")) {
        turnOff(grid, pom);
    } else if (input[i].startsWith("turn on")) {
        turnOn(grid, pom);
    } else {
        toggle(grid, pom);
    }
}

console.log(on(grid));


function turnOff(grid, pom) {
    change(grid, pom, false);
}

function turnOn(grid, pom) {
    change(grid, pom, true);
}

function toggle(grid, pom) {
    for (var i = pom[0]; i <= pom[2]; i++) {
        for (var j = pom[1]; j <= pom[3]; j++) {
            grid[i][j] = !grid[i][j];
        }
    }
}

function change(grid, pom, val) {
    for (var i = pom[0]; i <= pom[2]; i++) {
        for (var j = pom[1]; j <= pom[3]; j++) {
            grid[i][j] = val;
        }
    }
}

function print(grid) {
    for (var i = 0; i < grid.length; i++) {
        for (var j = 0; j < grid.length; j++) {
            process.stdout.write(grid[i][j].toString());
        }
        console.log();
    }
}

function on(grid) {
    return grid.map(g => g.filter(e => e === true).length).reduce((a, b) => a + b, 0);
    /*
    cnt = 0; 
    for (var i = 0; i < grid.length; i++) {
         for (var j = 0; j < grid.length; j++) {
             if (grid[i][j]) cnt++;
         }
     }
     return cnt; */
}