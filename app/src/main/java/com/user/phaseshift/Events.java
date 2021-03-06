package com.user.phaseshift;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Events extends AppCompatActivity {

    ListView lv;
    Context context;

    ArrayList prgmName;
    ListView list;
    public static Integer[] prgmImages={R.drawable.answer,R.drawable.engineer,R.drawable.learn,R.drawable.code,R.drawable.create,R.drawable.play};
    public static String [] prgmNameList={"ANSWER","ENGINEER","LEARN","CODE","CREATE","PLAY","SEMINARS","WORKSHOP"};

    public static String[] prgm={"answer","engineer","learn","code","create","play","seminars","workshop"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Events");

        CustomAdapter adapter = new CustomAdapter(this, prgmNameList, prgmImages);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Intent i;
                if(position==6)
                {
                     i=new Intent(getApplicationContext(),seminar.class);
                }
                else if(position==7)
                    {
                        i=new Intent(getApplicationContext(),workshop.class);
                    }

                else {
                    i = new Intent(getApplicationContext(), ListEvents.class);
                }
                i.putExtra("value",prgm[position]);
                i.putExtra("position",position);
                startActivity(i);

            }
        });
    }

}