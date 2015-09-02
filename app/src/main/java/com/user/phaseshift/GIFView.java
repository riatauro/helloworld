package com.user.phaseshift;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class GIFView extends View {
    public Movie mMovie;
    int[] images={R.drawable.answer1,R.drawable.engineer1,R.drawable.learn1,R.drawable.create1,R.drawable.code1,R.drawable.play1};
    public long movieStart;
    private int gifId;
    public GIFView(Context context,int id) {
        super(context);
        initializeView();

    }

    public GIFView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView();
    }

    public GIFView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView();
    }

    private void initializeView() {
//R.drawable.loader - our animated GIF

        Log.d("Id","Id"+gifId);
        mMovie = Movie.decodeStream(getContext().getResources().openRawResource(images[gifId]));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);
        super.onDraw(canvas);
        long now = android.os.SystemClock.uptimeMillis();
        if (movieStart == 0) {
            movieStart = now;
        }
        if (mMovie != null) {
            int relTime = (int) ((now - movieStart) % mMovie.duration());
            mMovie.setTime(relTime);
            mMovie.draw(canvas, getWidth() - mMovie.width(), getHeight() - mMovie.height());
            this.invalidate();
        }
    }


    public void setGIFResource(int resId) {
        this.gifId = resId;
        initializeView();
    }

    public int getGIFResource() {
        return this.gifId;
    }
}