package me.chenqichao.procustomview.view.view3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
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
public class BulrMaskFilterDemo extends View {

    private Paint mPaint, shadowPaint;
    private int w, h;
    private Context context;
    private Bitmap srcBitmap, shadowBitmap;

    //一般会这样写自定义View的初始化函数,当然不是绝对
    //动态创建View时调用，例如在Activity中使用这样代码：SimpleCustomView scv = new SimpleCustomView(context)
    public BulrMaskFilterDemo(Context context) {
        this(context, null);
    }

    //在xml中声明控件，则会自动调用第二个构造函数
    public BulrMaskFilterDemo(Context context, AttributeSet attrs) {
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
    public BulrMaskFilterDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        //自定View初始化工作

        //初始化第一个中类型画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(50);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        srcBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.view);
        //get Alpha chanel btimap
        shadowBitmap = srcBitmap.extractAlpha();
        shadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        shadowPaint.setColor(Color.DKGRAY);
        shadowPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
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
        int x = w / 2 - srcBitmap.getWidth() / 2;
        int y = h / 2 - srcBitmap.getHeight() / 2;
        canvas.drawBitmap(shadowBitmap, x, y, shadowPaint);
        canvas.drawBitmap(srcBitmap, x, y, null);
    }
}
