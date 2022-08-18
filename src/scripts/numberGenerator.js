function createNewSecretNumber() {
    
    $jsapi.context().session.numberOfAttempts = 0;
    // Set secret number
    $jsapi.context().session.secretNumber = ['1', '2', '3', '4'];
    return ['1', '2', '3', '4']; 
}