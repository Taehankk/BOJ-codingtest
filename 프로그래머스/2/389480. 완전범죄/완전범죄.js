let answer, trace, max_a, max_b, visited;

function solution(info, n, m) {
    answer = 1000;
    
    trace = info;
    max_a = n;
    max_b = m;
    visited = [];
    
    stealItem(-1, 0, 0);
    
    if(answer === 1000) {
        answer = -1;
    }
    
    return answer;
}

function stealItem(idx, acc_a, acc_b){    
    if(idx >= trace.length - 1) {
        if(acc_a < answer){
            answer = acc_a;
        }
        return;
    }
    
    let key = idx+ ":" + acc_a + "/" + acc_b;
    
    if(visited.includes(key)) return;
    visited.push(key);
    
    if(acc_a + trace[idx + 1][0] < max_a && acc_a + trace[idx + 1][0] < answer) {
        stealItem(idx + 1, acc_a + trace[idx + 1][0], acc_b);
    }
    
    if(acc_b + trace[idx + 1][1] < max_b) {
        stealItem(idx + 1, acc_a, acc_b + trace[idx + 1][1]);
    }    
}