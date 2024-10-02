function solution(cap, n, deliveries, pickups) {
    var answer = 0;
    
    var deliverStack = [];
    var pickupStack = [];
    
    deliveries.forEach((delivery, index) => {
        if(delivery !== 0){
            deliverStack.push([index+1, delivery]);
        }
    })
    
    pickups.forEach((pickup, index) => {
        if(pickup !== 0){
            pickupStack.push([index+1, pickup]);
        }
    })
    
    var deliverHouse = [];
    var pickupHouse = [];    
    var deliverCount, pickupCount;
    var houseIndex = 0;
    
    while(deliverStack.length !== 0 || pickupStack.length !== 0){
        deliverCount = cap;        
        while (deliverStack.length !== 0 && 0 < deliverCount){
            deliverHouse = deliverStack.pop();
            
            if (deliverHouse[1] > deliverCount) {
                deliverHouse[1] -= deliverCount;
                deliverCount = 0;

                deliverStack.push([deliverHouse[0], deliverHouse[1]]);

                if (deliverHouse[0] > houseIndex) {
                    houseIndex = deliverHouse[0];
                }
            } else {
                deliverCount -= deliverHouse[1];

                if (deliverHouse[0] > houseIndex) {
                    houseIndex = deliverHouse[0];
                }
            }
        }     
        
        pickupCount = cap;
        while (pickupStack.length !== 0 && 0 < pickupCount) {
            pickupHouse = pickupStack.pop();

            if (pickupHouse[1] > pickupCount) {
                pickupHouse[1] -= pickupCount;
                pickupCount = 0;

                pickupStack.push([pickupHouse[0], pickupHouse[1]]);

                if (pickupHouse[0] > houseIndex) {
                    houseIndex = pickupHouse[0];
                }
            } else {
                pickupCount -= pickupHouse[1];

                if (pickupHouse[0] > houseIndex) {
                    houseIndex = pickupHouse[0];
                }
            }

        }

        answer += (houseIndex * 2);
        houseIndex = 0;
    }
    return answer;
}