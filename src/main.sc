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
        a: Приветствую! Сыграем в "Быки и коровы"? Я загадаю 4-х значное число (цифры от "0" до "9", все разные), а ты попробуешь его угадать. Если нужны подробные правила игры, напиши "Как играть". Если хочешь начать игру заново напиши "Сыграем". Если хочешь узнать кто я, напиши "Бот". Начнем?
        go!: /Start/Agree?
        
        state: Agree?
            
            state: AgreeYes
                intent: /Согласен
                go!: ../GameStart
                
            state: AgreeNo
                intent: /Не_согласен
                random:
                    a: Ну ОК. Как будешь готов - напиши "Сыграем".
                    a: Хорошо. Как захочешь, напиши "Пуск".
            
            state: GameStart
                intent: /Начало_игры
                script:
                    // IMPORTANT!:  line below is a test feature. Remove in production.
                    //$reactions.answer('_User wrote {{ $request.query }}');
            
                    // Start session by cleaning $session. obj
                    $jsapi.startSession();
                    
                    // CAILA @duchling.number handles input wrong! ('0013' recognize like a '13' and mess with input like 'y1234')
                    // We have to make validation by ourself via inputISvalid()
                    if (inputISvalid($request.query)) {
                        // Set number of attempts to '0'
                        // This feature made for the better UX and gameplay
                        $session.numberOfAttempts = 0;
                        // random nubmer with $jsapi.random() is not safe
                        // we can get '13' instead '0013' and our script will crush. We have to use custom funct
                        $session.secretNumber = createNewSecretNumber();
                        $reactions.transition("/Check");
                    } else {
                        $reactions.answer('Требуются четыре разные цифры');    
                    }
        
    state: Check
        # use global intent for expirienced users - they can start the game just after loading (without 'Да-Нет' choise)
        intent!: /Игра
        script: 
            var worldEndDict = [
                [ 'ов', '' ],
                [ '', 'а' ],
                [ 'а', 'ы' ],
                [ 'а', 'ы' ]
            ];
            // IMPORTANT!: line below is a test feature. Remove in production. 
            $reactions.answer("_Secret number {{$session.secretNumber}}");
            $session.numberOfAttempts += 1;
            
            if (inputISvalid($request.query)) {
                
            
                var result = checkNumber($request.query, $session.secretNumber);
                $reactions.answer(result[0] + 'бык' + worldEndDict[result[0]][0] + ' / ' + result[1] + ' коров' + worldEndDict[result[1]][1]);
            
                if (result[0] === 0 && result[1] === 0) {
                    $reactions.answer(selectRandomArg(['Что-то совсем пусто. Нет правильных цифр', 'Гм. Нет. Пока мимо.', 'Попробуй еще, пока нет совпадений']));
                }
            
                if (result[0] === 4) {
                    $reactions.answer('Победа! Поздравляю. Попытки: {{ $session.numberOfAttempts }}');
                    $reactions.answer('Напиши "Да", если хочешь еще раз сыграть.');
                    $jsapi.stopSession();
                    $reactions.transition("/Start/Agree?");
            }
            } else {
                $reactions.answer('Требуются четыре разные цифры'); 
            }

    state: Rules
        intent!: /Правила
        a: Вот правила игры: Я загадываю число из четырех цифр, а тебе надо его угадать, то есть написать мне четыре цифры. Если ты пишешь цифру правильно по месту и значению, то я отвечу "бык". Если какая-то твоя цифра есть в моем секретном числе, но место не верно, то я отвечу "корова". Например, я загадал "1234", а ты мне пишешь "1111" - я отвечаю "бык корова корова корова"; ты пишешь "4567" - я отвечаю "корова"; ты напишешь "1243" - отвечу "бык бык корова корова". Сыграем?
        go: /Start/Agree?
        
    state: Bot_about
        intent!: /Бот
        a: Я бот на платформе JAICP. Кто придумал игру, я не знаю, а написал меня Александр Зеленков https://github.com/SashaZel \n Будем играть?
        go: /Start/Agree?
        
    # Can I use '\n' end of the line?
    # Is it OK for integration like a telecom bots?

    state: NoMatch
        event!: noMatch
        a: Я не понимаю. \n Если хочешь новую игру, напиши "Сыграем". \n Если нужны подробные правила, напиши "Как играть" \n Если хочешь узнать кто я, напиши "Бот"
        
    state: Match
        event!: match
        a: {{$context.intent.answer}}