const [N, M] = require('fs').readFileSync('/dev/stdin').toString().split(' ').map(Number);

const array = Array(M).fill(0);
const visited = Array(M + 1).fill(false);

dfs(0);

function dfs(depth) {
    if (depth === M) {
        console.log(array.join(' '));
        return;
    }
    
    for (let i = 1; i <= N; i++) {
        if (visited[i]) {
            continue;
        }
        
        array[depth] = i;
        visited[i] = true;
        dfs(depth + 1);
        visited[i] = false;
    }
}
