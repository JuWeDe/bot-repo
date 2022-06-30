
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// don't forget to add .jar file
public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args)  {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void onUpdateReceived(Update update) {
        Model model = new Model();
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/start":
                    sendMes(message, "POGнали");
                    break;
                case "/help":
                    sendMes(message, "Писать только название города (eng or rus)");
                    break;
                case "/gg":
                    sendMes(message, "Не будет");
                    //sendSticker()
                    break;
                default:
                    try {
                        sendMes(message, Weather.getWeather(message.getText(), model));
                    } catch (IOException e) {
                        sendMes(message, "Город не найден!");
                    }
            }
        }
    }
        /*if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getChatId().toString();
            String chatId = update.getMessage().getChatId().toString();

            SendMessage sender = new SendMessage();
            sender.setChatId(chatId);
            sender.setText(message);

            try {
                execute(sender);
            }
            catch (TelegramApiException e) {
                // need logger
                e.printStackTrace();
            }
        } */
    public void sendMes(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            boolean isProblem = true;
        }
    }
    public void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        sendMessage.enableMarkdown(false);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboard1stRow = new KeyboardRow();
        KeyboardRow keyboard2ndRow = new KeyboardRow();
        keyboard1stRow.add(new KeyboardButton("/start"));
        keyboard1stRow.add(new KeyboardButton("/help"));
        keyboard2ndRow.add(new KeyboardButton("/gg"));


        keyboardRowList.add(keyboard1stRow);
        keyboardRowList.add(keyboard2ndRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

    }
    public String getBotUsername() {
        return "BotName";
    }

    public String getBotToken() {
        return "5150552312:AAE5KwxvCt3rQtLOfTrlW8zpK2bJa8RsNNA";
    }
}
