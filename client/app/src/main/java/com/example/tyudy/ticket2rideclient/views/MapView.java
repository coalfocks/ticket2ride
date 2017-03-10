package com.example.tyudy.ticket2rideclient.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.example.tyudy.ticket2rideclient.common.cities.City;
import com.example.tyudy.ticket2rideclient.drawing.DrawingHelper;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.sql.ClientInfoStatus;

/**
 * Created by tyudy on 3/9/17.
 */

public class MapView extends View {

    float mScreenWidth;
    float mScreenHeight;
    City mSource;
    City mDestination;
    Canvas mCanvas;

    public MapView(Context context){
        super(context);

        WindowManager mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = mWindowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        mScreenWidth = size.x;
        mScreenHeight = size.y;

        mCanvas = null;
        mSource = new City();
        mDestination = new City();

    }

    @Override
    public void onDraw(Canvas canvas){

        super.onDraw(canvas);

//        if(mCanvas == null){
//            mCanvas = canvas;
//        }

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);

        // Make this draw from atlanta to Miami

        float sourceX = mSource.getxPosScale() * mScreenWidth;
        float sourceY = mSource.getyPosScale() * mScreenHeight;
        float destinationX = mDestination.getxPosScale() * mScreenWidth;
        float destinationY = mDestination.getyPosScale() * mScreenHeight;
        canvas.drawLine(sourceX,sourceY,destinationX,destinationY,paint);
    }

    /**
     * redraws the view adding a line to the two cities
     * @param source
     * @param destination
     */
    public void reDrawWithLineBetween(City source, City destination){
        mSource = source;
        mDestination = destination;
        invalidate();
    }

}
