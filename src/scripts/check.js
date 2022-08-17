function checkNumber(userGuess) {
    //var userGuess = $parseTree._UserGuess;
    if (String(userGuess).length !== 4) {
        return 'Нужно написать четыре цифры';
    }         
    // Increment number of attempts
    $jsapi.context().session.numberOfAttempts += 1;
    var arrForFormatting = String(userGuess).split('');
    return arrForFormatting.map(
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
    
           
    if (userGuess === $jsapi.context().session.secretNumber) {
        return 'Угадал!';
    //    $jsapi.context().reactions.answer('Угадал!');
    } 

    return 'Не угадал.';

}