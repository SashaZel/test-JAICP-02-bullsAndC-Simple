function checkNumber(userGuess) {
    //var userGuess = $parseTree._UserGuess;
            
    //$reactions.answer(checkNumber('a'));
    var arrForFormatting = String(userGuess).split('');
    return arrForFormatting.map(
        function(element, index) {
            return element + 'Hi';
        }
    ).join(' ');
    
    if (String(userGuess).length !== 4) {
        return 'Нужно написать четыре цифры';
    }        
    if (userGuess === $jsapi.context().session.secretNumber) {
        return 'Угадал!';
    //    $jsapi.context().reactions.answer('Угадал!');
    } 

    return 'Не угадал.';

}