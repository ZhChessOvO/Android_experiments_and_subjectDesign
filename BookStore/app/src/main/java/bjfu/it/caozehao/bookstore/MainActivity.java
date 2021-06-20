package bjfu.it.caozehao.bookstore;

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

public class MainActivity extends AppCompatActivity {
    private Cursor favoritesCursor;
    private SQLiteDatabase db;
    private int chartNum = 0; //购物车商品数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupOptionsListView();
        setupFavoritesListView();
        setupChart();

    }

    private void setupChart() {
        //读取数据库，获得表格行数存给chartNum
        SQLiteOpenHelper data = new DatabaseHelper(this);
        try {
            db = data.getReadableDatabase();
            Cursor cursor = db.query("CART", null, null, null, null, null, null);
            chartNum = cursor.getCount();
            cursor.close();
        } catch (Exception e) {
            chartNum = 0;
            e.printStackTrace();
        }

    }

    private void setupOptionsListView() {
        ListView listView = findViewById(R.id.typelist);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(MainActivity.this, BookMenu.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(MainActivity.this, MagazineMenu.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, NewsMenu.class);
                    startActivity(intent);
                }
            }
        };
        listView.setOnItemClickListener(itemClickListener);
    }

    private void setupFavoritesListView() {

        ListView listFavorites = findViewById(R.id.favorlist);

        SQLiteOpenHelper data = new DatabaseHelper(this);

        try {
            db = data.getReadableDatabase();
            favoritesCursor = db.query("BOOK",
                    new String[]{"_id", "NAME"},
                    "" +
                            "FAVORITE=1",
                    null, null, null, null);
            CursorAdapter favoriteAdapter =
                    new SimpleCursorAdapter(MainActivity.this,
                            android.R.layout.simple_list_item_1, favoritesCursor,
                            new String[]{"NAME"},
                            new int[]{android.R.id.text1}, 0);
            listFavorites.setAdapter(favoriteAdapter);
        } catch (SQLiteException e) {
            Log.e("sqlite", e.getMessage());
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }


        listFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Book.class);
                intent.putExtra(Book.EXTRA_BOOKID, (int) id);
                startActivity(intent);
            }
        });

        //杂志
        ListView listFavorites2 = findViewById(R.id.favorlist2);

        try {
            db = data.getReadableDatabase();
            favoritesCursor = db.query("MAGAZINE",
                    new String[]{"_id", "NAME"},
                    "" +
                            "FAVORITE=1",
                    null, null, null, null);
            CursorAdapter favoriteAdapter =
                    new SimpleCursorAdapter(MainActivity.this,
                            android.R.layout.simple_list_item_1, favoritesCursor,
                            new String[]{"NAME"},
                            new int[]{android.R.id.text1}, 0);
            listFavorites2.setAdapter(favoriteAdapter);
        } catch (SQLiteException e) {
            Log.e("sqlite", e.getMessage());
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }


        listFavorites2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Magazine.class);
                intent.putExtra(Magazine.EXTRA_BOOKID, (int) id);
                startActivity(intent);
            }
        });

        //新闻
        ListView listFavorites3 = findViewById(R.id.favorlist3);

        try {
            db = data.getReadableDatabase();
            favoritesCursor = db.query("NEWSPAPER",
                    new String[]{"_id", "NAME"},
                    "" +
                            "FAVORITE=1",
                    null, null, null, null);
            CursorAdapter favoriteAdapter =
                    new SimpleCursorAdapter(MainActivity.this,
                            android.R.layout.simple_list_item_1, favoritesCursor,
                            new String[]{"NAME"},
                            new int[]{android.R.id.text1}, 0);
            listFavorites3.setAdapter(favoriteAdapter);
        } catch (SQLiteException e) {
            Log.e("sqlite", e.getMessage());
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }


        listFavorites3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, News.class);
                intent.putExtra(News.EXTRA_BOOKID, (int) id);
                startActivity(intent);
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        favoritesCursor.close();
        db.close();
    }

    public void onRestart() {
        super.onRestart();
        Cursor newCursor = db.query("BOOK",
                new String[]{"_id", "NAME"}, "FAVORITE=1",
                null, null, null, null);
        ListView listFavorites = findViewById(R.id.favorlist);
        CursorAdapter adapter = (CursorAdapter) listFavorites.getAdapter();
        adapter.changeCursor(newCursor);

        favoritesCursor = newCursor;

        try {
            Cursor cursor = db.query("CART", null, null, null, null, null, null);
            chartNum = cursor.getCount();
            cursor.close();
        } catch (Exception e) {
            chartNum = 0;
            e.printStackTrace();
        }
    }

    public void onClick1(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
//        intent.putExtra(News.EXTRA_BOOKID,(int)id);
        startActivity(intent);
    }

    public void onClick3(View view) {

        Cursor cursor;

        SQLiteOpenHelper data = new DatabaseHelper(this);
        try {
            db = data.getReadableDatabase();
            cursor = db.query("LOG",
                    new String[]{"LOGIN"},
                    null,
                    null, null, null, null);
            cursor.moveToFirst();


            if (cursor.getInt(cursor.getColumnIndex("LOGIN")) == 0) {
//                Toast toast= Toast.makeText(this,"0",Toast.LENGTH_SHORT);
//                toast.show();

                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            } else {
//                Toast toast= Toast.makeText(this,"1",Toast.LENGTH_SHORT);
//                toast.show();

                Intent intent = new Intent(MainActivity.this, UserSuccess.class);
                startActivity(intent);
            }

        } catch (SQLiteException e) {
            Log.e("sqlite", e.getMessage());
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }


    }

    public void onClickChart(View view) {
        if (chartNum == 0) {
            Intent intent = new Intent(MainActivity.this, EmptyChartActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(MainActivity.this, ChartActivity.class);
            startActivity(intent);
        }
    }
}
