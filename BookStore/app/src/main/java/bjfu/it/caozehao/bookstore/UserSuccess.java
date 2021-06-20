package bjfu.it.caozehao.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_success);
    }

    public void onClickLogout(View view){
        //退出登录
        //修改数据库登录状态
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("LOGIN",0);

        SQLiteOpenHelper data =new DatabaseHelper(this);

        try(SQLiteDatabase db = data.getWritableDatabase()){
            int row = db.update("LOG",drinkValues,"LOGIN=?",new String[]{"1"});
            Log.d("sqlite","update row"+row);
        }catch (SQLiteException e){
            e.printStackTrace();
        }

        //弹出提示
        Toast toast= Toast.makeText(this,"已退出登录",Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(UserSuccess.this, UserActivity.class);
        startActivity(intent);
    }

    public void onClick1(View view){
        Intent intent = new Intent(UserSuccess.this,MainActivity.class);
//        intent.putExtra(News.EXTRA_BOOKID,(int)id);
        startActivity(intent);
    }

    public void onClickSign(View view){
        EditText editText = findViewById(R.id.editText5);
        String userSign = editText.getText().toString();
        TextView textView = findViewById(R.id.textView24);
        textView.setText("个性签名："+userSign);
    }

    public void onClickMusic(View view){
        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.vindication);
        mediaPlayer.start();
    }
}
