package me.chenqichao.procustomview.view.view2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created with Android Studio.
 *
 * @author Qichao Chen
 * @see <a href="http://blog.csdn.net/aigestudio/article/details/41212583">http://blog.csdn.net/aigestudio/article/details/41212583</a>
 * Info: 自定义控件其实很简单系列
 */
public class ColorFilterView1 extends View {

    private Paint mPaint;
    private int radius = 200;

    //一般会这样写自定义View的初始化函数,当然不是绝
    //动态创建View时调用，例如在Activity中使用这样代码：SimpleCustomView scv = new SimpleCustomView(context)
    public ColorFilterView1(Context context) {
        this(context, null);
    }

    //在xml中声明控件，则会自动调用第二个构造函数
    public ColorFilterView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //第三个函数系统是不调用的，要由View显式调用，比如在这里我们在第二个构造函数中调用了第三个构造函数。
    //第三个参数的意义就如同它的名字所说的，是默认的Style，这里的默认的Style是指它在当前Application或Activity所用的Theme中的默认Style
    public ColorFilterView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //Api 21以上多了一个构造函数
    /*
    public SimpleCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    */

    private void init() {
        //自定View初始化工作
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.argb(255, 255, 128, 103));
        //设置彩色变换矩阵
        mPaint.setColorFilter(new ColorMatrixColorFilter(getColorMatrix()));
        mPaint.setStrokeWidth(10);
    }


    private int w, h;

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
        canvas.drawCircle(w / 2, h / 2, radius, mPaint);
    }

    private ColorMatrix getColorMatrix() {
        return new ColorMatrix(new float[]{
                0.5f, 0, 0, 0, 0,
                0, 0.5f, 0, 0, 0,
                0, 0, 0.5f, 0, 0,
                0, 0, 0, 1, 0,
        });
    }
}
