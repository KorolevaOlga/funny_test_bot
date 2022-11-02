package com.example.service;

import com.example.config.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {

    final BotConfig config;

    public Bot(BotConfig config) {
        this.config = config;
        List<BotCommand> commands = new ArrayList<>();
        commands.add(new BotCommand("/start", "selection tests"));

        try {
            this.execute(new SetMyCommands(commands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error command " + e.getMessage());
        }
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();

            long chatId = update.getMessage().getChatId();


            switch (messageText) {

                case "/start":
                    startAnswer(chatId, update.getMessage().getChat().getFirstName());

                    menuStart(chatId);
                    break;
                default:
                    sendMessage(chatId, "Sorry, command was not recognized");


            }

        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            if (callbackData.equals("YesButton")) {
                secondMenu(chatId, "Выберите категорию");
            } else if (callbackData.equals("NoButton")) {
                startAnswer(chatId, update.getMessage().getChat().getFirstName());
                menuStart(chatId);

            }
        }
    }

    private void menuStart(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Хотите пройти тест?");

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rows = new ArrayList<>();

        var buttonYes = new InlineKeyboardButton();
        buttonYes.setText("Да");
        buttonYes.setCallbackData("YesButton");


        var buttonNo = new InlineKeyboardButton();
        buttonNo.setText("Нет");
        buttonNo.setCallbackData("NoButton");

        rows.add(buttonYes);
        rows.add(buttonNo);
        rowsInline.add(rows);
        markup.setKeyboard(rowsInline);
        message.setReplyMarkup(markup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

    public void startAnswer(long chatId, String name) {
        String answer = "Hello, " + name;
        log.info("Replied to user " + name);
        sendMessage(chatId, answer);
    }


    private void sendMessage(long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);


//        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//        List<KeyboardRow> keyboardRows = new ArrayList<>();
//        KeyboardRow rower = new KeyboardRow();
//
//        rower.add("answer");
//        keyboardRows.add(rower);
//        keyboardMarkup.setKeyboard(keyboardRows);
//        sendMessage.setReplyMarkup(keyboardMarkup);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

    private void secondMenu(long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(text);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsMenu= new ArrayList<>();
        List<InlineKeyboardButton> rows = new ArrayList<>();
        List<InlineKeyboardButton> rows2 = new ArrayList<>();

        var buttonStar = new InlineKeyboardButton();
        buttonStar.setText("Знаменитости");
        buttonStar.setCallbackData("StarButton");

        var buttonSelf = new InlineKeyboardButton();
        buttonSelf.setText("Познай себя");
        buttonSelf.setCallbackData("SelfButton");

        var buttonGuess = new InlineKeyboardButton();
        buttonGuess.setText("Угадай");
        buttonGuess.setCallbackData("GuessButton");

        var buttonFilms = new InlineKeyboardButton();
        buttonFilms.setText("Фильмы");
        buttonFilms.setCallbackData("FilmsButton");

        rows.add(buttonStar);
        rows.add(buttonSelf);
        rows2.add(buttonGuess);
        rows2.add(buttonFilms);

        rowsMenu.add(rows);
        rowsMenu.add(rows2);

        markup.setKeyboard(rowsMenu);

        sendMessage.setReplyMarkup(markup);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }


    }

}
