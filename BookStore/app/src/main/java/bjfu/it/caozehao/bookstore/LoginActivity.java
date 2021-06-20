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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static int mode = 1; //1密码登录，2验证码登录，3用户注册
    private static final String code = "071487";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    //登录
                    //啥都不做
                }else{
                    //注册
                    //切换页面
                    Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(onItemSelectedListener);


        RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(mode==2){
                    mode=1;
                    edit();
                }else{
                    mode=2;
                    edit();
                }
            }
        };
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    public void edit(){
        if(mode==1){
            TextView textView = findViewById(R.id.password);
            textView.setText("登录密码：");
            Button button = findViewById(R.id.forgetButton);
            button.setText("忘记密码");
        }else if(mode==2){
            TextView textView = findViewById(R.id.password);
            textView.setText("验证码");
            Button button = findViewById(R.id.forgetButton);
            button.setText("获取验证码");
        }else{

        }
    }

    public void onClickForget(View view){
        if(mode==1){
            //忘记密码
            //跳转至忘记密码页面
            Intent intent = new Intent(LoginActivity.this,ForgetPassword.class);
            EditText editText = (EditText) findViewById(R.id.editText);
            String s = editText.getText().toString();
            intent.putExtra("phone",s);
            startActivity(intent);

        }else{
            //获取验证码
            //弹出提示
            Toast toast= Toast.makeText(this,"验证码为"+code,Toast.LENGTH_SHORT);
            toast.show();

        }
    }

    public void onClickLog(View view){
        boolean success = false;
        //获取用户信息
        EditText editText = (EditText) findViewById(R.id.editText);
        String name = editText.getText().toString();
        editText = (EditText) findViewById(R.id.editText2);
        String password = editText.getText().toString();

        if(mode==1){
            //密码

            //查找用户信息

            Cursor cursor;
            SQLiteOpenHelper data = new DatabaseHelper(this );
            try{
                SQLiteDatabase db = data.getReadableDatabase();
                db= data.getReadableDatabase();
                cursor = db.query("USER",
                        new String[]{"PASSWORD"},
                        "NAME="+name,
                        null,null,null,null);
                cursor.moveToFirst();
                if(cursor.getString(cursor.getColumnIndex("PASSWORD")).equals(password)){
                    success = true;
                }else {
                    //弹出提示
                    Toast toast= Toast.makeText(this,"账号或密码有误！请重新输入",Toast.LENGTH_SHORT);
                    toast.show();
                }

            }catch (SQLiteException e){
                Log.e("sqlite",e.getMessage());
                Toast toast= Toast.makeText(this,"Database unavailable",Toast.LENGTH_SHORT);
                toast.show();
            }
        }else{
            //验证码
            if(password.equals(code)){
                success = true;
            }else{
                //弹出提示
                Toast toast= Toast.makeText(this,"验证码有误！请重新输入",Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        if(success){
            //修改数据库登录状态
            ContentValues drinkValues = new ContentValues();
            drinkValues.put("LOGIN",1);

            SQLiteOpenHelper data =new DatabaseHelper(this);

            try(SQLiteDatabase db = data.getWritableDatabase()){
                int row = db.update("LOG",drinkValues,"LOGIN=?",new String[]{"0"});
                Log.d("sqlite","update row"+row);
            }catch (SQLiteException e){
                e.printStackTrace();
            }

            //弹出提示
            Toast toast= Toast.makeText(this,"用户"+name+"登录成功",Toast.LENGTH_SHORT);
            toast.show();

            //跳转至登录成功界面
            Intent intent = new Intent(LoginActivity.this,UserSuccess.class);
            startActivity(intent);
        }
    }
}
