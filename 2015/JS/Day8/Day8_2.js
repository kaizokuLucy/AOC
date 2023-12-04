var fs = require('fs');
var input = fs.readFileSync('Day8/input.txt').toString().split("\n");

var num_of_char = input.map(x => x.length).reduce((a, b) => a + b, 0);
// var a = ['""', '"abc"', '"aaa\\"aaa"', '"\\x27"'];
var num_of_deeval = input.map(x => deeval(x).length).reduce((a,b) => a + b, 0);

console.log(num_of_deeval - num_of_char);

function deeval(input) {
    var output_string = '"';
    for (var i of input) {
        switch (i) {
            case '"':
                output_string += '\\"';
                break;
            case '\\':
                output_string += '\\\\';
                break;
            default:
                output_string += i;
                break;
        }
    }
    output_string += '"';
    return output_string;
}