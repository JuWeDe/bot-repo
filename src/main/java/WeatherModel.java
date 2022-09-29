//@lombok.Data
/*
public class WeatherModel {

    public Coord coord;
    public List<Weather> weather;
    public String base;
    public Main main;
    public Integer visibility;
    public Wind wind;
    public Clouds clouds;
    public Integer dt;
    public Sys sys;
    public Integer timezone;
    public Integer id;
    public String name;
    public Integer cod;

    //@lombok.Data
    public static class Coord {
        public Double lon;
        public Double lat;
    }

    //@lombok.Data
    public static class Weather {

        public Integer id;
        public String main;
        public String description;
        public String icon;
    }

    //@lombok.Data
    public static class Main {

        public Double temp;
        public Double feels_like;
        public Double temp_min;
        public Integer temp_max;
        public Integer pressure;
        public Integer humidity;
    }

    //@lombok.Data
    public static class Wind {

        public Integer speed;
        public Integer deg;
    }

    //@lombok.Data
    public static class Clouds {

        public Integer all;
    }

    //@lombok.Data
    public static class Sys {

        public Integer type;
        public Integer id;
        public String country;
        public Integer sunrise;
        public Integer sunset;
    }

}


//@lombok.Data
class Weather {

    private Double temp;
    private Double tempMin;
    private String humidity;
/*
    public static Weather ofRoot(WeatherModel weatherModel) {
        Weather result = new Weather();
        result.temp = weatherModel.getMain().getTemp();
        result.tempMin = weatherModel.getMain().getFeels_like();
        result.humidity = weatherModel.getWeather().stream()
                .map(WeatherModel.Weather::getDescription).collect(Collectors.joining("; "));
        return result;
    }
}
 */





