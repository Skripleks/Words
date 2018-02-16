package android.words;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Dialog.ExampleDialogListener{




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        findViewById(R.id.add_word).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Add.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.all_words).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, All_words.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Play.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    public void openDialog(){
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), "Create Dialog");

    }

    @Override
    public void Applytext(String word) {
        if(!word.equalsIgnoreCase("")) {
            DbHelper dbHelper = new DbHelper(MainActivity.this);
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            sqLiteDatabase.delete("WORDS", "ENWORD = ?", new String[]{word});
            sqLiteDatabase.close();
        }else {
            Toast.makeText(this, "edit is empty", Toast.LENGTH_SHORT).show();
        }

    }
}
