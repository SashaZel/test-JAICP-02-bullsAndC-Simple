# Bot created by Alexander Zelenkov lll555@yandex.ru

require: scripts/check.js
require: scripts/numberGenerator.js
require: scripts/inputValidation.js
require: common.js
  module = sys.zb-common
require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        intent!: /Сыграем
        a: Приветствую! Сыграем в "Быки и коровы"? Я загадаю 4-х значное число, а ты попробуешь его угадать. Начнем?
        go!: /Start/Agree?
        
        state: Agree?
            
            state: AgreeYes
                intent: /Согласен
                go!: /Game
                
            state: AgreeNo
                intent: /Не_согласен
                a: Ну ОК. Как будешь готов - напиши "сыграем".
                
            # Why does the bot state bubbles to the global scope if no match?
            # How can I catch a wrong answer in the scope of "Agree?" state
            # and handle it with "Agree?/NoMatch" case?
                
            #state: NoMatch
            #    event!: noMatch
            #    a: Я не понял. "Да" или "Нет?" Вы сказали: {{$request.query}}
            
    state: Game
        a: Напиши свою догадку.
        script:
            // random nubmer with $jsapi.random() is not safe
            // we can get '13' instead '0013' and our script will crush
            // ES6 solution dosen't works...
            // even Babel tried to use polyfills for ES5 solution
            // $session.secretNumber = [].concat(String(Math.round(Math.random() * 10000)).padStart(4, '0'));
            
            //$session.secretNumber = [0, 0, 0, 0];
            //for (var i = 0; i < 4 ; i++) {
            //    $session.secretNumber[i] = Math.floor(Math.random() * 10);    
            //}
            //$session.secretNumber = ['1', '2', '3', '4'];
            // CAILA @duchling.number handle input wrong! ('0013' recognize like a '13', mess with 'y1234')
            // We have to make validation by ourself
            $reactions.answer('_var UserGuess {{ $parseTree._UserGuess }}');
            $reactions.answer('_User wrote {{ $request.query }}');
            // Set number of attempts to '0'
            // This feature make for better UX and gameplay
            //console.log('Hello console');
            // console do not accesseble...
            if (inputISvalid($request.query)) {
                $jsapi.startSession();
                $session.numberOfAttempts = 0;
                $session.secretNumber = createNewSecretNumber();
                $reactions.transition("/Check");
            } else {
                $reactions.answer('Hyжно написать четыре цифры');    
            }
        a: Напиши свою догадку.
            
        #go!: /Check
        
        
    state: Check
        intent: /Число
        script: 
            // TODO: line below is a test feature. Remove in production. 
            $reactions.answer("_Secret number {{$session.secretNumber}}");
            // call imported function for checking result from src/scripts/check.js <string>
            var result = checkNumber($parseTree._UserGuess, $session.secretNumber);
            $reactions.answer(result);
            if (result === '   ') {
                $reactions.answer(selectRandomArg(['Что-то совсем пусто. Ничего не угадал', 'Гм. Нет. Пока мимо.', 'Попробуй еще, пока нет совпадений']));
            }
            if (result === 'бык бык бык бык') {
                // Is it possible to add NLG feature for numbers?
                $reactions.answer('Победа! Поздравляю. Попытки: {{ $session.numberOfAttempts }}');
                $reactions.answer('Напиши "Да", если хочешь еще раз сыграть.');
                // Sould I use graceful finish of session?
                $jsapi.stopSession();
                $reactions.transition("/Start/Agree?");
            }
            //if (!Number.isInteger(Number(userGuess))) {
            //    $reactions.answer('Это не число. Пиши цифры.');
            //}
            

    state: Rules
        intent!: /Правила
        a: Вот правила игры: Я загадываю число из четырех цифр, а ты его должен угадать, то есть написать мне четыре цифры. Если ты написал цифру правильно по месту и значению, то я отвечу "бык". Если какая-то твоя цифра есть в моем секретном числе, но место не верно, то я отвечу "корова". Например, я загадал "1234", а ты мне написал "1243". Я отвечу "бык бык корова корова". Сыграем?
    
    state: Bot_about
        intent!: /Бот
        a: Я бот на платформе JAICP. Кто придумал игру, я не знаю, а написал меня Александр Зеленков https://github.com/SashaZel
        
    # Can I use '\n' end of the line?
    # Is it OK for integration like a telecom bots?

    state: NoMatch
        event!: noMatch
        a: Я не понимаю. \n Если будем играть, напиши "Да". \n Если нужны подробные правила, напиши "Правила" \n Если хочешь узнать кто я, напиши "Бот"

    state: Match
        event!: match
        a: {{$context.intent.answer}}