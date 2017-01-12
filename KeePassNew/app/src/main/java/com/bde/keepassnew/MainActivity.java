package com.bde.keepassnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
// Declare references

    EditText entryName;
    EditText username;
    EditText password;
    TextView recordsTextView;

    static MyDBHandler dbHandler;
    private final boolean isToUseTextViewInMainActivity = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entryName = (EditText) findViewById(R.id.entryName);
        username = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.passWord);
        recordsTextView = (TextView) findViewById(R.id.textView);
        /* Can pass nulls because of the constants in the helper.
         * the 1 means version 1 so don't run update.
         */
        setDbHandler(new MyDBHandler(this, null, null, 1));
        //dbHandler = new MyDBHandler(this, null, null, 1);
        printDatabase();
    }

    public static MyDBHandler getDbHandler() {
        return dbHandler;
    }

    public static void setDbHandler(MyDBHandler dbHandler) {
        MainActivity.dbHandler = dbHandler;
    }

    //Print the database
    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        if (isToUseTextViewInMainActivity == true) {
            recordsTextView.setText(dbString);
        }
        entryName.setText("");
        username.setText("");
        password.setText("");
    }

    //add your elements onclick methods.
    //Add a product to the database
    public void addButtonClicked(View view){
        long status = 0;
        // dbHandler.add needs an object parameter.
        Entries entry = new Entries(entryName.getText().toString(),username.getText().toString(),password.getText().toString());
        status = dbHandler.addEntry(entry);

        if ( -1 == status ) {
            Toast.makeText(this, "Database entry failed, try again later!!!", Toast.LENGTH_LONG).show();
        }
        if (entryName.getText().toString().equals(username.getText().toString())) {
            Log.i("DCM","str eql");
        }else {
            Log.i("DCM","str not eql");
        }
        if (entryName.getText().toString().equals("")) {
            Log.i("DCM","str emty");
        }else {
            Log.i("DCM","str not empty");
        }
        if (entryName.getText().toString().equals(null)) {
            Log.i("DCM","str null");
        }else {
            Log.i("DCM","str not null");
        }
        printDatabase();
    }

    //Delete items
    public void deleteButtonClicked(View view){
        // dbHandler delete needs string to find in the db
/*        String inputText = userInput.getText().toString();
        dbHandler.deleteProduct(inputText);
        printDatabase();
*/
    }

    public void displayButtonClicked(View view){
        // dbHandler.add needs an object parameter.
        Intent myIntent = new Intent(this, DisplayList.class);
        //myIntent.putExtra(Activity_interface.FRIST_ARG, dbHandler);
        //myIntent.putExtra(Activity_interface.FRIST_ARG, (Serializable) dbHandler);
        startActivity(myIntent);
    }

}