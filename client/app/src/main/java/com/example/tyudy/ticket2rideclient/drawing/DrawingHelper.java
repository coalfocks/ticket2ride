package com.example.tyudy.ticket2rideclient.drawing;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;



/**
 * A class that enables drawing to the game view without having to know anything about Android canvases
 * or matrices.
 */
public class DrawingHelper {

    //Constant for the width of paths
    private static final float PATH_WIDTH = 250;

    //The current GameView's current canvas
    private static Canvas canvas;

    //Helper paints
    private static Paint paint = new Paint();
    private static Paint fillPaint = new Paint();
    private static int viewWidth;
    private static int viewHeight;


    /**
     * Get the width of the game view
     * @return The width of the game view, 0 if the current canvas is null
     */
    public static int getViewWidth() {

        if(canvas == null)
            return 0;

        return viewWidth;
    }


    /**
     * Get the height of the game view
     * @return The height of the game view, 0 if the current canvas is null
     */
    public static int getViewHeight() {

        if(canvas == null)
            return 0;

        return viewHeight;
    }

    public static void setViewWidth(int w) {
        viewWidth = w;
    }

    public static void setViewHeight(int h) {
        viewHeight = h;
    }

    public static Canvas getCanvas() {
        return canvas;
    }

}