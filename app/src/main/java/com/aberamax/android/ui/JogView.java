package com.aberamax.android.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.aberamax.android.xapomateriallab.R;


/**
 * Created by jiri_ on 19.01.2017.
 */

public class JogView extends AbstractBaseView {

    private static final String TAG = "JogView";

    private int jogRadius = 160;
    private int jogStroke = 10;
    private int jogDotRadius = 160;
    private int jogDotOffset = 32;
    private float jogAngle = 270f;
    private int jogDotFillColor = 0xFFFF8C00;
    private int jogFillColor = 0xFFFF8C00;

    private int jogAreaMargin = 2;

    private PointF centerPos;
    private PointF dotPos;
    private PointF minAreaTouch;
    private PointF maxAreaTouch;

    private float millisCounter = 270;
    private float lastAngle = 270;
    private JogViewListener listener;


    // help variables for optimalized
    //
    private Bitmap bufferBitmap;
    private Canvas bufferCanvas;

    public JogView(Context context) {
        super(context);
        Log.d(TAG,"JogView(Context context)");
        init();
    }

    public JogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG,"JogView(Context context, AttributeSet attrs)");
        setAttrs(attrs);
        init();
    }

    public JogView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        Log.d(TAG, "constructor JogView(Context context, AttributeSet attrs, int defStyle)");

        setAttrs(attrs);
        init();
    }

    // CircleView(Context context, int strokeColor, int strokeWidth, int fillColor, int circleRadius, int circleGap)

    public JogView(Context context, int jogRadius, int jogStroke, int jogAngle, int jogDotRadius, int jogDotOffset){
        super(context);

        Log.d(TAG, "JogView(Context context, int jogRadius, int jogStroke, int jogAngle, int jogDotRadius, int jogDotOffset)");
        this.jogRadius = jogRadius;
        this.jogStroke = jogStroke;
        this.jogAngle = (float)jogAngle;
        this.jogDotRadius = jogDotRadius;
        this.jogDotOffset = jogDotOffset;
    }



    private void setAttrs(AttributeSet attrs){

        TypedArray aTypedArray = getContext().obtainStyledAttributes(attrs, R.styleable.JogView);

        // !!!! important DATA types must be correct according to declaration
        // studio doesn't notice an error but ignores all other settings
        jogFillColor = aTypedArray.getColor(R.styleable.JogView_jogDotFillColor, jogDotFillColor);
        jogDotRadius = aTypedArray.getDimensionPixelSize(R.styleable.JogView_jogDotRadius, jogDotRadius);
        jogDotFillColor = aTypedArray.getColor(R.styleable.JogView_jogFillColor, jogFillColor);
        jogRadius = aTypedArray.getDimensionPixelSize(R.styleable.JogView_jogRadius, jogRadius);
        jogStroke = aTypedArray.getDimensionPixelSize(R.styleable.JogView_jogStroke, jogStroke);
        jogDotOffset = aTypedArray.getDimensionPixelSize(R.styleable.JogView_jogDotOffset, jogDotOffset);
        jogAngle = (float)aTypedArray.getInteger(R.styleable.JogView_jogAngle, Math.round(jogAngle));

        aTypedArray.recycle();
    }


    private void init() {

        this.centerPos = new PointF();
        this.dotPos = new PointF();
        this.minAreaTouch = new PointF();
        this.maxAreaTouch = new PointF();

        this.setMinimumHeight(Math.max((jogRadius + jogStroke)* 2, (jogDotOffset + jogDotRadius)*2 ) );
        this.setMinimumWidth(Math.max((jogRadius + jogStroke)* 2, (jogDotOffset + jogDotRadius)*2 ) );
        this.setSaveEnabled(true);
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int w = this.getWidth();
        int h = this.getHeight();

        int ox = w / 2;
        int oy = h / 2;

        // DOUBLE BUFFERING  (slightly better)

        Bitmap bufferBitmap;
        bufferBitmap = Bitmap.createBitmap(canvas.getWidth(),
        canvas.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas bufferCanvas = new Canvas(bufferBitmap);

        bufferCanvas.drawCircle(ox, oy, jogRadius, getStroke());

        centerPos.set(ox,oy);
        dotPos = getPosition(centerPos, jogDotOffset, jogAngle);

        bufferCanvas.drawCircle(dotPos.x, dotPos.y, jogDotRadius, getFill());
        canvas.drawBitmap(bufferBitmap, 0, 0, null);

    }


    private void attachTouchListener () {

        this.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent event) {

                if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

                    // txtAngle.setText(Integer.toString(Math.round(jogView.touchAngle(event.getX(), event.getY()))));
                    // onTouchMove(view, event);

                } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                    Log.d("TouchTest", "Touch up");
                    // jogView.resetLastAnglePosition();

                } else if (event.getAction() == android.view.MotionEvent.ACTION_MOVE) {

                    onTouchMove(view, event);



                  // txtAngle.setText(String.format("%.1f°", jogView.getJogAngle()));
                  //  txtMillis.setText(Long.toString(jogView.getMillisCounter()));
                }
                return true;
            }
        });
    }



    private boolean onTouchMove(View v, MotionEvent e){

        JogView jv = (JogView)v;

        float angle = this.touchAngle(e.getX(), e.getY());

        if ( jv.setJogAngle(angle) != 0){
            jv.invalidate();
        }

        return true;

    }


    /**
     *   LISTENER
     */

    public interface JogViewListener{
        void onValueChanged(float angle, float counter);
    }

    public void setJogViewListener(JogViewListener listener){
        this.listener = listener;
    }



    private Paint getStroke() {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setStrokeWidth(jogStroke);
        p.setColor(jogDotFillColor);
        p.setStyle(Paint.Style.STROKE);
        return p;
    }

    private Paint getFill() {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(jogFillColor);
        p.setStyle(Paint.Style.FILL);
        return p;
    }

    public float  getNewAngleAfterMotionTouch(float X, float Y){

        setDotArea( getPosition(centerPos, jogDotOffset, jogAngle),jogDotRadius);
        if ( !isInDotArea(X,Y)) return this.jogAngle;

        return touchAngle(X,Y);
    }

    private PointF getPosition(PointF center, float radius, float angle) {

        return  new PointF((float) (center.x + radius * Math.cos(Math.toRadians(angle))),
                (float) (center.y + radius* Math.sin(Math.toRadians(angle))));

    }



    public PointF getDotPosition(){

        return dotPos;
    }


    public void setDotArea(PointF dotPos, int jogDotRadius) {

        this.minAreaTouch.set(dotPos.x - jogDotRadius - jogAreaMargin,
                dotPos.y - jogDotRadius - jogAreaMargin);

        this.maxAreaTouch.set(dotPos.x + jogDotRadius + jogAreaMargin,
                dotPos.y + jogDotRadius + jogAreaMargin);
    }

    /**
     * Check if touch event is located in disc area
     * @param touchX : X position of the finger in this view
     * @param touchY : Y position of the finger in this view
     */
    private boolean isInDotArea(float touchX, float touchY) {

        return
                (touchX <= this.maxAreaTouch.x) && (touchX >= this.minAreaTouch.x ) &&
                        (touchY <= this.maxAreaTouch.y) && (touchY >= this.minAreaTouch.y );

    }


    /**
     * Compute a touch angle in degrees from center
     * North = 0, East = 90, West = -90, South = +/-180
     * @param touchX : X position of the finger in this view
     * @param touchY : Y position of the finger in this view
     * @return angle
     */
    public float touchAngle(float touchX, float touchY) {
        float dX = touchX - centerPos.x;
        float dY = centerPos.y - touchY;
        float rawAngle = normAngle((float)(270d - Math.toDegrees(Math.atan2(dY, dX))));

        float deltaAngle = getDelta(lastAngle, rawAngle);

        lastAngle = rawAngle;
        millisCounter = millisCounter + deltaAngle;

        return normAngle((float)((270f - Math.toDegrees(Math.atan2(dY, dX))) % 360f - 180f)-90f);
    }



    private float getDelta(float lastAngle, float newAngle){
        float alpha = newAngle - lastAngle;
        float delta;

        if (Math.abs(alpha)>180f) {
            delta = Math.signum(alpha) * (Math.abs(alpha)-360);
        }else{
            delta = alpha;
        }

        return delta;
    }


    @Override
    protected int hGetMaximumHeight() {
        return Math.max((jogRadius + jogStroke)* 2, (jogDotOffset + jogDotRadius)*2 );
    }

    @Override
    protected int hGetMaximumWidth() {
        return Math.max((jogRadius + jogStroke)* 2, (jogDotOffset + jogDotRadius)*2 );
    }

    public int getJogRadius() {
        return jogRadius;
    }

    public void setJogRadius(int jogRadius) {
        this.jogRadius = jogRadius;
    }

    public int getJogStroke() {
        return jogStroke;
    }

    public void setJogStroke(int jogStroke) {
        this.jogStroke = jogStroke;
    }

    public int getJogDotRadius() {
        return jogDotRadius;
    }

    public void setJogDotRadius(int jogDotRadius) {
        this.jogDotRadius = jogDotRadius;
    }

    public int getJogDotOffset() {
        return jogDotOffset;
    }

    public void setJogDotOffset(int jogDotOffset) {
        this.jogDotOffset = jogDotOffset;
    }

    public float getJogAngle() {
        return jogAngle;
    }

    /**
     * set internal variable jogAngle
     *
     * @param jogAngle  returns position of the spot
     * @return  return delta to previous
     */
    public float setJogAngle(float jogAngle) {
        jogAngle = normAngle(jogAngle);
        float delta = this.jogAngle - jogAngle;
        this.jogAngle = jogAngle;
        return delta;
    }

    public float normAngle(float aAngle){
        return (aAngle+360f) % 360f;
    }


    public int getJogDotFillColor() {
        return jogDotFillColor;
    }

    public void setJogDotFillColor(int jogDotFillColor) {
        this.jogDotFillColor = jogDotFillColor;
    }

    public int getDotFillColor() {
        return jogFillColor;
    }

    public void setDotFillColor(int dotFillColor) {
        this.jogFillColor = dotFillColor;
    }


    public long getMillisCounter(){
        return Math.round(millisCounter);
    }


}
