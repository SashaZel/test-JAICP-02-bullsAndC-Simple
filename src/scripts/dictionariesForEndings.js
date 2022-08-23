var bullsCowsWordsEndDict = [
    [ 'ов', '' ],
    [ '', 'а' ],
    [ 'а', 'ы' ],
    [ 'а', 'ы' ],
    [ 'а', 'ы' ],
];

function numberOfTurnsName (number) {
    
    if (number === 11 || number === 12 || number === 13 || number === 14) {
        return ' ходов';
    }
    
    var lastDigitOfTurnsNumber = number % 10;
    
    if (lastDigitOfTurnsNumber === 1) {
        return ' ход';
    }
    
    if (
        lastDigitOfTurnsNumber === 2 ||
        lastDigitOfTurnsNumber === 3 ||
        lastDigitOfTurnsNumber === 4 
    ) {
        return ' хода';
    }
    
    return ' ходов';
}