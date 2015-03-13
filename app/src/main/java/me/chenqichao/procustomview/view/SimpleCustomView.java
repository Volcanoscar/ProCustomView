package me.chenqichao.procustomview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created with Android Studio.
 * @author Qichao Chen
 * @see <a href="http://blog.csdn.net/aigestudio/article/details/41212583">http://blog.csdn.net/aigestudio/article/details/41212583</a>
 * Info: 自定义控件其实很简单系列
 */
public class SimpleCustomView extends View {

    //一般会这样写自定义View的初始化函数当然在

    public SimpleCustomView(Context context) {
        super(context);
    }

    public SimpleCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //Api 21以上多了一个构造函数
    /*
    public SimpleCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    */
}
