public class Model {

    private Double feelsTemp;
    private Double temp;
    private Double humidity;
    private String icon;
    private String main;
    private String description;

    private String name;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    // TODO: add pictures to reply
    public String getIcon() {
        return icon;
    }

    public Double getFeelsTemp() {
        return feelsTemp;
    }

    public void setFeelsTemp(Double feelsTemp) {
        this.feelsTemp = feelsTemp;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
