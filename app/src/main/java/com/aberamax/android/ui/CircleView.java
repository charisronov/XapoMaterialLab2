package com.aberamax.android.ui;

/**
 * Created by jiri_ on 18.01.2017.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

import com.aberamax.android.xapomateriallab.R;


/**
 * Created by Shahab on 3/20/14.
 *
 *  https://tausiq.wordpress.com/2014/03/20/android-custom-circle-view-with-stroke/
 */
public class CircleView
        extends AbstractBaseView {

    private static final String TAG = "CircleView";

    private int circleRadius = 20;
    private int strokeColor = 0xFFFF8C00;
    private int strokeWidth = 15;
    private int fillColor = 0XFFFFAB00;
    private int circleGap = 20;

    public CircleView(Context context) {
        super(context);
        Log.d(TAG,"CircleView(Context context)");
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        Log.d(TAG,"CircleView(Context context, AttributeSet attrs)");
        setAttrs(attrs);

        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG,"CircleView(Context context, AttributeSet attrs, int defStyleAttr))");

        setAttrs(attrs);

        init();
    }

    public CircleView(Context context, int strokeColor, int strokeWidth, int fillColor, int circleRadius, int circleGap) {
        super(context);
        Log.d(TAG,"CircleView(Context context, int strokeColor, int strokeWidth, int fillColor, int circleRadius, int circleGap)");
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        this.fillColor = fillColor;
        this.circleRadius = circleRadius;
        this.circleGap = circleGap;

        init();
    }

    private void init() {
        this.setMinimumHeight(circleRadius * 2 + strokeWidth);
        this.setMinimumWidth(circleRadius * 2 + strokeWidth);
        this.setSaveEnabled(true);
    }

    private void setAttrs(AttributeSet attrs){

        TypedArray aTypedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CircleView);

        strokeColor = aTypedArray.getColor(R.styleable.CircleView_strokeColor, strokeColor);
        strokeWidth = aTypedArray.getDimensionPixelSize(R.styleable.CircleView_strokeWidth, strokeWidth);
        fillColor = aTypedArray.getColor(R.styleable.CircleView_fillColor, fillColor);
        circleRadius = aTypedArray.getDimensionPixelSize(R.styleable.CircleView_circleRadius, circleRadius);
        circleGap = aTypedArray.getDimensionPixelSize(R.styleable.CircleView_circleGap, circleGap);

        aTypedArray.recycle();

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int w = this.getWidth();
        int h = this.getHeight();

        int ox = w / 2;
        int oy = h / 2;

        canvas.drawCircle(ox, oy, circleRadius, getStroke());
        canvas.drawCircle(ox, oy, circleRadius - circleGap, getFill());
    }

    private Paint getStroke() {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setStrokeWidth(strokeWidth);
        p.setColor(strokeColor);
        p.setStyle(Paint.Style.STROKE);
        return p;
    }

    private Paint getFill() {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(fillColor);
        p.setStyle(Paint.Style.FILL);
        return p;
    }

    @Override
    protected int hGetMaximumHeight() {
        return circleRadius * 2 + strokeWidth;
    }

    @Override
    protected int hGetMaximumWidth() {
        return circleRadius * 2 + strokeWidth;
    }

    public int getCircleRadius() {
        return circleRadius;
    }

    public void setCircleRadius(int circleRadius) {
        this.circleRadius = circleRadius;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public int getFillColor() {
        return fillColor;
    }

    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;
    }

    public int getCircleGap() {
        return circleGap;
    }

    public void setCircleGap(int circleGap) {
        this.circleGap = circleGap;
    }
}