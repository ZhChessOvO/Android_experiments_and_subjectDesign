package bjfu.it.caozehao.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StoreCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_category);

        ArrayAdapter<Store> listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,Store.stores);

        ListView listDrinks = findViewById(R.id.list_drinks);
        listDrinks.setAdapter(listAdapter);

        AdapterView.OnItemClickListener onItemClickListener=new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(StoreCategoryActivity.this,StoreActivity.class);
                intent.putExtra(StoreActivity.EXTRA_DRINKID,(int)id);
                startActivity(intent);
            }
        };
        listDrinks.setOnItemClickListener(onItemClickListener);
    }
}
