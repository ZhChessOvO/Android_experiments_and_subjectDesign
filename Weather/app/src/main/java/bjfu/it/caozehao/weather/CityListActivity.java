package bjfu.it.caozehao.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import bjfu.it.caozehao.weather.utils.CityHelper;

public class CityListActivity extends AppCompatActivity {
    public static final int CITY_ID_RESULT_CODE=1;
    public static final String CITY_ID_KEY="city_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        ListView listView=findViewById(R.id.list_view);
        List<String> cityNameList= CityHelper.getInstance().getCityNameList();
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cityNameList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String cityId=CityHelper.getInstance().getCityIdList().get(position);
                Intent intent=new Intent();
                intent.putExtra(CITY_ID_KEY,cityId);
                setResult(CITY_ID_RESULT_CODE,intent);
                finish();
            }
        });
    }
}