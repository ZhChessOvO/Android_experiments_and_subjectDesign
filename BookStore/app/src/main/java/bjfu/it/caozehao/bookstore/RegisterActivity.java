package bjfu.it.caozehao.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private static int mode = 3; //1密码登录，2验证码登录，3用户注册
    private static final String currentCode = "234708";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    //注册
                    //啥也不做

                }else{
                    //登录
                    //切换页面
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(onItemSelectedListener);

    }

    public void onClickForget(View view){
            Toast toast= Toast.makeText(this,"验证码为"+currentCode,Toast.LENGTH_SHORT);
            toast.show();
    }

    public void onClickLog(View view){
        boolean success = false;

        EditText editText = (EditText) findViewById(R.id.editText3);
        String code = editText.getText().toString();
        if(code.equals(currentCode)){
            success = true;
        }else{
            //弹出提示
            Toast toast= Toast.makeText(this,"验证码有误！请重新输入",Toast.LENGTH_SHORT);
            toast.show();
        }
        if(success){
            SQLiteOpenHelper data =new DatabaseHelper(this);

            //获取注册信息
            editText = (EditText) findViewById(R.id.editText);
            String name = editText.getText().toString();
            editText = (EditText) findViewById(R.id.editText2);
            String password = editText.getText().toString();

            //添加用户信息

            ContentValues userValues = new ContentValues();
            userValues.put("NAME",name);
            userValues.put("PASSWORD",password);

            try(SQLiteDatabase db = data.getWritableDatabase()){
                db.insert("USER",null,userValues);
            }catch (SQLiteException e){
                e.printStackTrace();
            }

            //修改数据库登录状态
            ContentValues drinkValues = new ContentValues();
            drinkValues.put("LOGIN",1);



            try(SQLiteDatabase db = data.getWritableDatabase()){
                int row = db.update("LOG",drinkValues,"LOGIN=?",new String[]{"0"});
                Log.d("sqlite","update row"+row);
            }catch (SQLiteException e){
                e.printStackTrace();
            }

            //弹出提示
            Toast toast= Toast.makeText(this,"用户"+name+"注册成功，已为您自动登录",Toast.LENGTH_SHORT);
            toast.show();

            //跳转至登录成功界面
            Intent intent = new Intent(RegisterActivity.this,UserSuccess.class);
            startActivity(intent);
        }
    }
}
