package bjfu.it.caozehao.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EmptyChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_chart);
    }

    public void onClickGuang(View view){
        Intent intent = new Intent(EmptyChartActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
