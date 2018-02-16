package android.words;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class All_words extends AppCompatActivity {
    TextView all;
    DbHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    String text;
    ArrayList arrayList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_words);

        all = (TextView) findViewById(R.id.all);
        dbHelper = new DbHelper(All_words.this);
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor1 = sqLiteDatabase.query("WORDS", new String[]{"ENWORD"}, null,null, null, null, null );
        while (cursor1.moveToNext()){
            text = cursor1.getString(cursor1.getColumnIndex("ENWORD"));
            arrayList.add(text);
        }
        all.setText(arrayList.toString());
        cursor1.close();
        sqLiteDatabase.close();


    }
}
