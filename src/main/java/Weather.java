import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {
    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+ message + "&units=metric&appid=bf40dbd4c7098b41743f52c421f87d82");
        Scanner input = new Scanner((InputStream) url.getContent());
        String result = "";
        while (input.hasNext()) {
            result += input.nextLine();
        }
        //String result1 = result;

        JSONObject jsonObject = new JSONObject(result);
        model.setName(jsonObject.getString("name"));

        JSONObject main = jsonObject.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray jsonArray = jsonObject.getJSONArray("weather");
/*
        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject objectInArray = jsonArray.getJSONObject(1);
            model.setIcon(objectInArray.getString("icon"));
            model.setMain(objectInArray.getString("main"));

        }
*/

        return //"City: "+model.getName()+"\n" +
                "Temp: "+model.getTemp() +" C" + "\n" +
                        "Humidity: "+model.getHumidity()+"%"+"\n";
//                +"Main: " + model.getMain()+"\n";
    }
}