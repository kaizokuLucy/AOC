var fs = require('fs');
var input = fs.readFileSync('Day7/input.txt').toString().split("\n");

var command = [];
var circuit = {};
var op_list = [];
for (var i = 0; i < input.length; i++) {
    command = input[i].split('->').map(e => e.trim());
    if (input[i].match(/^\d+ -> \w*/g)) {
        var br = parseInt(command[0]);
        circuit[command[1]] = br;
    } else {
        var bla = command[0].split(" ");
        bla.push(command[1]);
        bla = bla.map(e => isNaN(parseInt(e)) ? e : parseInt(e));
        op_list.push(bla);
    }
}
return;
console.log(JSON.stringify(circuit));

// Part 2
circuit['b'] = 3176;

while (op_list.length > 0) {
    for (var i = 0; i < op_list.length; i++) {
       // console.log("naredba: " + op_list[i]);
        var current_op = op_list[i];
        if (current_op.includes('NOT')) {
            if (not_f(current_op[1], current_op[2])) {
                if(circuit[current_op[2]] === 65535) {
                    console.log(current_op);
                    console.log(JSON.stringify(circuit[current_op[1]]));
                    console.log(JSON.stringify(circuit[current_op[2]]));
                }
                op_list.splice(i, 1);
                i--;
            }
            continue;
        } else if (current_op.includes('AND')) {
            if (and_f(current_op[0], current_op[2], current_op[3])) {
                if(circuit[current_op[3]] === 65535) {
                    console.log(current_op);
                    console.log(JSON.stringify(circuit[current_op[0]]));
                    console.log(JSON.stringify(circuit[current_op[2]]));
                    console.log(JSON.stringify(circuit[current_op[3]]));
                }
                op_list.splice(i, 1);
                i--;
            }
            continue;
        } else if (current_op.includes('OR')) {
            if (or_f(current_op[0], current_op[2], current_op[3])) {
                if(circuit[current_op[3]] === 65535) {
                    console.log(current_op);
                    console.log(JSON.stringify(circuit[current_op[0]]));
                    console.log(JSON.stringify(circuit[current_op[2]]));
                    console.log(JSON.stringify(circuit[current_op[3]]));

                }
                op_list.splice(i, 1);
                i--;
            }
        } else if (current_op.includes('RSHIFT')) {
            if (rshift_f(current_op[0], current_op[2], current_op[3])) {
                if(circuit[current_op[3]] === 65535) {
                    console.log(current_op);
                    
                    console.log(JSON.stringify(circuit[current_op[0]]));
                    console.log(JSON.stringify(circuit[current_op[2]]));
                    console.log(JSON.stringify(circuit[current_op[3]]));
                }
                op_list.splice(i, 1);
                i--;
            }
            continue;
        } else if (current_op.includes('LSHIFT')) {
            if (lshift_f(current_op[0], current_op[2], current_op[3])) {
                if(circuit[current_op[3]] === 65535) {
                    console.log(current_op);
                    
                    console.log(JSON.stringify(circuit[current_op[0]]));
                    console.log(JSON.stringify(circuit[current_op[2]]));
                    console.log(JSON.stringify(circuit[current_op[3]]));
                    
                }
                op_list.splice(i, 1);
                i--;
            }
            continue;   
        } else {
            if (circuit[current_op[0]] != undefined) {
                circuit[current_op[1]] = circuit[current_op[0]];
                op_list.splice(i, 1);
                i--;
            }
        }
    }
    
}

console.log(circuit["a"]);
console.log(JSON.stringify(circuit));

function and_f(x, y, z) {
    if (circuit[x] != undefined && circuit[y] != undefined) {
        circuit[z] = (circuit[x] & circuit[y]) & 0xFFFF;
        return true;
    } else if (!isNaN(x) && !isNaN(y)) {
        circuit[z] = (x & y) & 0xFFFF;
        return true;
    } else if (!isNaN(x) && circuit[y] != undefined) {
        circuit[z] = (x & circuit[y]) & 0xFFFF;
        return true;
    } else if (circuit[x] != undefined && !isNaN(y)) {
        circuit[z] = (circuit[x] & y) & 0xFFFF;
        return true;
    }
    return false;
}

function or_f(x, y, z) {
    if (circuit[x] != undefined && circuit[y] != undefined) {
        circuit[z] = (circuit[x] | circuit[y]) & 0xFFFF;
        return true;
    } else if (!isNaN(x) && !isNaN(y)) {
        circuit[z] = (x | y) & 0xFFFF;
        return true;
    } else if (!isNaN(x) && circuit[y] != undefined) {
        circuit[z] = (X | circuit[y]) & 0xFFFF;
        return true;
    } else if (circuit[x] != undefined && !isNaN(y)) {
        circuit[z] = (circuit[x] | y) & 0xFFFF;
        return true;
    }
    return false;
}

function not_f(x, z) {
    if (circuit[x] != undefined) {
        circuit[z] = ~circuit[x] & 0xFFFF;
        return true;
    } else if (!isNaN(x)) {
        circuit[z] = ~x & 0xFFFF;
        return true;
    }
    return false;
}



function rshift_f(x, y, z) {
    if (circuit[x] != undefined && circuit[y] != undefined) {
        circuit[z] = (circuit[x] >>> circuit[y]) & 0xFFFF;
        return true;
    } else if (!isNaN(x) && !isNaN(y)) {
        circuit[z] = (x >>> y) & 0xFFFF;
        return true;
    } else if (!isNaN(x) && circuit[y] != undefined) {
        circuit[z] = (X >>> circuit[y]) & 0xFFFF;
        return true;
    } else if (circuit[x] != undefined && !isNaN(y)) {
        circuit[z] = (circuit[x] >>> y) & 0xFFFF;
        return true;
    }
    return false;
}

function lshift_f(x, y, z) {
    if (circuit[x] != undefined && circuit[y] != undefined) {
        circuit[z] = (circuit[x] << circuit[y]) & 0xFFFF;
        return true;
    } else if (!isNaN(x) && !isNaN(y)) {
        circuit[z] = (x << y) & 0xFFFF;
        return true;
    } else if (!isNaN(x) && circuit[y] != undefined) {
        circuit[z] = (X << circuit[y]) & 0xFFFF;
        return true;
    } else if (circuit[x] != undefined && !isNaN(y)) {
        circuit[z] = (circuit[x] << y) & 0xFFFF;
        return true;
    }
    return false;
}