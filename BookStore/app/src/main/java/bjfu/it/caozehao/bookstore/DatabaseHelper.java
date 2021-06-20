package bjfu.it.caozehao.bookstore;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "bookstore.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE BOOK(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "NAME TEXT,"
                + "DESCRIPTION TEXT,"
                + "IMAGE_RESOURCE_ID INTEGER,"
                + "PRICE INTEGER,"
                + "FAVORITE NUMERIC);"
        );
        insertBook(db, "美的历程", "《美的历程》全书共分十章，每一章评述一个重要时期的艺术风神或某一艺术门类的发展。它并不是一部一般意义上的艺术史著作，重点不在于具体艺术作品的细部赏析，而是以人类学本体论的美学观把审美、艺术与整个历史进程有机地联系起来，点面结合，揭示出各种社会因素对于审美和艺术的作用和影响，对中国古典文艺的发展作出了概括性的分析与说明。", R.drawable.book1, 41);
        insertBook(db, "巨流河", "本书作者以逾八十高龄历时四年写作完成《巨流河》，其以缜密通透的笔力，从大陆巨流河写到台湾哑口海，以一个奇女子的际遇见证了纵贯百年、横跨两岸的大时代的变迁。本书有两条主线：一是借着父亲齐世英的经历，串联起一代铮铮铁汉们在侵略者炮火下头可抛、血可洒的气概与尊严；一是从自己诞生、童年写起，战火中逃离至重庆，八年间受南开中学与武汉大学教育，受业于名师，得文学启蒙，大学毕业后落脚台湾展开学术事业，成为台湾文学推手。", R.drawable.book2, 37);
        insertBook(db, "人间词话七讲", "《人间词话》是著名国学大师王国维所著的一部文学批评著作，也是晚清以来最有影响的著作之一。它的文辞优美，但因为它的解说方式是中国传统的感悟式的意象批评，所以一般读者不易读懂。本书分为两部分。第一部分为著名词学大师叶嘉莹的讲内容。作者以深入浅出和典雅细腻的文字，为读者讲述了王国维《人间词话》中著名的“境界”说、词与诗的美感特质的区别，及历代著名词家词作。第二部分为人间词话原文。", R.drawable.book3, 62);
        db.execSQL("CREATE TABLE MAGAZINE(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "NAME TEXT,"
                + "DESCRIPTION TEXT,"
                + "IMAGE_RESOURCE_ID INTEGER,"
                + "PRICE INTEGER,"
                + "FAVORITE NUMERIC);"
        );
        insertMagazine(db, "环球少年地理", "《环球少年地理》是美国《国家地理·少儿版》的中文版。经国家新闻出版总署批准，由《美国国家地理》杂志社和青岛城市传媒合作推出的《美国国家地理》少儿版（《NG KIDS》）——《环球少年地理》，在2013年1月11日正式发刊，面向国内公开发行。", R.drawable.magazine1, 16);
        insertMagazine(db, "环球科学", "《环球科学》以普及科学知识、培育民族科学精神为宗旨；以立足中国、人文视角、实用导向为编辑方针。预测全球科学未来的发展趋势，以及它对人类社会现在和未来的深刻影响，并将科研界的成果和人们的实际应用联系在一起，成为政府和企业制订科技政策和发展战略的参考，深入了解各领域科技动态的指南。", R.drawable.magazine2, 20);
        db.execSQL("CREATE TABLE NEWSPAPER(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "NAME TEXT,"
                + "DESCRIPTION TEXT,"
                + "IMAGE_RESOURCE_ID INTEGER,"
                + "PRICE INTEGER,"
                + "FAVORITE NUMERIC);"
        );
        insertNewspaper(db, "人民日报", "人民日报（People's Daily）是中国共产党中央委员会机关报。\n" +
                "报纸于1948年6月15日在河北省平山县里庄创刊。时由《晋察冀日报》和晋冀鲁豫《人民日报》合并而成，为华北中央局机关报，同时担负党中央机关报职能。毛泽东同志亲笔为人民日报题写报名。1949年8月1日，中共中央决定人民日报为中国共产党中央委员会机关报，并沿用1948年6月15日的期号。1992年，人民日报被联合国教科文组织评为世界十大报纸之一。", R.drawable.news1, 2);
        insertNewspaper(db, "扬子晚报", "《扬子晚报》为江苏省级报刊，是中国发行量最大的晚报都市报，于1986年元旦创刊。由中国共产党江苏省委员会直属事业单位新华日报社主办，中共江苏省委宣传部主管，隶属江苏省属国有企业新华报业传媒集团。“扬子晚报”四个字为时任中共中央总书记胡耀邦同志亲笔题写。报社地址：中国江苏省南京市江东中路369号新华报业传媒广场。", R.drawable.news2, 1);

        //用户表
        db.execSQL("CREATE TABLE USER(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "NAME TEXT,"
                + "PASSWORD TEXT);"
        );

        //登录状态表
        db.execSQL("CREATE TABLE LOG(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "LOGIN INTEGER);"
        );
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("LOGIN", 0);  //0未登录，1登录
        db.insert("LOG", null, drinkValues);


        //购物车表
        db.execSQL("CREATE TABLE CART(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "IMAGE_RESOURCE_ID INTEGER,"
                + "NAME TEXT,"
                + "PRICE INTEGER);"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    private static void insertBook(SQLiteDatabase db, String name, String description, int resourceld, int price) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceld);
        drinkValues.put("PRICE", price);
        long result = db.insert("BOOK", null, drinkValues);
        Log.d("sqlite", "insert" + name + ",_id" + result);
    }

    private static void insertMagazine(SQLiteDatabase db, String name, String description, int resourceld, int price) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceld);
        drinkValues.put("PRICE", price);
        long result = db.insert("MAGAZINE", null, drinkValues);
        Log.d("sqlite", "insert" + name + ",_id" + result);
    }

    private static void insertNewspaper(SQLiteDatabase db, String name, String description, int resourceld, int price) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceld);
        drinkValues.put("PRICE", price);
        long result = db.insert("NEWSPAPER", null, drinkValues);
        Log.d("sqlite", "insert" + name + ",_id" + result);
    }

}
