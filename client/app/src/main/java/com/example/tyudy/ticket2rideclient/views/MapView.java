package com.example.tyudy.ticket2rideclient.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by tyudy on 3/9/17.
 */

public class MapView extends View {

    int distance = 50;

    public MapView(Context context){
        super(context);

    }

    @Override
    public void onDraw(Canvas canvas){

        WindowManager mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = mWindowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);

        canvas.drawLine(64,312,144,458,paint);
    }

    public void reDraw(){
        distance += 50;
        invalidate();
    }

}
