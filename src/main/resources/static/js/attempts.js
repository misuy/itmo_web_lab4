class Attempt {
    constructor(id, x, y, r, result, processedAt, processingTime, owner) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
        this.processedAt = processedAt;
        this.processingTime = processingTime;
        this.owner = owner;
    }
}

class Dot {
    constructor(x, y, r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
}


function ajaxLoadAttempts(attempts) {
    let a = []
    $.ajax({
        url: "/api/getAllAttempts",
        method: "get",
        dataType: "json",
        success: function (resp) {
            resp.forEach((el) => {
                a.push(new Attempt(el.id, el.dot.x, el.dot.y, el.dot.r, el.result, el.processedAt, el.processingTime, el.owner));
            })
            attempts.value = a;
        },
    });
}


function ajaxAddAttempt(attempts, dot) {
    $.ajax({
        url: "/api/addAttempt",
        method: "post",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(dot),
        success: function (el) {
            attempts.value.push(new Attempt(el.id, el.dot.x, el.dot.y, el.dot.r, el.result, el.processedAt, el.processingTime, el.owner));
        }
    })
}