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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Magazine extends AppCompatActivity {

    public static final String EXTRA_BOOKID = "bookId";

    private int chartNum = 0;

    private int photoId;
    private String bookName;
    private int bookPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magazine);
        //Get the drink from the intent
        int drinkId=getIntent().getIntExtra(EXTRA_BOOKID,0);

        SQLiteOpenHelper data=new DatabaseHelper(this);
        //try with resource 自动关闭database
        try(SQLiteDatabase db=data.getReadableDatabase()){
            Cursor cursor=db.query("MAGAZINE",
                    new String[] {"NAME","DESCRIPTION","IMAGE_RESOURCE_ID","FAVORITE","PRICE"},
                    "_id=?",
                    new String[] {Integer.toString(drinkId)},
                    null,null,null);
            if(cursor.moveToFirst()){//导航至游标第一条记录
                //取出记录的1-3列
                bookName=cursor.getString(0);
                String descriptionText=cursor.getString(1);
                photoId=cursor.getInt(2);
                boolean isFavorite = (cursor.getInt(3)==1);
                bookPrice = cursor.getInt(4);
                //显示name
                TextView name=findViewById(R.id.name);
                name.setText(bookName);
                TextView description=findViewById(R.id.info);
                description.setText(descriptionText);
                ImageView photo=findViewById(R.id.bookpreview);
                photo.setImageResource(photoId);
                photo.setContentDescription(bookName);

                TextView textView=findViewById(R.id.textView2);
                textView.setText("单价："+bookPrice+"RMB");

                CheckBox favorite = findViewById(R.id.favor);
                favorite.setChecked(isFavorite);
            }
            cursor.close();
        }catch (SQLiteException e){
            Log.e("sqlite",e.getMessage());
            Toast toast= Toast.makeText(this,"Database unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }

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

    public void onFavoriteClicked(View view) {
        CheckBox favorite = (CheckBox)view;
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("FAVORITE",favorite.isChecked());

        SQLiteOpenHelper data =new DatabaseHelper(this);

        int drinkId=getIntent().getIntExtra(EXTRA_BOOKID,0);
        try(SQLiteDatabase db = data.getWritableDatabase()){
            int row = db.update("MAGAZINE",drinkValues,"_id=?",new String[]{Integer.toString(drinkId)});
            Log.d("sqlite","update row"+row);
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }

    public void onClickAdd(View view) {
        SQLiteOpenHelper data = new DatabaseHelper(this);

        try (SQLiteDatabase db = data.getWritableDatabase()) {
            ContentValues drinkValues = new ContentValues();
            drinkValues.put("IMAGE_RESOURCE_ID", photoId);
            drinkValues.put("NAME", bookName);
            drinkValues.put("PRICE", bookPrice);
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

    public void onClickCart(View view) {
        Intent intent = new Intent(Magazine.this, ChartActivity.class);
        startActivity(intent);
    }
}
