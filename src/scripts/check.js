function checkNumber(userGuess) {
    //var userGuess = $parseTree._UserGuess;
            
    //$reactions.answer(checkNumber('a'));
            
            
    if (userGuess == $jsapi.context().session.secretNumber) {
        $jsapi.context().reactions.answer('Угадал!');
    } else {
        $jsapi.context().reactions.answer('НЕ угадал!');
    }
}