function inputISvalid(userInput) {
    // if (userInput === '0000') {
    //     return true;
    // }
    
    if (
        !Number(userInput) || 
        typeof Number(userInput) !== 'number' || 
        String(userInput).length !== 4
        ) {
        return false;
    }
    for (var i = 0; i < 4; i++) {
        if (userInput.split('').filter(
                function (element) { return element === userInput[i] }
            ).length !== 1 ) {
            return false;    
        }
    }
    return true;
}