package com.user.phaseshift;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ListEvents extends Activity {

    ArrayList<String> list = new ArrayList<String>();

    /** Declaring an ArrayAdapter to set items to ListView */
    ArrayAdapter<String> adapter;
    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_main);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "bA2jSzNoYuZ5XOdcXzpC8LUhFoOLZvIXiRUlGS4Y", "SUgwli06qEgpFmQob5d56eFZsisbhSyMtdjDiGCD");
        ParseQuery query = new ParseQuery("ps");
        //  query.orderByDescending("title");
        query.whereEqualTo("category", "answer");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> categories, ParseException e) {
                if (e == null) {
                    Log.d("Hi", "Respomd");
                    for (int i = 0; i < categories.size(); i++) {
                        String c = categories.get(i).getString("title");
                        Log.d("Category", "Found" + c);

                        list.add(c);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Error",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        ListView lv = (ListView) findViewById(R.id.listview);
        //  ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, foos);
        lv.setAdapter(adapter);
    }
}