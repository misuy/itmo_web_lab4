const firstColor = "black"
const secondColor = "rgba(33, 150, 243, 0.7)"

function drawCoordinates(stage, width, height, xMiddle, yMiddle, r) {
    let linePath = stage.path()
    let textStyle = {fontSize: Math.round(r / 13), fontFamily: "Roboto", color: firstColor}
    
    //drawing vertical arrow
    linePath.moveTo(xMiddle, 0)
    linePath.lineTo(xMiddle, height)
    linePath.moveTo(xMiddle - r / 30, r / 20)
    linePath.lineTo(xMiddle, 0)
    linePath.lineTo(xMiddle + r / 30, r / 20)
    stage.text(xMiddle + r / 30, 0, "Y", textStyle)
    
    //drawing horizontal arrow
    linePath.moveTo(0, yMiddle)
    linePath.lineTo(width, yMiddle)
    linePath.moveTo(width - r / 20, yMiddle - r / 30)
    linePath.lineTo(width, yMiddle)
    linePath.lineTo(width - r / 20, yMiddle + r / 30)
    stage.text(width - r / 20, yMiddle + r / 30, "X", textStyle)
    
    //drawing marks
    linePath.moveTo(xMiddle - r / 40, yMiddle - r / 2)
    linePath.lineTo(xMiddle + r / 40, yMiddle - r / 2)
    stage.text(xMiddle + r / 40, yMiddle - r / 2, "R/2", textStyle)
    
    linePath.moveTo(xMiddle - r / 40, yMiddle - r)
    linePath.lineTo(xMiddle + r / 40, yMiddle - r)
    stage.text(xMiddle + r / 40, yMiddle - r, "R", textStyle)
    linePath.moveTo(xMiddle - r / 40, yMiddle + r / 2)
    linePath.lineTo(xMiddle + r / 40, yMiddle + r / 2)
    stage.text(xMiddle + r / 40, yMiddle + r / 2, "-R/2", textStyle)
    
    linePath.moveTo(xMiddle - r / 40, yMiddle + r)
    linePath.lineTo(xMiddle + r / 40, yMiddle + r)
    stage.text(xMiddle + r / 40, yMiddle + r, "-R", textStyle)
    
    linePath.moveTo(xMiddle + r / 2, yMiddle - r / 40)
    linePath.lineTo(xMiddle + r / 2, yMiddle + r / 40)
    stage.text(xMiddle + r / 2, yMiddle + r / 40, "R/2", textStyle)
    
    linePath.moveTo(xMiddle + r, yMiddle - r / 40)
    linePath.lineTo(xMiddle + r, yMiddle + r / 40)
    stage.text(xMiddle + r, yMiddle + r / 40, "R", textStyle)
    
    linePath.moveTo(xMiddle - r / 2, yMiddle - r / 40)
    linePath.lineTo(xMiddle - r / 2, yMiddle + r / 40)
    stage.text(xMiddle - r / 2, yMiddle + r / 40, "-R/2", textStyle)
    
    linePath.moveTo(xMiddle - r, yMiddle - r / 40)
    linePath.lineTo(xMiddle - r, yMiddle + r / 40)
    stage.text(xMiddle - r, yMiddle + r / 40, "-R", textStyle)
    
    linePath.stroke(firstColor)
}


function drawShape(stage, width, height, xMiddle, yMiddle, r) {
    let linePath = stage.path()
    
    linePath.moveTo(xMiddle, yMiddle)
    
    //drawing rectangle
    linePath.lineTo(xMiddle, yMiddle-r)
    linePath.lineTo(xMiddle+r, yMiddle-r)
    linePath.lineTo(xMiddle+r, yMiddle)
    linePath.lineTo(xMiddle+r/2, yMiddle)
    
    //drawing triangle
    linePath.lineTo(xMiddle, yMiddle+r)
    
    //drawing arc
    linePath.arcTo(r, r, 90, 90, r/2)
    
    linePath.lineTo(xMiddle, yMiddle)
    
    
    linePath.stroke(secondColor)
    linePath.fill(secondColor)
}


function drawAttempt(stage, width, height, xMiddle, yMiddle, r, attempt) {
    let color;
    if (attempt.result) color = "rgba(3, 252, 107, 1)"
    else color = "black"
    if (attempt !== 0) {
        stage.circle(xMiddle + (attempt.x / attempt.r) * r, yMiddle - (attempt.y / attempt.r) * r, 2).stroke(color).fill(color)
    }
}


function plotGraph(attemptsRel, rRel, csrf) {
    let currentR = parseFloat(rRel.value);
    let attempts = attemptsRel.value;
    clearHolder();
    let stage = acgraph.create("graph-holder");
    stage.removeChildren();
    const width = document.getElementById("graph-holder").clientWidth;
    const height = document.getElementById("graph-holder").clientHeight;
    const xMiddle = width / 2;
    const yMiddle = height / 2;
    const r = Math.min(width, height) * 0.4;
    
    drawCoordinates(stage, width, height, xMiddle, yMiddle, r);
    
    drawShape(stage, width, height, xMiddle, yMiddle, r);
    
    attempts.forEach(attempt => {
        if (currentR === attempt.r) drawAttempt(stage, width, height, xMiddle, yMiddle, r, attempt);
    })
    
    document.getElementById("graph-holder").addEventListener("click", (event) => {
        clickHandler(event, currentR, r, attemptsRel, csrf);
    });
}

function clearHolder() {
    let graphHolder = document.getElementById("graph-holder");
    graphHolder.replaceWith(graphHolder.cloneNode(false));
}

function clickHandler(event, r, r_pixels, attemptsRel) {
    let x_pos = event.clientX + window.scrollX;
    let y_pos = event.clientY + window.scrollY;
    let element = document.getElementById("graph-holder");
    let box = element.getBoundingClientRect();
    let x_middle = element.clientWidth / 2 + box.left + window.scrollX;
    let y_middle = element.clientHeight / 2 + box.top + window.scrollY;
    
    if (isNaN(r)) alert("R is incorrect :(");
    else {
        let x = (x_pos - x_middle) * r / r_pixels;
        let y = (y_middle - y_pos) * r / r_pixels;
        if (validateX(x.toString())) {
            if (validateY(y.toString())) {
                if (validateR(r.toString())) {
                    ajaxAddAttempt(attemptsRel, new Dot(x, y, r));
                }
                else alert("R is incorrect :(");
            }
            else alert("Y is incorrect :(");
        }
        else alert("X is incorrect :(");
    }
    
}