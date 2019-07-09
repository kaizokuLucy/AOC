console.log('Hello');

var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().split("");
count = 0;
for(var i=0; i < input.length; i++) {
    if(input[i] === '(') {
        count++;
    } else {
        count--;
    }
    if(count === -1) {
        var c = i+1;
        console.log(c);
        break;
    }
}    


console.log(count);