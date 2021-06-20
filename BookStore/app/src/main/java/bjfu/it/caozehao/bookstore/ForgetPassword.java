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
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPassword extends AppCompatActivity {
    private static final String code = "234710";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        Intent intent = getIntent();
        String s = intent.getStringExtra("phone");
        EditText editText = findViewById(R.id.editText4);
        editText.setText(s);
    }

    public void onClickForget(View view){
        //弹出提示
        Toast toast= Toast.makeText(this,"验证码为"+code,Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onClickLog(View view){

        //获取用户信息
        EditText editText = (EditText) findViewById(R.id.editText);
        String password1 = editText.getText().toString();
        editText = (EditText) findViewById(R.id.editText2);
        String password2 = editText.getText().toString();
        editText = (EditText) findViewById(R.id.editText3);
        String yanzheng = editText.getText().toString();
        editText = (EditText) findViewById(R.id.editText4);
        String phone = editText.getText().toString();

        //进行判断
        if(yanzheng.equals(code)&&password1.equals(password2)){

            //数据库中定位

            ContentValues drinkValues = new ContentValues();
            drinkValues.put("PASSWORD",password1);

            SQLiteOpenHelper data =new DatabaseHelper(this);

            //修改密码
            try(SQLiteDatabase db = data.getWritableDatabase()){
                int row = db.update("USER",drinkValues,"NAME=?",new String[]{phone});
                Log.d("sqlite","update row"+row);
            }catch (SQLiteException e){
                e.printStackTrace();
            }

            //顺便帮忙登录
            //修改数据库登录状态
            ContentValues drinkValues2 = new ContentValues();
            drinkValues2.put("LOGIN",1);

            SQLiteOpenHelper data2 =new DatabaseHelper(this);

            try(SQLiteDatabase db2 = data2.getWritableDatabase()){
                int row = db2.update("LOG",drinkValues2,"LOGIN=?",new String[]{"0"});
                Log.d("sqlite","update row"+row);
            }catch (SQLiteException e){
                e.printStackTrace();
            }

            //弹出提示
            Toast toast= Toast.makeText(this,"用户"+phone+"修改密码成功，已为您自动登录",Toast.LENGTH_SHORT);
            toast.show();

            //跳转至登录成功界面
            Intent intent = new Intent(ForgetPassword.this,UserSuccess.class);
            startActivity(intent);

        }else{
            //弹出提示
            Toast toast= Toast.makeText(this,"验证码有误或两次密码不一致！请重新输入",Toast.LENGTH_SHORT);
            toast.show();
        }



    }
}
