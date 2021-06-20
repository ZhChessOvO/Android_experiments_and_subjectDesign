package bjfu.it.caozehao.weather.bean;

public class SKWeather {
    private WeatherInfo weatherinfo;

    public WeatherInfo getWeatherInfo(){
        return weatherinfo;
    }
    public void setWeatherInfo(WeatherInfo weatherinfo){
        this.weatherinfo=weatherinfo;
    }
    public class WeatherInfo{
        private String temp;
        private String WD;
        private String WS;
        private String Humidity;
        private String Baro;
        public String getTemp(){
            return temp;
        }
        public void setTemp(String temp){
            this.temp=temp;
        }
        public String getWD(){
            return WD;
        }
        public void WD(String WD){
            this.WD=WD;
        }
        public String getWS(){
            return WS;
        }
        public void setWS(String WS){
            this.WS=WS;
        }
        public String getHumidity(){
            return Humidity;
        }
        public void setHumidity(String Humidity){
            this.Humidity=Humidity;
        }
        public String getBaro(){
            return Baro;
        }
        public void setBaro(String Baro){
            this.Baro=Baro;
        }
    }
}
