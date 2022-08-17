function checkNumber(userGuess) {
    //var userGuess = $parseTree._UserGuess;
    if (String(userGuess).length !== 4) {
        return 'Нужно написать четыре цифры';
    }         
    //$reactions.answer(checkNumber('a'));
    var arrForFormatting = String(userGuess).split('');
    return arrForFormatting.map(
        function(element, index) {
            if (element === $jsapi.context().session.secretNumber[index]) {
                return 'бык';
            }
            return element + 'Hi';
        }
    ).join(' ');
    
           
    if (userGuess === $jsapi.context().session.secretNumber) {
        return 'Угадал!';
    //    $jsapi.context().reactions.answer('Угадал!');
    } 

    return 'Не угадал.';

}