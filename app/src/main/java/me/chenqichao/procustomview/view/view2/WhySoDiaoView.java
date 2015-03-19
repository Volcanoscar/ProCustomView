package me.chenqichao.procustomview.view.view2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import me.chenqichao.procustomview.R;

/**
 * Created with Android Studio.
 *
 * @author Qichao Chen
 * @see <a href="http://blog.csdn.net/aigestudio/article/details/41212583">http://blog.csdn.net/aigestudio/article/details/41212583</a>
 * Info: 自定义控件其实很简单系列
 */
public class WhySoDiaoView extends View {

    boolean flag = true;
    private int w, h;
    private Bitmap fgBitmap, bgBitmap;
    private Canvas mCanvas;
    private Paint mPaint;
    private Path mPath;
    private float preX, preY;
    private Context context;

    //一般会这样写自定义View的初始化函数,当然不是绝对
    //动态创建View时调用，例如在Activity中使用这样代码：SimpleCustomView scv = new SimpleCustomView(context)
    public WhySoDiaoView(Context context) {
        this(context, null);
    }

    //Api 21以上多了一个构造函数
    /*
    public SimpleCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    */

    //在xml中声明控件，则会自动调用第二个构造函数
    public WhySoDiaoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //第三个函数系统是不调用的，要由View显式调用，比如在这里我们在第二个构造函数中调用了第三个构造函数。
    //第三个参数的意义就如同它的名字所说的，是默认的Style，这里的默认的Style是指它在当前Application或Activity所用的Theme中的默认Style
    public WhySoDiaoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context);
    }

    private void init(Context context) {
        //自定View初始化工作
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setARGB(128, 255, 0, 0);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(50);


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
        if (flag) {
            fgBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            bgBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
            flag = false;
            mCanvas = new Canvas(fgBitmap);
            mCanvas.drawColor(0xFF808080);
            canvas.drawBitmap(bgBitmap, 0, 0, null);
            canvas.drawBitmap(fgBitmap, 0, 0, null);
            mCanvas.drawPath(mPath, mPaint);
        } else {
            canvas.drawBitmap(bgBitmap, 0, 0, null);
            canvas.drawBitmap(fgBitmap, 0, 0, null);
            mCanvas.drawPath(mPath, mPaint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(x, y);
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = Math.abs(x - preX);
                float dy = Math.abs(y - preY);
                if (dx >= 5 || dy > 5) {
                    mPath.quadTo(preX, preY, (preX + x) / 2, (preY + y) / 2);
                    preX = x;
                    preY = y;
                }

                break;
        }
        invalidate();
        return true;
    }
}
