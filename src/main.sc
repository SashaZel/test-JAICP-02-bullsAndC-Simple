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
                a: Ты согласен.
                
            state: AgreeNo
                intent: /Не_согласен
                a: Ну ОК. Как будешь готов - напиши "сыграем".
        
        

    state: Hello
        intent!: /привет
        a: Привет привет

    state: Bye
        intent!: /пока
        a: Пока пока

    state: NoMatch
        event!: noMatch
        a: Я не понял. Вы сказали: {{$request.query}}

    state: Match
        event!: match
        a: {{$context.intent.answer}}