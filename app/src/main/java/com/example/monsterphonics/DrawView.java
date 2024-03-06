package com.example.monsterphonics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DrawView extends View {
    private Paint paint = new Paint();
    private float startX = -1;
    private float startY = -1;
    private float endX = -1;
    private float endY = -1;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint.setColor(android.graphics.Color.BLACK);
        paint.setStrokeWidth(8);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (startX != -1 && startY != -1 && endX != -1 && endY != -1) {
            canvas.drawLine(startX, startY, endX, endY, paint);
        }
    }

    public void drawLine(float startX, float startY, float endX, float endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        invalidate();
    }

    public void clearLines() {
        startX = startY = endX = endY = -1;
        invalidate();
    }
}
