function createNewSecretNumber() {
    //var resultNumber = [0, 0, 0, 0];
    //for (var i = 0; i < 4 ; i++) {
    //    resultNumber = String(Math.floor(Math.random() * 10));    
    //}
    //$jsapi.context().session.numberOfAttempts = 0;
    // Set secret number
    //$jsapi.context().session.secretNumber = ['1', '2', '3', '4'];
    //return ['1', '2', '3', '4']; 
    return ['', '', '', ''].map(
        function () {
            return String(Math.floor(Math.random() * 10)); 
        }    
    );
}