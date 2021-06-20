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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ChartActivity extends AppCompatActivity {

    private int chartNum = 0;
    private Cursor cursor;
    private int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        //初始化右上角图标
        SQLiteOpenHelper data = new DatabaseHelper(this);
        try (SQLiteDatabase db = data.getReadableDatabase()) {
            cursor = db.query("CART", new String[]{"_id", "IMAGE_RESOURCE_ID", "NAME", "PRICE"},
                    null, null, null, null, null);
            chartNum = cursor.getCount();

            ImageView imageView = findViewById(R.id.imageView2);
            if (chartNum == 0) {
                imageView.setImageResource(R.drawable.chart0);
            } else if (chartNum == 1) {
                imageView.setImageResource(R.drawable.chart1);
            } else if (chartNum == 2) {
                imageView.setImageResource(R.drawable.chart2);
            } else {
                imageView.setImageResource(R.drawable.chart3);
            }

            //设置可见
            if (chartNum < 3) {
                LinearLayout linearLayout = findViewById(R.id.line3);
                linearLayout.setVisibility(View.INVISIBLE);
            }
            if (chartNum < 2) {
                LinearLayout linearLayout = findViewById(R.id.line2);
                linearLayout.setVisibility(View.INVISIBLE);
            }

            //初始化信息
            cursor.moveToFirst();
            String name1 = cursor.getString(2);
            TextView textView = findViewById(R.id.textView11);
            textView.setText(name1);

            int price1 = cursor.getInt(3);
            totalPrice += price1;

            TextView textView1 = findViewById(R.id.textView13);
            textView1.setText(price1 + "");
            textView1 = findViewById(R.id.textView14);
            textView1.setText(price1 + "");

            int photo1 = cursor.getInt(1);
            ImageView imageView1 = findViewById(R.id.imageView4);
            imageView1.setImageResource(photo1);

            if (chartNum >= 2) {
                cursor.moveToNext();
                String name2 = cursor.getString(2);
                textView = findViewById(R.id.textView15);
                textView.setText(name2);

                price1 = cursor.getInt(3);
                totalPrice += price1;

                textView1 = findViewById(R.id.textView17);
                textView1.setText(price1 + "");
                textView1 = findViewById(R.id.textView18);
                textView1.setText(price1 + "");

                photo1 = cursor.getInt(1);
                imageView1 = findViewById(R.id.imageView5);
                imageView1.setImageResource(photo1);
            }


            if (chartNum >= 3) {
                cursor.moveToNext();
                String name2 = cursor.getString(2);
                textView = findViewById(R.id.textView19);
                textView.setText(name2);

                price1 = cursor.getInt(3);
                totalPrice += price1;

                textView1 = findViewById(R.id.textView21);
                textView1.setText(price1 + "");
                textView1 = findViewById(R.id.textView22);
                textView1.setText(price1 + "");

                photo1 = cursor.getInt(1);
                imageView1 = findViewById(R.id.imageView6);
                imageView1.setImageResource(photo1);
            }

            //总金额计算
            TextView textView2 = findViewById(R.id.textView23);
            textView2.setText("总金额：" + totalPrice + "元");


//            cursor.close();
        } catch (SQLiteException e) {
            Log.e("sqlite", e.getMessage());
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void onClickBuy(View view) {
        SQLiteOpenHelper data = new DatabaseHelper(this);
        try{
            SQLiteDatabase db = data.getWritableDatabase();
            db.execSQL("delete from CART");
        }catch (Exception e){
            e.printStackTrace();
        }
        //弹出提示
        Toast toast = Toast.makeText(this, "支付成功！", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(ChartActivity.this, EmptyChartActivity.class);
        startActivity(intent);
    }


}
