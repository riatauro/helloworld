package com.user.phaseshift;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class DetailEvents extends ActionBarActivity {
    //get the eventName string from the previous list view, pass along with an intent
    public String eventName ;
    protected  ParseObject eventParseObject = null;
    public TextView eventDetailEventName;
    public TextView eventDetailEventDescription;
    public TextView eventDetailCoordinatorA;
    public TextView eventDetailRegistrationFee;
    public TextView eventDetailNoOfTeamMembers;
    public TextView eventDetailPrizeMoney;
    public TextView eventDate;
    public TextView eventDetailTime;
    public TextView eventDetailVenue;
    public TextView loading;
    public Button register;
    public ScrollView scrollview;
    PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_list);
        Intent i=getIntent();
        eventName=i.getStringExtra("eventName");


        eventDetailEventName = (TextView) findViewById(R.id.eventDetailEventName);
        eventDetailEventDescription = (TextView) findViewById(R.id.eventDetailEventDescription);
        eventDetailCoordinatorA = (TextView) findViewById(R.id.eventDetailCoordinatorA);
        eventDetailRegistrationFee = (TextView) findViewById(R.id.eventDetailRegistrationFee);
        eventDetailNoOfTeamMembers = (TextView) findViewById(R.id.eventDetailNoOfTeamMembers);
        eventDetailPrizeMoney = (TextView) findViewById(R.id.eventDetailPrizeMoney);
        eventDetailVenue = (TextView) findViewById(R.id.eventDetailVenue);
        eventDetailTime = (TextView) findViewById(R.id.eventDetailTime);
        eventDate = (TextView) findViewById(R.id.eventDate);

        loading = (TextView) findViewById(R.id.loading);
        register = (Button) findViewById(R.id.eventDetailRegistrationButton);
        scrollview = (ScrollView) findViewById(R.id.scrollView);
        scrollview.setVisibility(View.GONE);
        register.setVisibility(View.GONE);


        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
            // Enable Local Datastore.
            //Parse.enableLocalDatastore(this);

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
                        eventDetailEventName.setTextColor(Color.parseColor("#ffa41c36"));
                        eventDetailEventDescription.setText(eventParseObject.getString("description"));
                        eventDetailEventDescription.setTextColor(Color.WHITE);
                        eventDetailCoordinatorA.setText(Html.fromHtml("<b>Co-ordinator:</b> " + eventParseObject.getString("cood_1") + "<br></br><b>Contact:</b> " +
                                eventParseObject.getString("phone")));
                        eventDetailCoordinatorA.setTextColor(Color.WHITE);
                        eventDetailRegistrationFee.setText(Html.fromHtml("<b>Registration Fee:</b> Rs " + eventParseObject.getString("fee")));
                        eventDetailRegistrationFee.setTextColor(Color.WHITE);
                        eventDetailNoOfTeamMembers.setText(Html.fromHtml("<b>Number Of Members:</b> " + eventParseObject.getString("num_of_members")));
                        eventDetailNoOfTeamMembers.setTextColor(Color.WHITE);
                        eventDetailPrizeMoney.setText("First Place: " + eventParseObject.getString("prize_1") +
                                "\nSecond Place: " + eventParseObject.getString("prize_2") + "\nThird Place: " + eventParseObject.getString("prize_3"));
                        eventDetailPrizeMoney.setTextColor(Color.WHITE);
                        eventDate.setText(eventParseObject.getString("date"));
                        eventDetailTime.setText(eventParseObject.getString("start_time")+"-"+eventParseObject.getString("end_time"));
                        eventDetailVenue.setText(eventParseObject.getString("venue"));

                                loading.setVisibility(View.GONE);
                        scrollview.setVisibility(View.VISIBLE);
                        register.setVisibility(View.VISIBLE);

                    } else {
                        Log.d("score101", "Error: " + e.getMessage());
                    }
                }
            });
        }
        else {
            // display error
            Log.d("score101", "Error: ");
        }
      register.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent i1=new Intent(getApplicationContext(),RegisterEvents.class);
              i1.putExtra("name",eventName);
              startActivity(i1);

          }
      });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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