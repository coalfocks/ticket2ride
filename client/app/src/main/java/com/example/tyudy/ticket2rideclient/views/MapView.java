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
import com.example.tyudy.ticket2rideclient.common.cities.Path;

import com.example.tyudy.ticket2rideclient.common.ColorENUM;

import java.util.ArrayList;

/**
 * Created by tyudy on 3/9/17.
 */

public class MapView extends View {

    private final int DRAW_WIDTH = 12;

    float mScreenWidth;
    float mScreenHeight;
    City mSource;
    City mDestination;
    Canvas mCanvas;
    ArrayList<Path> mPathsToDraw;

    public MapView(Context context){
        super(context);

        WindowManager mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = mWindowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        mScreenWidth = size.x;
        mScreenHeight = size.y;
        mPathsToDraw = new ArrayList<>();

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
        paint.setStrokeWidth(DRAW_WIDTH);

        // Make this draw from atlanta to Miami


        for(Path p: mPathsToDraw){
            if(p.hasOwner()){
                // Draw the route in its correct location with its correct mColorENUM
                ColorENUM drawColorENUM = p.getOwner().getColor();
                int paintColor = getColorFromEnum(drawColorENUM);
                paint.setColor(paintColor);

                City source = p.getCities().get(0);
                City destination = p.getCities().get(1);

                float sourceX = source.getxPosScale() * mScreenWidth;
                float sourceY = source.getyPosScale() * mScreenHeight;
                float destinationX = destination.getxPosScale() * mScreenWidth;
                float destinationY = destination.getyPosScale() * mScreenHeight;

                canvas.drawLine(sourceX,sourceY,destinationX,destinationY,paint);

            }
        }
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

    public void redrawModelPaths(ArrayList<Path> pathsToDraw){
        mPathsToDraw = pathsToDraw;
        invalidate();
    }

    private int getColorFromEnum(ColorENUM c){
        switch(c){
            case WHITE:
                return Color.WHITE;
            case BLACK:
                return Color.BLACK;
            case BLUE:
                return Color.BLUE;
            case RED:
                return Color.RED;
            case ORANGE:
                return android.graphics.Color.rgb(239, 163, 33); // GameBoardFragment says this is Orange so I believe it
            case YELLOW:
                return Color.YELLOW;
            case GREEN:
                return Color.GREEN;
            case PURPLE:
                return Color.MAGENTA;
            case COLORLESS:
                return Color.GRAY;
        }
        return 0;
    }

}
