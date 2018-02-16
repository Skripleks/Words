package android.words;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class Add extends AppCompatActivity {

    EditText en;
    EditText ru;
    DbHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                en = (EditText) findViewById(R.id.en);
                ru = (EditText) findViewById(R.id.ru);
                if(en.getText().toString().equalsIgnoreCase("") | ru.getText().toString().equalsIgnoreCase("")){
                Toast toast = Toast.makeText(Add.this, "En_word or Ru_word is empty", Toast.LENGTH_SHORT);
                toast.show();

                }
                else {

                    dbHelper = new DbHelper(Add.this);
                    sqLiteDatabase = dbHelper.getWritableDatabase();

                    dbHelper.insert_word(sqLiteDatabase, en.getText().toString(), ru.getText().toString());

                    sqLiteDatabase.close();

                    Toast toast = Toast.makeText(Add.this, "Word succeful add", Toast.LENGTH_SHORT);
                    toast.show();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(Add.this, MainActivity.class);
                    startActivity(intent);
                }


            }
        });
    }
}
