function solution(today, terms, privacies) {
    var answer = [];
    
    const splitToday = today.split(".");
    
    const year = Number(splitToday[0]);
    const month = Number(splitToday[1]);
    const day = Number(splitToday[2]);
    
    var splitTerms = new Map();
    
    var temp = [];
    for(term of terms){
        temp = term.split(" ");
        
        splitTerms.set(temp[0], Number(temp[1]));
    }
    
    var privDate = [];
    var privYear, privMonth, privDay, addMonth, addYear;
    
    privacies.forEach((privacy, index) => {
        temp = privacy.split(" ");
        
        privDate = temp[0].split(".");
        
        privYear = Number(privDate[0]);
        privMonth = Number(privDate[1]);
        privDay = Number(privDate[2]);
        
        addMonth = Number(splitTerms.get(temp[1]));
        
        addYear = parseInt((privMonth + addMonth) / 12);
        
        // 연도와 월 계산
        privMonth += addMonth;
        while (privMonth > 12) {
            privYear += 1;
            privMonth -= 12;
        }
        
        if (privMonth > 12) {
            privYear += 1;
            privMonth -= 12;
        }

        privDay = privDay - 1;
        if (privDay == 0) {
            privMonth -= 1;
            privDay = 28;

            if (privMonth == 0) {
                privMonth = 12;
                privYear -= 1;
            }
        }
        
        if (year < privYear) {
            return;
        } else if (year > privYear) {
            answer.push(index + 1);
        } else {
            if (month < privMonth) {
                return;
            } else if (month > privMonth) {
                answer.push(index + 1);
            } else {
                if (day < privDay) {
                    return;
                } else if (day > privDay) {
                    answer.push(index + 1);
                }
            }
        }
    })
    
    return answer;
}