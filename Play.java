package android.words;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Play extends AppCompatActivity {

    DbHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    String text;
    String word;
    String translate_world;
    String your_word_string;
    String [] strings;
    ArrayList arrayList_inBD;
    TextView new_word;
    EditText your_word;
    Button ok;
    Button set_colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        dbHelper = new DbHelper(Play.this);
        sqLiteDatabase = dbHelper.getReadableDatabase();

        arrayList_inBD = new ArrayList();

        new_word = (TextView) findViewById(R.id.new_array);
        your_word = (EditText) findViewById(R.id.edit_word);
        your_word_string = your_word.getText().toString();
        ok = (Button) findViewById(R.id.ok);
        set_colors = (Button) findViewById(R.id.set_color);


        Cursor cursor1 = sqLiteDatabase.query("WORDS", new String[]{"ENWORD"}, null,null, null, null, null );
        while (cursor1.moveToNext()){
            text = cursor1.getString(cursor1.getColumnIndex("ENWORD"));
            arrayList_inBD.add(text);
        }
        cursor1.close();
        sqLiteDatabase.close();


        strings = new String[arrayList_inBD.size()];

        for(int i =0; i<arrayList_inBD.size(); i++){
            strings[i] = arrayList_inBD.get(i).toString();
        }

        word = get_word(strings);
        new_word.setText(word);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.query("WORDS", new String[]{"ENWORD", "RUWORD"}, "ENWORD = ?",new String[]{word}, null, null, null, null);
            while (cursor.moveToNext()){
                translate_world = cursor.getString(cursor.getColumnIndex("RUWORD"));
            }

            if(translate_world.equalsIgnoreCase(your_word_string)){
                set_colors.setBackgroundColor(getResources().getColor(R.color.true_color));
            }
            else {
                set_colors.setBackgroundColor(getResources().getColor(R.color.false_color));
                Toast toast = Toast.makeText(Play.this, translate_world, Toast.LENGTH_SHORT);
                toast.show();
            }

                word = get_word(strings);
                new_word.setText(word);


            }
        });




    }



    public String get_word(String words[]){
        Random random = new Random();
        int k = random.nextInt(words.length);
        String word = strings[k];

        return word;

    }
}
