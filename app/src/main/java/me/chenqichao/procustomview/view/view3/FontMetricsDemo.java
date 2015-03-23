package me.chenqichao.procustomview.view.view3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created with Android Studio.
 *
 * @author Qichao Chen
 * @see <a href="http://blog.csdn.net/aigestudio/article/details/41212583">http://blog.csdn.net/aigestudio/article/details/41212583</a>
 * Info: 自定义控件其实很简单系列
 */
public class FontMetricsDemo extends View {

    private static final String text = "ap爱哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓OXg";
    private Paint mPaint, mPaint2, linePaint;
    private Paint.FontMetrics fontMetrics;
    private int w, h;

    //一般会这样写自定义View的初始化函数,当然不是绝对
    //动态创建View时调用，例如在Activity中使用这样代码：SimpleCustomView scv = new SimpleCustomView(context)
    public FontMetricsDemo(Context context) {
        this(context, null);
    }

    //在xml中声明控件，则会自动调用第二个构造函数
    public FontMetricsDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //Api 21以上多了一个构造函数
    /*
    public SimpleCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    */

    //第三个函数系统是不调用的，要由View显式调用，比如在这里我们在第二个构造函数中调用了第三个构造函数。
    //第三个参数的意义就如同它的名字所说的，是默认的Style，这里的默认的Style是指它在当前Application或Activity所用的Theme中的默认Style
    public FontMetricsDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //自定View初始化工作

        //初始化第一个中类型画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(50);
        fontMetrics = mPaint.getFontMetrics();
        mPaint.setTypeface(Typeface.SERIF);
        Log.d("TAG", "ascent:" + fontMetrics.ascent);
        Log.d("TAG", "top:" + fontMetrics.top);
        Log.d("TAG", "leading:" + fontMetrics.leading);
        Log.d("TAG", "descent:" + fontMetrics.descent);
        Log.d("TAG", "bottom:" + fontMetrics.bottom);

        mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint2.setTextSize(70);
        mPaint2.setColor(Color.RED);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(1);
        linePaint.setColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            w = width;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            w = Math.min(w, width);
        } else {
            //Mode:UNSPECIFIED 暂不处理

        }

        if (heightMode == MeasureSpec.EXACTLY) {
            h = height;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            h = Math.min(h, height);
        } else {
            //Mode:UNSPECIFIED 暂不处理

        }

        //必须执行下面代码
        setMeasuredDimension(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(text, 0, Math.abs(fontMetrics.top), mPaint);
        DrawPaint2(canvas);
    }

    private void DrawPaint2(Canvas canvas) {
        int baseX = (int) (canvas.getWidth() / 2 - mPaint2.measureText(text) / 2);

        //Real Center in Parent
        int baseY1 = (int) (canvas.getHeight() / 2 - (mPaint2.descent() + mPaint2.ascent()) / 2);
        canvas.drawText(text, baseX, baseY1, mPaint2);

        //Only BaseLine in Center
        int baseY2 = canvas.getHeight() / 2;
        mPaint2.setColor(Color.BLACK);
        canvas.drawText(text, baseX, baseY2, mPaint2);
        canvas.drawLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2, linePaint);
    }
}
