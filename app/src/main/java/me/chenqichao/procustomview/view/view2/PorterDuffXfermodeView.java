package me.chenqichao.procustomview.view.view2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import me.chenqichao.procustomview.BO.PorterDuffBO;
import me.chenqichao.procustomview.R;

/**
 * Created with Android Studio.
 *
 * @author Qichao Chen
 * @see <a href="http://blog.csdn.net/aigestudio/article/details/41212583">http://blog.csdn.net/aigestudio/article/details/41212583</a>
 * Info: 自定义控件其实很简单系列
 */
public class PorterDuffXfermodeView extends View {

    private static final int RECT_SIZE_SMALL = 400;
    private static final int RECT_SIZE_BIG = 800;
    private Paint mPaint;
    private Bitmap bitmap;
    private int x, y;
    private int currentEffectId = 0;
    private int w, h;
    private int src_left, src_top;
    private int dst_left, dst_top;
    private int rectX, rectY;
    private Context context;
    private PorterDuff.Mode mode;
    private PorterDuffBO porterDuffBO;

    //一般会这样写自定义View的初始化函数,当然不是绝
    //动态创建View时调用，例如在Activity中使用这样代码：SimpleCustomView scv = new SimpleCustomView(context)
    public PorterDuffXfermodeView(Context context) {
        this(context, null);
    }

    //Api 21以上多了一个构造函数
    /*
    public SimpleCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    */

    //在xml中声明控件，则会自动调用第二个构造函数
    public PorterDuffXfermodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
    }

    //第三个函数系统是不调用的，要由View显式调用，比如在这里我们在第二个构造函数中调用了第三个构造函数。
    //第三个参数的意义就如同它的名字所说的，是默认的Style，这里的默认的Style是指它在当前Application或Activity所用的Theme中的默认Style
    public PorterDuffXfermodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //自定View初始化工作
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        porterDuffBO = new PorterDuffBO();
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.view);
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

        //计算bitmap画的位置（居中）
        x = w / 2 - bitmap.getWidth() / 2;
        y = h / 2 - bitmap.getHeight() / 2;

        //必须执行下面代码
        setMeasuredDimension(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getRectSize();
        canvas.drawColor(Color.BLACK);
        porterDuffBO.setSize(RECT_SIZE_SMALL);
        canvas.drawBitmap(porterDuffBO.initSrcBitmap(), src_left, src_top, mPaint);
        canvas.drawBitmap(porterDuffBO.initDisBitmap(), dst_left, dst_top, mPaint);
        int sc = canvas.saveLayer(0, 0, w, h, null, Canvas.ALL_SAVE_FLAG);
        porterDuffBO.setSize(RECT_SIZE_BIG);
        canvas.drawBitmap(porterDuffBO.initDisBitmap(), rectX, rectY, mPaint);
        getXfermode();
        mPaint.setXfermode(new PorterDuffXfermode(mode));
        canvas.drawBitmap(porterDuffBO.initSrcBitmap(), rectX, rectY, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(sc);
    }

    private void getRectSize() {
        src_left = 0;
        src_top = 0;
        dst_left = w - RECT_SIZE_SMALL;
        dst_top = 0;
        rectX = w / 2 - RECT_SIZE_BIG / 2;
        rectY = RECT_SIZE_SMALL + (h - RECT_SIZE_SMALL) / 2 - RECT_SIZE_BIG / 2;
    }

    /**
     * CLEAR
     * SRC
     * DST
     * SRC_OVER
     * DST_OVER
     * SRC_IN
     * DST_IN
     * SRC_OUT
     * DST_OUT
     * SRC_ATOP
     * DST_ATOP
     * XOR
     * DARKEN
     * LIGHTEN
     * MULTIPLY
     * SCREEN
     * ADD
     * OVERLAY
     */
    public void getXfermode() {
        this.currentEffectId++;
        switch (currentEffectId) {
            case 1:
                Toast.makeText(context, "PorterDuffColorFilter : CLEAR", Toast.LENGTH_SHORT).show();
                mode = PorterDuff.Mode.CLEAR;
                break;
            case 2:
                Toast.makeText(context, "PorterDuffColorFilter : SRC", Toast.LENGTH_SHORT).show();
                mode = PorterDuff.Mode.SRC;
                break;
            case 3:
                Toast.makeText(context, "PorterDuffColorFilter : DST", Toast.LENGTH_SHORT).show();
                mode = PorterDuff.Mode.DST;
                break;
            case 4:
                Toast.makeText(context, "PorterDuffColorFilter : SRC_OVER", Toast.LENGTH_SHORT).show();
                mode = PorterDuff.Mode.SRC_OVER;
                break;
            case 5:
                Toast.makeText(context, "PorterDuffColorFilter : DST_OVER", Toast.LENGTH_SHORT).show();
                mode = PorterDuff.Mode.DST_OVER;
                break;
            case 6:
                Toast.makeText(context, "PorterDuffColorFilter : SRC_IN", Toast.LENGTH_SHORT).show();
                mode = PorterDuff.Mode.SRC_IN;
                break;
            case 7:
                Toast.makeText(context, "PorterDuffColorFilter : DST_IN", Toast.LENGTH_SHORT).show();
                mode = PorterDuff.Mode.DST_IN;
                break;
            case 8:
                Toast.makeText(context, "PorterDuffColorFilter : SRC_OUT", Toast.LENGTH_SHORT).show();
                mode = PorterDuff.Mode.SRC_OUT;
                break;
            case 9:
                Toast.makeText(context, "PorterDuffColorFilter : DST_OUT", Toast.LENGTH_SHORT).show();
                mode = PorterDuff.Mode.DST_OUT;
                break;
            case 10:
                Toast.makeText(context, "PorterDuffColorFilter : SRC_ATOP", Toast.LENGTH_SHORT).show();
                mode = PorterDuff.Mode.SRC_ATOP;
                break;
            case 11:
                Toast.makeText(context, "PorterDuffColorFilter : DST_ATOP", Toast.LENGTH_SHORT).show();
                mode = PorterDuff.Mode.DST_ATOP;
                break;
            case 12:
                Toast.makeText(context, "PorterDuffColorFilter : XOR", Toast.LENGTH_SHORT).show();
                mode = PorterDuff.Mode.XOR;
                break;
            case 13:
                Toast.makeText(context, "PorterDuffColorFilter : DARKEN", Toast.LENGTH_SHORT).show();
                mode = PorterDuff.Mode.DARKEN;
                break;
            case 14:
                Toast.makeText(context, "PorterDuffColorFilter : LIGHTEN", Toast.LENGTH_SHORT).show();
                mode = PorterDuff.Mode.LIGHTEN;
                break;
            case 15:
                Toast.makeText(context, "PorterDuffColorFilter : MULTIPLY", Toast.LENGTH_SHORT).show();
                mode = PorterDuff.Mode.MULTIPLY;
                break;
            case 16:
                Toast.makeText(context, "PorterDuffColorFilter : SCREEN", Toast.LENGTH_SHORT).show();
                mode = PorterDuff.Mode.SCREEN;
                break;
            case 17:
                Toast.makeText(context, "PorterDuffColorFilter : ADD", Toast.LENGTH_SHORT).show();
                mode = PorterDuff.Mode.ADD;
                break;
            case 18:
                this.currentEffectId = 0;
                Toast.makeText(context, "PorterDuffColorFilter : OVERLAY", Toast.LENGTH_SHORT).show();
                mode = PorterDuff.Mode.OVERLAY;
                break;
        }
    }

    public void ShowNextEffect() {
        this.context = context;
        invalidate();
    }


}
