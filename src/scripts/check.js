function checkNumber(userGuess) {
    //var userGuess = $parseTree._UserGuess;
            
    //$reactions.answer(checkNumber('a'));
    if (String(userGuess).length != 4) {
        return 'Нужно написать четыре цифры';
    }        
    if (userGuess == $jsapi.context().session.secretNumber) {
        return 'Угадал!';
    //    $jsapi.context().reactions.answer('Угадал!');
    } 

    return 'Не угадал.';

}