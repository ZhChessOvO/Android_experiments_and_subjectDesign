package bjfu.it.caozehao.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class BookMenu extends AppCompatActivity {

    private Cursor cursor;

    private int chartNum = 0;

    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_menu);

        ListView listDrinks = findViewById(R.id.booklist);
        SQLiteOpenHelper data = new DatabaseHelper(this);
        try (SQLiteDatabase db = data.getReadableDatabase()) {
            cursor = db.query("BOOK", new String[]{"_id", "NAME"},
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
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> listDrinks,
                                            View itemView,
                                            int position,
                                            long id) {
                        Intent intent = new Intent(BookMenu.this,
                                Book.class);
                        intent.putExtra(Book.EXTRA_BOOKID, (int) id);
                        startActivity(intent);
                    }
                };
        listDrinks.setOnItemClickListener(itemClickListener);

        try {
            SQLiteDatabase db = data.getReadableDatabase();
            Cursor cursor = db.query("CART", null, null, null, null, null, null);
            chartNum = cursor.getCount();
            cursor.close();
        } catch (Exception e) {
            chartNum = 0;
            e.printStackTrace();
        }


        ImageView imageView = findViewById(R.id.imageView);
        if (chartNum == 0) {
            imageView.setImageResource(R.drawable.chart0);
        } else if (chartNum == 1) {
            imageView.setImageResource(R.drawable.chart1);
        } else if (chartNum == 2) {
            imageView.setImageResource(R.drawable.chart2);
        } else {
            imageView.setImageResource(R.drawable.chart3);
        }
    }

    public void onClick6(View view) {
        SQLiteOpenHelper data = new DatabaseHelper(this);

        try (SQLiteDatabase db = data.getWritableDatabase()) {
            ContentValues drinkValues = new ContentValues();
            drinkValues.put("IMAGE_RESOURCE_ID", R.drawable.book1);
            drinkValues.put("NAME", "美的历程");
            drinkValues.put("PRICE", 41);
            db.insert("CART", null, drinkValues);
            //弹出提示
            Toast toast = Toast.makeText(this, "加入购物车成功", Toast.LENGTH_SHORT);
            toast.show();

            //更改购物车图标
            chartNum++;
            ImageView imageView = findViewById(R.id.imageView);
            if (chartNum == 0) {
                imageView.setImageResource(R.drawable.chart0);
            } else if (chartNum == 1) {
                imageView.setImageResource(R.drawable.chart1);
            } else if (chartNum == 2) {
                imageView.setImageResource(R.drawable.chart2);
            } else {
                imageView.setImageResource(R.drawable.chart3);
            }

        } catch (SQLiteException e) {
            e.printStackTrace();
        }

    }

    public void onClick7(View view) {
        SQLiteOpenHelper data = new DatabaseHelper(this);

        try (SQLiteDatabase db = data.getWritableDatabase()) {
            ContentValues drinkValues = new ContentValues();
            drinkValues.put("IMAGE_RESOURCE_ID", R.drawable.book2);
            drinkValues.put("NAME", "巨流河");
            drinkValues.put("PRICE", 37);
            db.insert("CART", null, drinkValues);
            //弹出提示
            Toast toast = Toast.makeText(this, "加入购物车成功", Toast.LENGTH_SHORT);
            toast.show();

            //更改购物车图标
            chartNum++;
            ImageView imageView = findViewById(R.id.imageView);
            if (chartNum == 0) {
                imageView.setImageResource(R.drawable.chart0);
            } else if (chartNum == 1) {
                imageView.setImageResource(R.drawable.chart1);
            } else if (chartNum == 2) {
                imageView.setImageResource(R.drawable.chart2);
            } else {
                imageView.setImageResource(R.drawable.chart3);
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void onClick8(View view) {

        SQLiteOpenHelper data = new DatabaseHelper(this);

        try (SQLiteDatabase db = data.getWritableDatabase()) {
            ContentValues drinkValues = new ContentValues();
            drinkValues.put("IMAGE_RESOURCE_ID", R.drawable.book3);
            drinkValues.put("NAME", "人间词话七讲");
            drinkValues.put("PRICE", 62);
            db.insert("CART", null, drinkValues);
            //弹出提示
            Toast toast = Toast.makeText(this, "加入购物车成功", Toast.LENGTH_SHORT);
            toast.show();

            //更改购物车图标
            chartNum++;
            ImageView imageView = findViewById(R.id.imageView);
            if (chartNum == 0) {
                imageView.setImageResource(R.drawable.chart0);
            } else if (chartNum == 1) {
                imageView.setImageResource(R.drawable.chart1);
            } else if (chartNum == 2) {
                imageView.setImageResource(R.drawable.chart2);
            } else {
                imageView.setImageResource(R.drawable.chart3);
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }
}
