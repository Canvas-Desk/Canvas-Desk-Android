package org.rohanp.layerpicker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    ArrayList<CheckBox> list = new ArrayList<CheckBox>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void addLayer(View view){
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.mainLayout);
        CheckBox c = new CheckBox(this);
        showInputDialog(list, list.size()+1, c);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //params.addRule(RelativeLayout.TRUE);
        try{
            params.addRule(RelativeLayout.BELOW, list.get(list.size()-2).getId());
        }
        catch (IndexOutOfBoundsException e) {
            try {
                params.addRule(RelativeLayout.BELOW, list.get(list.size()-2).getId());
            }
            catch (IndexOutOfBoundsException f) {
                params.addRule(RelativeLayout.BELOW, R.id.title);
            }
        }
        c.setLayoutParams(params);
        layout.addView(c);
        //R.layout.activity_main(c.getId()(android:layout_below="@id/title"));
//        android:layout_marginTop="10dp",
//                android:layout_width="wrap_content",
//                android:layout_height="400dp")
    }

    protected void showInputDialog(final List list, int checkBoxNumber, final CheckBox c) {
        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText input = (EditText)promptView.findViewById(R.id.result);

        input.setText("Layer " + checkBoxNumber);

        // setup a dialog window
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        c.setText(input.getText());
                        list.add(c);
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
