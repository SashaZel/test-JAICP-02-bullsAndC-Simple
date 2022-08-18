function checkNumber(userGuess, secretNumber) {
    if (!userGuess || !secretNumber) {
        return 'Ой, у меня ошибка. Игра не началась. Напиши "Да", чтобы поигать, или "Правила", чтобы прочитать правила.'
    }
    if (String(userGuess).length !== 4) {
        return 'Нужно написать четыре цифры';
    }         
    // Increment number of attempts
    $jsapi.context().session.numberOfAttempts += 1;
    //var arrForFormatting = String(userGuess).split('');
    return String(userGuess).split('').map(
        function(element, index) {
            if (element === $jsapi.context().session.secretNumber[index]) {
                return 'бык';
            }
            if ($jsapi.context().session.secretNumber.indexOf(element) >= 0) {
                return 'корова';
            }
            return '';
        }
    ).join(' ');
    // How to handle an error in this env?
    // No console access...
}