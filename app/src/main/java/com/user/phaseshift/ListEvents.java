package com.user.phaseshift;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ListEvents extends AppCompatActivity{

    ArrayList<String> list = new ArrayList<String>();
    ListView lv;
    TextView loading;

    /** Declaring an ArrayAdapter to set items to ListView */
    ArrayAdapter<String> adapter;
    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // int[] images={R.drawable.answer1,R.drawable.eng,R.drawable.create1,R.drawable.code1,R.drawable.learn1,R.drawable.play1};
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        loading = (TextView) findViewById(R.id.mainLoading);


        Log.d("Hi", "Respomd");
        Intent i=getIntent();

       String value= i.getStringExtra("value");
        int positon=i.getIntExtra("position", 0);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(value.toUpperCase());
        RelativeLayout rel = (RelativeLayout) findViewById(R.id.r1);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);


        GIFView gifView=new GIFView(this,positon);
        p.addRule(RelativeLayout.CENTER_HORIZONTAL | RelativeLayout.CENTER_IN_PARENT, gifView.getId());
        GIFView gf=(GIFView)findViewById(R.id.view);
        gf.setGIFResource(positon);
        adapter = new ArrayAdapter<String>(this, R.layout.listview_item, list);
      //  Parse.enableLocalDatastore(this);
        Log.d("Hi", "Respomd");
      //  Parse.initialize(this, "bA2jSzNoYuZ5XOdcXzpC8LUhFoOLZvIXiRUlGS4Y", "SUgwli06qEgpFmQob5d56eFZsisbhSyMtdjDiGCD");

        ParseQuery query = new ParseQuery("ps");
        Log.d("Hi", "Respomd");
        //  query.orderByDescending("title");
        query.whereEqualTo("category", value);
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
                Log.d("Hi", "Done");
                 lv = (ListView) findViewById(R.id.listview);
                lv.setVisibility(View.GONE);
                Log.d("Hi", "Done");
                //  ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, foos);
                lv.setAdapter(adapter);
                loading.setVisibility(View.GONE);
                lv.setVisibility(View.VISIBLE);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        // ListView Clicked item index
                        int itemPosition = position;

                        // ListView Clicked item value
                        String itemValue = (String)lv.getItemAtPosition(position);

                        Intent i=new Intent(getApplicationContext(),DetailEvents.class);
                        i.putExtra("eventName",itemValue);
                        startActivity(i);

                    }

                });
                Log.d("Hi", "Done");
            }

        });

    }
}