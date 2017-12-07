package orion.garon.mycustomview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.style.LineHeightSpan;
import android.util.AttributeSet;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by VKI on 02.12.2017.
 */

public class CustomView extends View {

    private int squaresCount;
    private int startColor;

    private boolean isFirstLaunch;

    private Paint squarePaint;



    public CustomView(Context context, AttributeSet attributeSet) {

        super(context, attributeSet);
        init();
    }


    private void init() {

        squarePaint = new Paint();
        squarePaint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        int width = this.getWidth();
        int height = this.getHeight();

        int square = width * height;
        int squareX = width * 50;
        squaresCount = (int)(square / 250);
        int squareY = height * 50;
        int rowsCountX = (int)(square / squareX);
        int rowsCountY = (int)(square / squareY);


        for(int i = 0; i < rowsCountY; i++)
        {
            for(int j = 0; j < rowsCountX; j++)
            {
                if(!isFirstLaunch) {

                    if(i % 2 == 0 && j % 2 != 0)
                    {
                        squarePaint.setColor(Color.YELLOW);
                    }
                    else
                    {
                        squarePaint.setColor(Color.BLACK);
                    }
                }
                else
                {
                    Random random = new Random();
//                    squarePaint.setARGB(
//                            255,
//                            random.nextInt(255),
//                            random.nextInt(255),
//                            random.nextInt(255)
//                    );
                    squarePaint.setColor(startColor);
                    squarePaint.setAlpha(random.nextInt(256));
                }

                canvas.drawRect(i * 50, j * 50, 50 + i * 50, 50 + j * 50, squarePaint);
            }
        }

        if(!isFirstLaunch) {
            isFirstLaunch = true;
        }
    }

    //region Properties
    public boolean isFirstLaunch() {

        return isFirstLaunch;
    }

    public int getSquaresCount() {

        return squaresCount;
    }

    public void setSquaresCount(int squaresCount) {

        this.squaresCount = squaresCount;
        invalidate();
        requestLayout();
    }

    public void setStartColor(int color) {

        this.startColor = color;
        invalidate();
        requestLayout();
    }
    //endregion
}
