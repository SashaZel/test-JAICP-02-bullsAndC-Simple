function checkNumber(userGuess) {
    //var userGuess = $parseTree._UserGuess;
            
    //$reactions.answer(checkNumber('a'));
            
            
    if (userGuess == $session.secretNumber) {
        $reactions.answer('Угадал!');
    } else {
        $reactions.answer('НЕ угадал!');
    }
}