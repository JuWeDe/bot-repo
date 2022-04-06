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

import javax.validation.groups.Default;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) throws TelegramApiException {
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
    public void sendMes(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            execute(sendMessage); //sendMessage(sendMessage)
        } catch (Exception e) {
            e.printStackTrace();
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
