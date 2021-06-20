package bjfu.it.caozehao.starbuzz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StarbuzzDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="starbuzz.db";
    private static final int DB_VERSION=3;

    public StarbuzzDatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE DRINK(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"NAME TEXT,"
                +"DESCRIPTION TEXT,"
                +"IMAGE_RESOURCE_ID INTEGER,"
                +"FAVORITE NUMERIC);"
        );
        insertDrink(db,"Latte","Espresso and steamed milk",R.drawable.latte);
        insertDrink(db,"Cappuccino","Espresso, hot milk and steamed-milk foam",R.drawable.cappuccino);
        insertDrink(db,"Filter","Our best drip coffee",R.drawable.filter);

        //food表
        db.execSQL("CREATE TABLE FOOD(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"NAME TEXT,"
                +"DESCRIPTION TEXT,"
                +"IMAGE_RESOURCE_ID INTEGER,"
                +"FAVORITE NUMERIC);"
        );
        insertFood(db,"Cake","A piece of sweet bread",R.drawable.latte);
        insertFood(db,"Chip","Nice fried potatoes",R.drawable.cappuccino);
        insertFood(db,"Ice cream","Highest quality milk and ice",R.drawable.filter);
    }

    private static void insertDrink(SQLiteDatabase db,String name,String description,int resourceld){
        ContentValues drinkValues=new ContentValues();
        drinkValues.put("NAME",name);
        drinkValues.put("DESCRIPTION",description);
        drinkValues.put("IMAGE_RESOURCE_ID",resourceld);
        long result=db.insert("DRINK",null,drinkValues);
        Log.d("sqlite","insert"+name+",_id"+result);
    }

    private static void insertFood(SQLiteDatabase db,String name,String description,int resourceld){
        ContentValues drinkValues=new ContentValues();
        drinkValues.put("NAME",name);
        drinkValues.put("DESCRIPTION",description);
        drinkValues.put("IMAGE_RESOURCE_ID",resourceld);
        long result=db.insert("FOOD",null,drinkValues);
        Log.d("sqlite","insert"+name+",_id"+result);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
            if(oldVersion<=1){
                db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
            }
            if(oldVersion<=2){
                ContentValues latteDesc=new ContentValues();
                latteDesc.put("DESCRIPTION","Tasty");
                db.update("DRINK",latteDesc,"NAME=?",new String[]{"Latte"});
            }
    }
}
