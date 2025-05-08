let answer, max, req, resp;
let selected, max_ans_selected;
// let code = [0, 0, 0, 0, 0];


function solution(n, q, ans) {
    answer = 0;
    max = n;
    req = q;
    resp = ans;
    
    selected = [];
    max_ans_selected = [];
    
    for(let i = 0; i < n; i++) {
        selected.push(false);
        max_ans_selected.push(false);
    }
    
    let max_ans = 0;
    let max_idx = 0;
    for(let i =0; i < ans.length; i++) {
        if(ans[i] > max_ans) {
            max_ans = ans[i];
            max_idx = i;
        }
    }
    
    for(let i = 0; i < 5; i++) {
        max_ans_selected[q[max_idx][i] - 1] = true;
    }
    
    selectInMaxCase(q[max_idx], 0, 0, max_ans);
    
    return answer;
}

function selectInMaxCase(arr, idx, next, max_cnt) {
    if(next >= 5) return;
    
    for(let i = next; i < 5; i++) {
        selected[arr[i] - 1] = true;
        // code[idx] = arr[i];
        
        if(idx + 1 < max_cnt) {
            selectInMaxCase(arr, idx + 1, i + 1, max_cnt);
        }else {
            selectInRest(max_cnt, 0);
        }
        
        selected[arr[i] - 1] = false; 
    }
}

function selectInRest(idx, next) {
    if(idx >= 5) {
        checkCode();
        return;
    }
    
    for(let i = next; i < max; i++) {
        if(!max_ans_selected[i] && !selected[i]) {
            selected[i] = true;
            // code[idx] = i + 1;
            
            selectInRest(idx + 1, i + 1);
            selected[i] = false;
        }
    }
}

function checkCode() {
    // let sortedCode = [...code].sort((a, b) => a - b);
    
    let arr, cnt;
    for(let i = 0; i < req.length; i++) {
        arr = req[i];
        cnt = 0;
        
        for(let i = 0; i < 5; i++) {
            if(selected[arr[i] - 1]) {
                cnt++;
            }
        }
        
        if(cnt != resp[i]) {
            return;
        }
    }
    
    answer++;
}