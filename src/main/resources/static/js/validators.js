function validateX(xS) {
    let x = parseFloat(xS);
    if (!isNaN(x)) return (x >= -2) && (x <= 2);
    return false;
}

function validateY(xY) {
    let y = parseFloat(xY);
    if (!isNaN(y)) return (y >= -5) && (y <= 3);
    return false;
}

function validateR(xR) {
    let r = parseFloat(xR);
    if (!isNaN(r)) return (r >= 0.5) && (r <= 2);
    return false;
}