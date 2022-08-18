function checkNumber(userGuess, secretNumber) {
    
    if (!userGuess || !secretNumber) {
        return 'Ой, у меня ошибка. Игра не началась. Напиши "Как играть", чтобы прочитать правила.'
    }
    
    if (!inputISvalid(userGuess)) {
        return 'Ошибка ввода. Нужно написать четыре цифры';
    }         

    return String(userGuess).split('').map(
        function(element, index) {
            if (element === secretNumber[index]) {
                return 'бык';
            }
            if (secretNumber.indexOf(element) >= 0) {
                return 'корова';
            }
            return '';
        }
    ).join(' ');
    // How to handle an error in this env?
    // No console access...
}