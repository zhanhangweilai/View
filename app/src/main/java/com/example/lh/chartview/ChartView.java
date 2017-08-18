package com.example.lh.chartview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lihang on 17-7-26.
 */

public class ChartView extends View {

    private Paint mPointLinePaint;
    private Paint mScaleLinePaint;
    private Paint mScaleValuePaint;
    private Paint mBackgroundPaint;
    private String[] xLabel;
    private String[] yLabel;
    private String[] data;
    private String title ;
    int width;
    int height;
    float scaleX;
    float scaleY;
    float initStartPointX;
    float initStartPointY;
    float initStopPointX;
    float initStopPointY;


    public ChartView(Context context) {
        super(context);
    }

    public ChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public ChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public ChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    private void initPaint() {

        xLabel = new String[] {"07-01","07-02","07-03","07-03","07-04","07-05","07-06"};
        data = new String[] {"4.01","4.02","4.01","4.03","4.01","4.03","4.01"};
        title = getResources().getString(R.string.title);
        mPointLinePaint = new Paint();
        mScaleLinePaint = new Paint();
        mScaleValuePaint = new Paint();
        mBackgroundPaint = new Paint();

        mPointLinePaint.setAntiAlias(true);
        mScaleLinePaint.setAntiAlias(true);
        mScaleValuePaint.setAntiAlias(true);
        mBackgroundPaint.setAntiAlias(true);
        mPointLinePaint.setColor(0xFFDEDCD8);
        mBackgroundPaint.setColor(0x11DEDE68);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTitle(canvas);
        drawCroodinate(canvas);
        drawBackground(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initParams();
    }

    public void drawBackground (Canvas canvas) {
        //按平行Y轴绘制背景
        for (int i = 0; i < 7; i++) {
            if (i % 2 == 0) {
                float left = initStartPointX + scaleX * i;
                float top = initStartPointY;
                float right = left + scaleX;
                float bottom = initStopPointY;
                canvas.drawRect(left, top, right, bottom, mBackgroundPaint);
            }

        }

    }

    public void drawTitle(Canvas canvas) {
        canvas.drawText(title,100,100,mPointLinePaint);
    }

    private void drawCroodinate (Canvas canvas) {
        float startPointX = initStartPointX;
        float startPointY = initStartPointY;

        //绘制X轴
        for (int i = 0; i < 7; i++) {
            startPointY = scaleY * (i + 1);
            canvas.drawLine(initStartPointX,startPointY,initStopPointX,startPointY,mScaleLinePaint);
        }
        //绘制Y轴
        for (int j = 0; j < 7; j++) {
            startPointX = initStartPointX + scaleX * j;
            canvas.drawLine(startPointX,initStartPointY,startPointX,initStopPointY,mScaleLinePaint);
        }

    }

    private void initParams () {
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        scaleX = width / 7.5f;
        scaleY = height / 7.5f;
        initStartPointX = scaleX / 2;
        initStartPointY = scaleY / 2;
        initStopPointX = scaleX * 7f;
        initStopPointY = scaleY * 7f;

    }

}
