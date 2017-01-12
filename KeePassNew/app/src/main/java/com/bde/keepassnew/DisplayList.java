package com.bde.keepassnew;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DisplayList extends AppCompatActivity{

    private MyDBHandler dbHandler= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_display_list);

        MainActivity main_obj = new MainActivity();
        dbHandler = (MyDBHandler)main_obj.getDbHandler();
        //Intent i = getIntent();
        //dbHandler = (MyDBHandler)i.getSerializableExtra(Activity_interface.FRIST_ARG);

        String[] foods = {"Bacon", "Ham", "Tuna", "Candy", "Meatball", "Potato"};

        ListAdapter buckysAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, foods);
        ListView buckysListView = (ListView) findViewById(R.id.buckysListView);
        buckysListView.setAdapter(buckysAdapter);

        buckysListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String food = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(DisplayList.this, food, Toast.LENGTH_LONG).show();
                }
                }
        );
    }

}
