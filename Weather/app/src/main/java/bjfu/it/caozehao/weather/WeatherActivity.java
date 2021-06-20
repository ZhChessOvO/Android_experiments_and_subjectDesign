package bjfu.it.caozehao.weather;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import bjfu.it.caozehao.weather.bean.CityInfoWeather;
import bjfu.it.caozehao.weather.bean.SKWeather;
import bjfu.it.caozehao.weather.network.ApiService;
import bjfu.it.caozehao.weather.utils.CityHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {
    private TextView cityNameTv;
    private TextView tempTv;
    private TextView weatherTv;
    private TextView windDirectionTv;
    private TextView windSpeedTv;
    private TextView humidityTv;
    private TextView baroTv;
    private String cityId;
    ApiService apiService;
    ApiService apiService_ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        cityNameTv=findViewById(R.id.cityName);
        tempTv=findViewById(R.id.temp);
        weatherTv=findViewById(R.id.weather);
        windDirectionTv=findViewById(R.id.winddirect);
        windSpeedTv=findViewById(R.id.windspeed);
        humidityTv=findViewById(R.id.humidity);
        baroTv=findViewById(R.id.baro);

        cityNameTv.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WeatherActivity.this, CityListActivity.class);
                startActivityForResult(intent, CityListActivity.CITY_ID_RESULT_CODE);
            }
        });
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.weather.com.cn").addConverterFactory(GsonConverterFactory.create()).build();
        apiService= retrofit.create(ApiService.class);
        refresh(CityHelper.getDefaultCityId());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CityListActivity.CITY_ID_RESULT_CODE){
            if(data != null){
                String cityId=data.getStringExtra(CityListActivity.CITY_ID_KEY);
                refresh(cityId);
            }
        }
    }
    private void refresh(String cityId){
        if(TextUtils.isEmpty(cityId)||cityId.equals(this.cityId)){
            return;
        }
        Call<CityInfoWeather> cityInfoCall= apiService.getCityInfoWeather(cityId);
        cityInfoCall.enqueue(new Callback<CityInfoWeather>(){
            @Override
            public void onResponse(Call<CityInfoWeather> call, final Response<CityInfoWeather> cityInfoResponse) {
                Call<SKWeather> skCall=apiService.getSKWeather(cityId);
                skCall.enqueue(new Callback<SKWeather>() {
                    @Override
                    public void onResponse(Call<SKWeather> call, Response<SKWeather> skResponse) {
                        CityInfoWeather cityInfoWeather= cityInfoResponse.body();
                        SKWeather skWeather=skResponse.body();
                        WeatherActivity.this.cityId=cityId;
                        cityNameTv.setText(getString(R.string.city_name_str,CityHelper.getInstance().getCityInfoMap().get(cityId).getName()));
                        tempTv.setText(getString(R.string.temp_str,skWeather.getWeatherInfo().getTemp()));
                        weatherTv.setText(cityInfoWeather.getWeatherinfo().getWeather());
                        windDirectionTv.setText(getString(R.string.wd_str, skWeather.getWeatherInfo().getWD()));
                        windSpeedTv.setText(getString(R.string.ws_str, skWeather.getWeatherInfo().getWS()));
                        humidityTv.setText(getString(R.string.humidity_str, skWeather.getWeatherInfo().getWD()));
                        baroTv.setText(getString(R.string.baro_str, skWeather.getWeatherInfo().getBaro()));
                    }
                    @Override
                    public void onFailure(Call<SKWeather> call, Throwable t) {
                        Toast.makeText(WeatherActivity.this,"请求SK接口失败", Toast.LENGTH_LONG).show();
                    }
                });
            }
            @Override
            public void onFailure(Call<CityInfoWeather> call, Throwable t) {
                Toast.makeText(WeatherActivity.this,"请求CityInfo接口失败", Toast.LENGTH_LONG).show();
            }

        });
    }
}
