package com.dice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@RequiredArgsConstructor
public class ResponseFacade {

    private final DiceService diceService;

    public void replyToStartBot(long chatId, SilentSender sender) {
        var message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Привет, я DiceGod");
        sender.execute(message);
    }

    public void replyToThrowDice(MessageContext messageContext, SilentSender sender) {
        var message = new SendMessage();
        message.setChatId(messageContext.chatId());
        sender.forceReply(diceService.rollDice(messageContext.arguments()), messageContext.chatId());
    }

}
