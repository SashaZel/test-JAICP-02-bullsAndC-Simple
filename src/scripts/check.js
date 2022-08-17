function checkNumber(userGuess) {
    //var userGuess = $parseTree._UserGuess;
            
    //$reactions.answer(checkNumber('a'));
            
            
    if (userGuess == $jsapi.context().session.secretNumber) {
        return 'Угадал!';
    //    $jsapi.context().reactions.answer('Угадал!');
    } else {
        return 'Не угадал.';
    //    $jsapi.context().reactions.answer('НЕ угадал!');
    }
}