function inputISvalid(userInput) {
    if (userInput === '0000') {
        return true;
    }
    if (
        Number(userInput) && 
        typeof Number(userInput) === 'number' && 
        String(userInput).length === 4
        ) {
        return true;
    }
    return false;
}