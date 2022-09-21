import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    public Bot() {
    }


    @Override
    public String getBotUsername() {
        return "crntWeatherBot";
    }

    @Override
    public String getBotToken() {
        return "5749844610:AAEVwsriN3Uf0oilsZIAFyhrNracxpoSMSU";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Model model = new Model();
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/start":
                    sendMes(message, "Hi there! Just write me city name");
                    break;
                case "/help":
                    sendMes(message, "This simple bot can send you info about weather.\nAll you need is to write city in english");
                    break;
                default:

                    try {
                        sendMes(message, WeatherStats.getWeather(message.getText(), model));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
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
            execute(sendMessage);
        } catch (TelegramApiException e) {
            //error caused by pulling "long" value into "int" variable in telegram Api
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

        keyboard1stRow.add(new KeyboardButton("/start"));
        keyboard1stRow.add(new KeyboardButton("/help"));


        keyboardRowList.add(keyboard1stRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

    }


}