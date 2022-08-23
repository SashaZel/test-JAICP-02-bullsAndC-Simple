function createValidSecretNumber () {
    return ['', '', '', ''].map(
        function () {
            return String($reactions.random(10)); 
        }    
    );
}

// validation of secret number
// in fact this feature is redundant cause docs specified $reactions.random(max) as a method
// with check of not repeating returned random value more often than once per every max/2 

function createNewSecretNumber() {
    var resultNumber;
    
    while (!inputIsValid(resultNumber.join(''))) {
        resultNumber = createValidSecretNumber();
    }
    
    return resultNumber;
}

