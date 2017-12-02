package orion.garon.mycustomview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.style.LineHeightSpan;
import android.util.AttributeSet;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by VKI on 02.12.2017.
 */

public class CustomView extends View {

    private boolean showText;
    private int textPos;

    private Paint textPaint;
    private int textColor;
    private float textHeight;

    private Paint piePaint;
    private Paint shadowPaint;

    private GestureDetector detector;

    public CustomView(Context context, AttributeSet attributeSet) {

        super(context, attributeSet);
        TypedArray array = context.getTheme().obtainStyledAttributes(
                attributeSet,
                R.styleable.CustomView,
                0,
                0);

        try {

            showText = array.getBoolean(R.styleable.CustomView_showText, false);
            textPos = array.getInteger(R.styleable.CustomView_labelPosition, 0);
        } finally {

            array.recycle();
        }

        detector = new GestureDetector(CustomView.this.getContext(), new CustomListener());
        textColor = R.color.black;

        init();
    }


    private void init() {

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(textColor);
        if (textHeight == 0) {

            textHeight = textPaint.getTextSize();
        } else {

            textPaint.setTextSize(textHeight);
        }

        piePaint = new Paint();
        piePaint.setStyle(Paint.Style.STROKE);
        piePaint.setColor(textColor);

        shadowPaint = new Paint(0);
        shadowPaint.setColor(0xff101010);
        shadowPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);

        // Account for padding
        float xpad = (float)(getPaddingLeft() + getPaddingRight());
        float ypad = (float)(getPaddingTop() + getPaddingBottom());

        // Account for the label
        if (showText) xpad += textHeight;

        float ww = (float)w - xpad;
        float hh = (float)h - ypad;

        // Figure out how big we can make the pie.
        float diameter = Math.min(ww, hh);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        boolean result = detector.onTouchEvent(event);

        if(!result) {

            if(event.getAction() == MotionEvent.ACTION_UP) {

                stopNestedScroll();
                result = true;
            }
        }

        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        piePaint.setStrokeWidth(10.0f);
        canvas.drawRect(30, 30, 300, 80, piePaint);


//        canvas.drawLine(size.x * 0.5f, 0.0f, size.x * 0.5f, size.y, piePaint);
//        canvas.drawLine(0.0f, size.y * 0.5f, size.x, size.y * 0.5f, piePaint);

        canvas.drawText("ABCD", 15, 15, textPaint);



//        canvas.drawLine();
    }

    //region Properties
    public boolean isShowText() {

        return showText;
    }

    public void setShowText(boolean showText) {

        this.showText = showText;
        invalidate();
        requestLayout();
    }

    public int getTextPos() {

        return textPos;
    }

    public void setTextPos(int textPos) {

        this.textPos = textPos;
        invalidate();
        requestLayout();
    }
    //endregion
}
