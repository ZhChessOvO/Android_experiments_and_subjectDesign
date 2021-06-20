package bjfu.it.caozehao.weather.bean;

public class CityInfoWeather {
    private WeatherInfo weatherinfo;
    public WeatherInfo getWeatherinfo(){
        return weatherinfo;
    }
    public void setWeatherinfo(WeatherInfo weatherinfo){this.weatherinfo=weatherinfo;}

    public class WeatherInfo{
        private String weather;

        public String getWeather(){
            return weather;
        }
        public void setWeather(String weather){
            this.weather=weather;
        }
    }

}
