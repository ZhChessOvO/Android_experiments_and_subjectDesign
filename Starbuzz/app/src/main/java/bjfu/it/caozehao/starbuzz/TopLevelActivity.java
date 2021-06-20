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
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.List;

public class TopLevelActivity extends AppCompatActivity {

    private Cursor favoritesCursor;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

        setupOptionsListView();
        setupFavoritesListView();

    }

    private void setupOptionsListView() {
        ListView listView = findViewById(R.id.list_options);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(TopLevelActivity.this, DrinkCategoryActivity.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(TopLevelActivity.this, FoodCategoryActivity.class);
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(TopLevelActivity.this, StoreCategoryActivity.class);
                    startActivity(intent);
                }
            }
        };
        listView.setOnItemClickListener(itemClickListener);
    }

    private void setupFavoritesListView(){
        ListView listFavorites = findViewById(R.id.list_favorites);

        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);

        try{
            db=starbuzzDatabaseHelper.getReadableDatabase();
            favoritesCursor = db.query("DRINK",
                    new String[]{"_id","NAME"},
                    "" +
                            "FAVORITE=1",
                    null,null,null,null);
            CursorAdapter favoriteAdapter=
                    new SimpleCursorAdapter(TopLevelActivity.this,
                            android.R.layout.simple_list_item_1,favoritesCursor,
                            new String[]{"NAME"},
                            new int[]{android.R.id.text1},0);
            listFavorites.setAdapter(favoriteAdapter);
        }catch (SQLiteException e){
            Log.e("sqlite",e.getMessage());
            Toast toast= Toast.makeText(this,"Database unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }

        listFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TopLevelActivity.this,DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINKID,(int)id);
                startActivity(intent);
            }
        });
    }

    public void onDestroy(){
        super.onDestroy();
        favoritesCursor.close();
        db.close();
    }

    public void onRestart(){
        super.onRestart();
        Cursor newCursor=db.query("drink",
                new String[]{"_id","NAME"},"FAVORITE=1",
                null,null,null,null);
        ListView listFavorites=findViewById(R.id.list_favorites);
        CursorAdapter adapter=(CursorAdapter)listFavorites.getAdapter();
        adapter.changeCursor(newCursor);
        favoritesCursor=newCursor;
    }
}

