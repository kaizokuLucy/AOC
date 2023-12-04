var fs = require('fs');
var characters  = fs.readFileSync('Day8/input.txt').toString().split("\n");

var num_of_char = characters.map(x => x.length).reduce((a,b) => a + b, 0);
var num_of_mem  = characters.map(x => eval(x).length).reduce((a,b) => a + b, 0);

console.log(num_of_char - num_of_mem);