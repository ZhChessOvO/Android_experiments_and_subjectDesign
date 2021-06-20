package bjfu.it.caozehao.starbuzz;

import androidx.annotation.NonNull;

public class Food {
    private String name;
    private String description;
    private int imageResourseId;

    public String getDescription() {
        return description;
    }

    public int getImageResourseId() {
        return imageResourseId;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public Food(String name, String description, int imageResourseId) {
        this.name = name;
        this.description = description;
        this.imageResourseId = imageResourseId;
    }

    public String getName() {
        return name;
    }

    public static final Food[] foods = {
            new Food("Cake","A piece of sweet bread",R.drawable.latte),
            new Food("Chip","Nice fried potatoes",R.drawable.cappuccino),
            new Food("Ice cream", "Highest quality milk and ice",R.drawable.filter)
    };
}
