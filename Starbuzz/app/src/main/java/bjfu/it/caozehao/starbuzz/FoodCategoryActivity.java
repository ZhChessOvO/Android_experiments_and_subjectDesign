package bjfu.it.caozehao.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class FoodCategoryActivity extends AppCompatActivity {

    private Cursor cursor;
    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_category);
//        ArrayAdapter<Drink> listAdapter=new ArrayAdapter<>(
//                this,android.R.layout.simple_list_item_1,Drink.drinks);
//        ListView listDrinks=findViewById(R.id.list_drinks);
//        listDrinks.setAdapter(listAdapter);
        //Create the listener
//       OnItemClickListener itemClickListener=
//                new OnItemClickListener(){
//                    @Override
//                    public void onItemClick(AdapterView<?> listDrinks,
//                        View itemView,
//                        int position,
//                        long id){
//                        Intent intent=new Intent(DrinkCategoryActivity.this,
//                                DrinkActivity.class);
//                        intent.putExtra(DrinkActivity.EXTRA_DRINKID,(int)id);
//                        startActivity(intent);
//                    }
//                };
//       listDrinks.setOnItemClickListener(itemClickListener);
        ListView listDrinks = findViewById(R.id.list_drinks);
        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
        try (SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase()) {
            cursor = db.query("FOOD", new String[]{"_id", "NAME"},
                    null, null, null, null, null);
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0);

            listDrinks.setAdapter(listAdapter);
//            cursor.close();
        } catch (SQLiteException e) {
            Log.e("sqlite", e.getMessage());
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        //Create the listener
        OnItemClickListener itemClickListener =
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> listDrinks,
                                            View itemView,
                                            int position,
                                            long id) {
                        Intent intent = new Intent(FoodCategoryActivity.this,
                                FoodActivity.class);
                        intent.putExtra(FoodActivity.EXTRA_DRINKID, (int) id);
                        startActivity(intent);
                    }
                };
        listDrinks.setOnItemClickListener(itemClickListener);
    }
}