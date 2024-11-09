package com.dice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;

@Component
public class ApplicationTelegramBot extends AbilityBot {


    @Autowired
    private ResponseFacade responseFacade;

    public ApplicationTelegramBot(@Value("${app.bot.token}") String token, @Value("${app.bot.name}") String name) {
        super(token, name);
    }


    @Override
    public long creatorId() {
        return 1l;
    }

    public Ability startBot() {
        return Ability.builder()
                .name("start")
                .info("Запуск или перезапуск бота")
                .locality(Locality.USER)
                .privacy(Privacy.PUBLIC)
                .action(ctx -> responseFacade.replyToStartBot(ctx.chatId(), silent))
                .build();
    }

    public Ability rollDice() {
        return Ability.builder()
                .name("roll").info("Бросок кубика")
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .action(ctx -> responseFacade.replyToThrowDice(ctx, silent()))
                .build();
    }

}
