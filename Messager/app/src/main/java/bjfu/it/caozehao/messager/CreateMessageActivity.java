package bjfu.it.caozehao.messager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateMessageActivity extends AppCompatActivity {

    public static final String MESSAGE_KEY = "bjfu.it.caozehao.messager";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
    }

    public void onSendMessage(View button) {
        EditText editText=findViewById(R.id.input);
        String message=editText.getText().toString();
//        Intent intent = new Intent(this,ReceiveMessageActivity.class);
//        intent.putExtra(MESSAGE_KEY,message);
//        startActivity(intent);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,message);
        startActivity(intent);
    }
}
