package bjfu.it.caozehao.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    private static boolean loged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState!=null){
            loged=savedInstanceState.getBoolean("loged");
        }

        setContentView(R.layout.activity_user);
        TextView textView = findViewById(R.id.loginstatus);
        Button button = findViewById(R.id.loginbutton);
        if(loged){
            button.setText("退出登录");
        }else{
            textView.setText("未登录");
        }
    }

    public void onClickLogin(View view){
//        if(loged){
//            loged = false;
//        }else{
//            loged = true;
//        }
        Intent intent = new Intent(UserActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    public void onClick1(View view){
        Intent intent = new Intent(UserActivity.this,MainActivity.class);
//        intent.putExtra(News.EXTRA_BOOKID,(int)id);
        startActivity(intent);
    }

//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState){
//        super.onSaveInstanceState(savedInstanceState);
//        savedInstanceState.putBoolean("loged",loged);
//    }
}
