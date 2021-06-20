package bjfu.it.caozehao.messager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);

        Intent intent=getIntent();
        String message=intent.getStringExtra(CreateMessageActivity.MESSAGE_KEY);
        TextView textView=findViewById(R.id.output);
        textView.setText(message);
    }
}
