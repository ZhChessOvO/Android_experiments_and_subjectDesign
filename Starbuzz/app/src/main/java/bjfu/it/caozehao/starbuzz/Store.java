package bjfu.it.caozehao.starbuzz;

import androidx.annotation.NonNull;

public class Store {
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

    public Store(String name, String description, int imageResourseId) {
        this.name = name;
        this.description = description;
        this.imageResourseId = imageResourseId;
    }

    public String getName() {
        return name;
    }

    public static final Store[] stores = {
            new Store("Plastic Bag","A nice designed one",R.drawable.latte),
            new Store("Cup","Looking like kitty's paws",R.drawable.cappuccino),
            new Store("Instant coffee", "Make your own coffee at home!",R.drawable.filter)
    };
}
