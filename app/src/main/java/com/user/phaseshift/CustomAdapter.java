package com.user.phaseshift;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class CustomAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;

    public CustomAdapter(Activity context, String[] itemname, Integer[] imgid) {
        super(context, R.layout.list_row, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_row, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.icon);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
       // Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        txtTitle.setText(itemname[position]);
        txtTitle.setTextColor(Color.WHITE);
        txtTitle.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);


       // txtTitle.setText(itemname[position]);
        //imageView.setImageResource(imgid[position]);
//        animation.setDuration(500);
//        rowView.startAnimation(animation);
//        animation = null;

        return rowView;

    };
}