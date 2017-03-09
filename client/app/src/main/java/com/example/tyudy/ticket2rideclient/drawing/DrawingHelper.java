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

    public static void setCanvas(Canvas canvas) {
        DrawingHelper.canvas = canvas;
    }




    /**
     * Draws a point to the game view using the provided parameters.  Will not draw if the current canvas is null.
     * @param location The location of the point in view coordinates
     * @param width The width of the point
     * @param color The color of the point
     * @param alpha The point alpha value (0 - 255, 0 = completely transparent, 255 = completely opaque)
     */
    public static void drawPoint(PointF location, float width, int color, int alpha) {

        if(canvas == null)
            return;

        paint.reset();
        paint.setStrokeWidth(width);
        paint.setColor(color);
        paint.setAlpha(alpha);
        canvas.drawPoint(location.x, location.y, paint);
    }

    /**
     * Draw a line between the given points
     * @param start - start point
     * @param stop - stop point
     * @param color - color to draw the line
     */
    public static void drawLine(PointF start, PointF stop, int color){
        paint.reset();
        paint.setStrokeWidth(PATH_WIDTH);
        paint.setColor(color);
        canvas.drawLine(start.x, start.y, stop.x, stop.y, paint);
    }


    /**
     * Get the width of the game view
     * @return The width of the game view, 0 if the current canvas is null
     */
    public static int getGameViewWidth() {

        if(canvas == null)
            return 0;

        return viewWidth;
    }


    /**
     * Get the height of the game view
     * @return The height of the game view, 0 if the current canvas is null
     */
    public static int getGameViewHeight() {

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