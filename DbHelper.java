package android.words;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by Admin on 2/11/2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String NAME = "TABLE";
    public static final int VERSION = 1;

    public DbHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE WORDS(ENWORD, RUWORD)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }public void insert_word(SQLiteDatabase db, String en_word, String ru_word){
        ContentValues word = new ContentValues();
        word.put("ENWORD", en_word);
        word.put("RUWORD",ru_word);
        db.insert("WORDS", null, word);
        db.close();
    }

   public void delete(){

   }
}
