function createNewSecretNumber() {
    // Set number of attempts to '0'
    // This feature make for better UX and gameplay
    $jsapi.context().session.numberOfAttempts = 0;
    // Set secret number
    $jsapi.context().session.secretNumber = ['1', '2', '3', '4'];
}