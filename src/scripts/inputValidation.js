function inputISvalid(userInput) {
    if (
        !Number(userInput) || 
        typeof Number(userInput) !== 'number' || 
        String(userInput).length !== 4
        ) {
        return false;
    }
    return true;
}