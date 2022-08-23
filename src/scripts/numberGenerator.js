function createValidSecretNumber () {
    return ['', '', '', ''].map(
        function () {
            return String($reactions.random(10)); 
        }    
    );
}

// Validation of secret number.
// In fact this feature is redundant cause docs specified $reactions.random(max) as a method
// which returns uniqe number for less than max/2 attempt 

function createNewSecretNumber() {
    var resultNumber = ['0', '0', '0', '0'];
    
    while (!inputISvalid(resultNumber.join(''))) {
        resultNumber = createValidSecretNumber();
    }
    
    return resultNumber;
}