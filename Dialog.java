package android.words;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Admin on 2/15/2018.
 */

public class Dialog extends AppCompatDialogFragment {
 private EditText editTextDelete;
 private ExampleDialogListener listener;


    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());



        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        editTextDelete = (EditText) view.findViewById(R.id.edit_delete);
        builder.setView(view).setTitle("Put your world that delete").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String word = editTextDelete.getText().toString();
                listener.Applytext(word);


            }
        });



        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

       try {
           listener = (ExampleDialogListener) context;
       }catch (ClassCastException e){
          throw new ClassCastException(context.toString() + " p58");
       }
    }

    public interface ExampleDialogListener{
        void Applytext(String word);
    }

}
