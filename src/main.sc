# Bot created by Alexander Zelenkov lll555@yandex.ru

require: scripts/check.js
require: scripts/dictionariesForEndings.js
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
        a: Приветствую! Сыграем в "Быки и коровы"? Я загадаю 4-х значное число (цифры от "0" до "9", все разные), а ты попробуешь его угадать. Если хочешь начать игру заново напиши: "Сыграем". Начнем?
        go!: /Start/Agree
        
        state: Agree
            
            state: AgreeYes
                intent: /Согласен
                go!: ../GameStart
                
            state: AgreeNo
                intent: /Не_согласен
                random:
                    a: Ну ОК. Как будешь готов - напиши: "Сыграем".
                    a: Хорошо. Как захочешь, напиши: "Пуск".
            
            state: GameStart
                intent: /Начало_игры
                script:
                    $jsapi.startSession();
                    
                    if (inputISvalid($request.query)) {
                        $session.numberOfAttempts = 0;
                        $session.secretNumber = createNewSecretNumber();
                        $reactions.transition("/Check");
                    } else {
                        $reactions.answer('Требуются четыре разные цифры.');    
                    }
        
    state: Check
        intent!: /Игра
        script: 
            // IMPORTANT!: line below is a test feature. Remove in production. 
            //$reactions.answer("_Secret number {{$session.secretNumber}}");
            
            $session.numberOfAttempts += 1;
            
            if (inputISvalid($request.query)) {
                
                var result = checkNumber($request.query, $session.secretNumber);
                $reactions.answer(result[0] + ' бык' + bullsCowsWordsEndDict[result[0]][0] + ' / ' + result[1] + ' коров' + bullsCowsWordsEndDict[result[1]][1]);
            
                if (result[0] === 0 && result[1] === 0) {
                    $reactions.answer('Попробуй еще. Пока совпадений нет.');
                }
            
                if (result[0] === 4) {
                    $reactions.answer('Победа! Число угадано за ' + $session.numberOfAttempts + numberOfTurnsName($session.numberOfAttempts) + '. Напиши "Да", если хочешь еще раз сыграть.');
                    $jsapi.stopSession();
                    $reactions.transition("/Start/Agree");
              }
            } else {
                $reactions.answer('Требуются четыре разные цифры.'); 
            }

    state: NoMatch
        event!: noMatch
        a: Я не понимаю. Игра остановлена. Если хочешь новую игру, напиши: "Сыграем".
        go!: /Start/Agree
        
    # state: Match
    #     event!: match
    #     a: {{$context.intent.answer}}