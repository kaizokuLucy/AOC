var fs = require('fs');
var input = fs.readFileSync("Day9/input.txt").toString().split("\n");

var data = input.map(x => x.split(/=|to/).map(l => l.trim()));

let graph = [];
let cities = new Set();

for (var d of data) {
    cities.add(d[0]);
    cities.add(d[1]);
    var pom = {
        "from": d[0],
        "to": d[1],
        "dist": parseInt(d[2])
    }
    graph.push(pom);
    pom = {
        "from": d[1],
        "to": d[0],
        "dist": parseInt(d[2])
    }
    graph.push(pom);
}
for (var c of cities) {
    var seen = [c];
    console.log(minimum_path(c, seen, 0));
}
// var seen = [graph[0].from];
// console.log(minimum_path(graph[0].from, seen, 0));

function minimum_path(city, seen, travelled_dist) {
    if (seen.length === cities.size) {
        return travelled_dist;
    }
    var nei = [...cities].filter(x => !seen.includes(x));
    var min = 6666666;
    for (var n of nei) {
        var seen2 = [...seen, n];
        travelled_dist2 = travelled_dist + graph.filter(x => x.from == city && x.to == n)[0].dist;
        min = Math.min(min, minimum_path(n, seen2, travelled_dist2));
    }
    return min;
}