function checkNumber(userGuess, secretNumber) {
    
    var bulls = 0;
    var cows = 0;
    
    for ( var i = 0; i < 4; i++ ) {
        if (userGuess[i] === secretNumber[i]) {
            bulls += 1;
        } 
        if (secretNumber.indexOf(userGuess[i]) >= 0 && userGuess[i] !== secretNumber[i]) {
            cows += 1;
        }
    }
    
    return [ bulls, cows ];
}