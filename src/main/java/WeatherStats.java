import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class WeatherStats {

    //private final static Gson GSON = new Gson();

    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=bf40dbd4c7098b41743f52c421f87d82");
        Scanner input = new Scanner((InputStream) url.getContent());

        StringBuilder result = new StringBuilder();
        //System.out.println(url);
        while (input.hasNext()) {
            result.append(input.nextLine());
        }

        //Parsing v1.0

        JSONObject jsonObject = new JSONObject(result.toString());
        model.setName(jsonObject.getString("name"));

        JSONObject mainPart = jsonObject.getJSONObject("main");
        model.setTemp(mainPart.getDouble("temp"));
        //null values


        model.setHumidity(mainPart.getDouble("humidity"));
        model.setFeelsTemp(mainPart.getDouble("feels_like"));
        JSONArray weatherPart = jsonObject.getJSONArray("weather");


        for (int i = 0; i < weatherPart.length(); i++) {
            JSONObject objectInArray = weatherPart.getJSONObject(0); // zero for getting first example
            model.setDescription(objectInArray.getString("description"));
            model.setMain(objectInArray.getString("main"));
            model.setIcon(objectInArray.getString("icon"));
        }



        /*

        String json = String.valueOf(result);
        Weather weather = Weather.ofRoot(GSON.fromJson(json, WeatherModel.class));
        System.out.println(weather.getHumidity());

         */
        return "City: " + model.getName() + "\n" +
                "Temp: " + model.getTemp() + " C\n" +
                "Feels like: " + model.getFeelsTemp() + "\n" +
                "Humidity: " + model.getHumidity() + "%\n" +
                "Main: " + model.getMain() + "\n" +
                "Description: " + model.getDescription() + "\n" +
                "Icon: " + model.getIcon()
                ;
    }
}