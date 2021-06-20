package bjfu.it.caozehao.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodActivity extends AppCompatActivity {

    public static final String EXTRA_DRINKID ="drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        int drinkId=getIntent().getIntExtra(EXTRA_DRINKID,0);
        Food food =Food.foods[drinkId];

        ImageView photo = findViewById(R.id.photo);
        photo.setImageResource(food.getImageResourseId());
        photo.setContentDescription(food.getName());

        TextView name=findViewById(R.id.name);
        name.setText(food.getName());

        TextView description = findViewById(R.id.description);
        description.setText(food.getDescription());
    }
}
