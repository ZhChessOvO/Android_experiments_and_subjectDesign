package bjfu.it.caozehao.programadviser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final ProgramExpert expert = new ProgramExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButton(View button){
        Spinner spinner = findViewById(R.id.feature);
        String feature = spinner.getSelectedItem().toString();
        String language = expert.getLanguage(feature);
        TextView textView = findViewById(R.id.language);
        textView.setText(language);
    }

}
