package bjfu.it.caozehao.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class StoreActivity extends AppCompatActivity {

    public static final String EXTRA_DRINKID ="drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        int drinkId=getIntent().getIntExtra(EXTRA_DRINKID,0);
        Store store =Store.stores[drinkId];

        ImageView photo = findViewById(R.id.photo);
        photo.setImageResource(store.getImageResourseId());
        photo.setContentDescription(store.getName());

        TextView name=findViewById(R.id.name);
        name.setText(store.getName());

        TextView description = findViewById(R.id.description);
        description.setText(store.getDescription());
    }
}
