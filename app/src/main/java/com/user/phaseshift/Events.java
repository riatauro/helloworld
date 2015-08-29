package com.user.phaseshift;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Events extends Activity {

    ListView lv;
    Context context;

    ArrayList prgmName;
    ListView list;
    public static Integer[] prgmImages={R.drawable.cse,R.drawable.ece,R.drawable.eee,R.drawable.ise,R.drawable.mech,R.drawable.tc};
    public static String [] prgmNameList={"COMPUTER ENGINEERING","ELECTRONIC AND COMMUNICATION ENGINEERING","ELECTRICAL AND ELECTRONIC ENGINEERING","INFORMATION SCIENCE AND ENGINEERING","MECHANICAL ENGINEERING","TELECOMMUNICATION ENGINEERING"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        CustomAdapter adapter = new CustomAdapter(this, prgmNameList, prgmImages);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setDivider(null);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = prgmNameList[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });
    }

}