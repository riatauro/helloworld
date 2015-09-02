package com.user.phaseshift;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class DetailEvents extends AppCompatActivity {
    //get the eventName string from the previous list view, pass along with an intent
    public String eventName = "Gridlock";
    protected  ParseObject eventParseObject = null;
    public TextView eventDetailEventName;
    public TextView eventDetailEventDescription;
    public TextView eventDetailCoordinatorA;
    public TextView eventDetailRegistrationFee;
    public TextView eventDetailNoOfTeamMembers;
    public TextView eventDetailPrizeMoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i=getIntent();
        String eventName=i.getStringExtra("eventName");
        setContentView(R.layout.detail_list);
        eventDetailEventName = (TextView) findViewById(R.id.eventDetailEventName);
        eventDetailEventDescription = (TextView) findViewById(R.id.eventDetailEventDescription);
        eventDetailCoordinatorA = (TextView) findViewById(R.id.eventDetailCoordinatorA);
        eventDetailRegistrationFee = (TextView) findViewById(R.id.eventDetailRegistrationFee);
        eventDetailNoOfTeamMembers = (TextView) findViewById(R.id.eventDetailNoOfTeamMembers);
        eventDetailPrizeMoney = (TextView) findViewById(R.id.eventDetailPrizeMoney);


        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
            // Enable Local Datastore.
           // Parse.enableLocalDatastore(this);

            //Parse.initialize(this, "bA2jSzNoYuZ5XOdcXzpC8LUhFoOLZvIXiRUlGS4Y", "SUgwli06qEgpFmQob5d56eFZsisbhSyMtdjDiGCD");
            ParseQuery<ParseObject> query = ParseQuery.getQuery("ps");
            query.whereEqualTo("title", eventName);
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> scoreList, ParseException e) {
                    if (e == null && scoreList.size() == 1) {
                        Log.d("score101", "Retrieved " + scoreList.size() + " scores");
                        //ParseObject.pinAllInBackground("ps", scoreList);
                        eventParseObject = scoreList.get(0);
                        eventDetailEventName.setText(eventParseObject.getString("title"));
                        eventDetailEventDescription.setText(eventParseObject.getString("description"));
                        eventDetailCoordinatorA.setText("Co-ordinator: "+eventParseObject.getString("cood_1")+"\n"+"Contact: "+
                                eventParseObject.getString("phone"));
                        eventDetailRegistrationFee.setText("Registration Fee: Rs "+eventParseObject.getString("fee"));
                        eventDetailNoOfTeamMembers.setText("Number Of Members: "+eventParseObject.getString("num_of_members"));
                        eventDetailPrizeMoney.setText("Prize\nFirst Place: "+eventParseObject.getString("prize_1")+
                                "\nSecond Place: "+eventParseObject.getString("prize_2")+"\nThird Place: "+eventParseObject.getString("prize_3"));
                    } else {
                        Log.d("score101", "Error: " + e.getMessage());
                    }
                }
            });



        }
        else {
            // display error
        }
    }
}
