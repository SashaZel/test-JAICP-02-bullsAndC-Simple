function createNewSecretNumber() {
    // pure funct
    // Hardcoded test dummy data below
    // return ['1', '2', '3', '4']; 
    return ['', '', '', ''].map(
        function () {
            return String($reactions.random(10)); 
        }    
    );
}