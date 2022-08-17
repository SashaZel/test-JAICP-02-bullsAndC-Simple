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
                script:
                    // random nubmer with $jsapi.random() is not safe
                    // we can get '13' instead '0013' and our script will crush
                    // ES6 solution dosen't works...
                    // even Babel tried to use polyfills for ES5 solution
                    // $session.secretNumber = [].concat(String(Math.round(Math.random() * 10000)).padStart(4, '0'));
                    $session.secretNumber = [0, 0, 0, 0];
                    for (var i = 0; i < 4 ; i++) {
                        $session.secretNumber[i] = Math.floor(Math.random() * 10);    
                    }
                a: Секрет!!! {{ $session.secretNumber }}.
                go!: /Game
                
            state: AgreeNo
                intent: /Не_согласен
                script:
                    // is the js comment works here?
                    // we clean up secretNumber without null cause I am not sure in this env and afraid error crush
                    $session.secretNumber = [0, 0, 0, 0];
                a: Ну ОК. Как будешь готов - напиши "сыграем".
                
            # Why does the bot state bubbles to global scope if no match?
            # How can I catch wrong answer in scope of "Agree?" state
            # and handle it with "Agree?/NoMatch" case?
                
            state: NoMatch
                event!: noMatch
                a: Я не понял. "Да" или "Нет?" Вы сказали: {{$request.query}}
        
    state: Game
        a: Ты написал {{ $parseTree._UserGuess }}
            

    state: Hello
        intent!: /привет
        a: Привет привет

    state: Bye
        intent!: /пока
        a: Пока пока

    

    state: Match
        event!: match
        a: {{$context.intent.answer}}