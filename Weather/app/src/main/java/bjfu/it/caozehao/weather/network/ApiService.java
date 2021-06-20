package bjfu.it.caozehao.weather.network;

import bjfu.it.caozehao.weather.bean.CityInfoWeather;
import bjfu.it.caozehao.weather.bean.SKWeather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    public static final String Host_WEATHER="http://www.weather.com.cn";

    @GET("/data/cityinfo/{cityId}.html")
    Call<CityInfoWeather> getCityInfoWeather(@Path("cityId")String cityId);
    @GET("/data/sk/{cityId}.html")
    Call<SKWeather> getSKWeather(@Path("cityId")String cityId);
}
