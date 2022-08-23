function checkNumber(userGuess, secretNumber) {
    
    // if (!userGuess || !secretNumber) {
    //     return 'Ой, у меня ошибка. Игра не началась. Напиши "Как играть", чтобы прочитать правила.';
    // }
    
    // if (!inputISvalid(userGuess)) {
    //     return 'Ошибка ввода. Нужно написать четыре цифры';
    // }         

    // return String(userGuess).split('').map(
    //     function(element, index) {
    //         if (element === secretNumber[index]) {
    //             return 'бык';
    //         }
    //         if (secretNumber.indexOf(element) >= 0) {
    //             return 'корова';
    //         }
    //         return '';
    //     }
    // ).join(' ');
    var bulls = 0;
    var cows = 0;
    
    for ( var i = 0; i < 4; i++ ) {
        if (userGuess[i] === secretNumber[i]) {
            bulls += 1;
        } 
        if (secretNumber.indexOf(userGuess[i]) >= 0 && userGuess[i] === secretNumber[i]) {
            cows += 1;
        }
    }
    
    return [ bulls, cows ];

}