package me.chenqichao.procustomview.view.view2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import me.chenqichao.procustomview.R;

/**
 * Created with Android Studio.
 *
 * @author Qichao Chen
 * @see <a href="http://blog.csdn.net/aigestudio/article/details/41212583">http://blog.csdn.net/aigestudio/article/details/41212583</a>
 * Info: 自定义控件其实很简单系列
 */
public class ColorFilterView2 extends View {

    private Paint mPaint;
    private Bitmap bitmap;
    private int x, y;
    private int currentEffectId = 0;

    //一般会这样写自定义View的初始化函数,当然不是绝
    //动态创建View时调用，例如在Activity中使用这样代码：SimpleCustomView scv = new SimpleCustomView(context)
    public ColorFilterView2(Context context) {
        this(context, null);
    }

    //在xml中声明控件，则会自动调用第二个构造函数
    public ColorFilterView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //第三个函数系统是不调用的，要由View显式调用，比如在这里我们在第二个构造函数中调用了第三个构造函数。
    //第三个参数的意义就如同它的名字所说的，是默认的Style，这里的默认的Style是指它在当前Application或Activity所用的Theme中的默认Style
    public ColorFilterView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //Api 21以上多了一个构造函数
    /*
    public SimpleCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    */

    private void init(Context context) {
        //自定View初始化工作
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.view);
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
            h = width;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            h = Math.min(w, width);
        } else {
            //Mode:UNSPECIFIED 暂不处理

        }

        //计算bitmap画的位置（居中）
        x = w / 2 - bitmap.getWidth() / 2;
        y = h / 2 - bitmap.getHeight() / 2;

        //必须执行下面代码
        setMeasuredDimension(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, x, y, mPaint);
    }

    public void ShowNextEffect() {
        currentEffectId++;
        switch (currentEffectId) {
            case 1:
                mPaint.setColorFilter(new ColorMatrixColorFilter(getColorMatrix1()));
                invalidate();
                break;
            case 2:
                mPaint.setColorFilter(new ColorMatrixColorFilter(getColorMatrix2()));
                invalidate();
                break;
            case 3:
                mPaint.setColorFilter(new ColorMatrixColorFilter(getColorMatrix3()));
                invalidate();
                break;
            case 4:
                mPaint.setColorFilter(new ColorMatrixColorFilter(getColorMatrix4()));
                invalidate();
                break;
            case 5:
                mPaint.setColorFilter(new ColorMatrixColorFilter(getColorMatrix5()));
                invalidate();
                break;
            case 6:
                mPaint.setColorFilter(new ColorMatrixColorFilter(getColorMatrix6()));
                invalidate();
                break;
            case 7:
                currentEffectId = 0;
                mPaint.setColorFilter(new ColorMatrixColorFilter(getColorMatrix7()));
                invalidate();
                break;
        }
    }

    //变暗
    private ColorMatrix getColorMatrix1() {
        return new ColorMatrix(new float[]{
                0.5f, 0, 0, 0, 0,
                0, 0.5f, 0, 0, 0,
                0, 0, 0.5f, 0, 0,
                0, 0, 0, 1, 0,
        });
    }

    //灰度化
    private ColorMatrix getColorMatrix2() {
        return new ColorMatrix(new float[]{
                0.33f, 0.59f, 0.11f, 0, 0,
                0.33f, 0.59f, 0.11f, 0, 0,
                0.33f, 0.59f, 0.11f, 0, 0,
                0, 0, 0, 1, 0,
        });
    }

    //反相
    private ColorMatrix getColorMatrix3() {
        return new ColorMatrix(new float[]{
                -1, 0, 0, 1, 1,
                0, -1, 0, 1, 1,
                0, 0, -1, 1, 1,
                0, 0, 0, 1, 0,
        });
    }

    //RGB->BRG
    private ColorMatrix getColorMatrix4() {
        return new ColorMatrix(new float[]{
                0, 0, 1, 0, 0,
                0, 1, 0, 0, 0,
                1, 0, 0, 0, 0,
                0, 0, 0, 1, 0,
        });
    }

    //老照片
    private ColorMatrix getColorMatrix5() {
        return new ColorMatrix(new float[]{
                0.393f, 0.769f, 0.189f, 0, 0,
                0.349f, 0.686f, 0.168f, 0, 0,
                0.272f, 0.534f, 0.131f, 0, 0,
                0, 0, 0, 1, 0,
        });
    }

    //去色高对比
    private ColorMatrix getColorMatrix6() {
        return new ColorMatrix(new float[]{
                1.5f, 1.5f, 1.5f, 0, -1,
                1.5f, 1.5f, 1.5f, 0, -1,
                1.5f, 1.5f, 1.5f, 0, -1,
                0, 0, 0, 1, 0,
        });
    }

    //饱和对比度加强
    private ColorMatrix getColorMatrix7() {
        return new ColorMatrix(new float[]{
                1.438f, -0.122f, -0.016f, 0, -0.03f,
                -0.062f, 1.378f, -0.016f, 0, 0.05f,
                -0.062f, -0.122f, 1.483f, 0, -0.02f,
                0, 0, 0, 1, 0,
        });
    }
}
