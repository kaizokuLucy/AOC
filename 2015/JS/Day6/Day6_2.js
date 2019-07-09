var fs = require('fs');
var input = fs.readFileSync('Day6/input.txt').toString().split("\n");

var grid = Array(1000).fill(0).map(x => Array(1000).fill(0))
var pom = [];
var func = { "turn on": turnOn, "turn off": turnOff, "toggle": toggle };
var action;
for (var i = 0; i < input.length; i++) {
    pom = input[i].match(/\d+/g).map(i => parseInt(i));
    action = input[i].match(/turn off|turn on|toggle/g).toString();
    func[action](grid, pom);
}

console.log(on(grid));


function turnOff(grid, pom) {
    change(grid, pom, -1);
}

function turnOn(grid, pom) {
    change(grid, pom, 1);
}

function toggle(grid, pom) {
    change(grid, pom, 2);
}

function change(grid, pom, val) {
    for (var i = pom[0]; i <= pom[2]; i++) {
        for (var j = pom[1]; j <= pom[3]; j++) {
            grid[i][j] += val;
            grid[i][j] = Math.max(0, grid[i][j]);
            // grid[i][j] = (grid[i][j] + val < 0 ? 0 : grid[i][j] + val);
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
    return grid.map(g => g.reduce((a, b) => a + b, 0)).reduce((a, b) => a + b, 0);
    /*
    cnt = 0; 
    for (var i = 0; i < grid.length; i++) {
         for (var j = 0; j < grid.length; j++) {
             if (grid[i][j]) cnt++;
         }
     }
     return cnt; */
}