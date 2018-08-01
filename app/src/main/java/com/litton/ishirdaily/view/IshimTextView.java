package com.litton.ishirdaily.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.litton.ishirdaily.R;

/**
 * 自定义View Day01
 * Created by littonishir on 2018/8/1.
 */
public class IshimTextView extends LinearLayout {

    private String mText;
    private int mTextSize = 15;
    private int mTextColor = Color.BLACK;
    private Paint mPaint;

    /**
     * 代码new时调用
     * IshimTextView ishimTextView = new IshimTextView(this);
     *
     * @param context
     */
    public IshimTextView(Context context) {
        this(context, null);
    }

    /**
     * 布局文件中使用时调用
     * <p>
     * <com.ishir.view.IshimTextView
     * app:text="hello view"
     * android:layout_width="wrap_content"
     * android:layout_height="wrap_content" />
     *
     * @param context
     * @param attrs
     */
    public IshimTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    /**
     * 布局文件中且使用style时调用
     * <p>
     * <com.hc.view_day03.IshimTextView
     * app:text="hello view"
     * style="@style/IshimTextStyle" />
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public IshimTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    /**
     * 为TypedArray提供默认值的样式资源的资源标识符，仅在defStyleAttr为0或在主题中找不到时使用。可以为0以查找默认值。
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    public IshimTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        // 获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.IshimTextView);
        mText = array.getString(R.styleable.IshimTextView_text);
        mTextColor = array.getColor(R.styleable.IshimTextView_textColor, mTextColor);
        mTextSize = array.getDimensionPixelSize(R.styleable.IshimTextView_textSize, sp2px(mTextSize));

        // 回收
        array.recycle();
        // 初始化画笔
        mPaint = new Paint();
        // 抗锯齿
        mPaint.setAntiAlias(true);
        // 设置字体的大小和颜色
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);

        /**
         * ViewGroup默认情况下，出于性能考虑，会被设置成WILL_NOT_DROW，onDraw不会被执行。
         * 如果我们想重写一个ViewGroup的onDraw方法，有两种方法：
         * 1，构造函数中，给ViewGroup设置一个颜色。
         * 2，构造函数中，调用setWillNotDraw（false），去掉其WILL_NOT_DRAW flag。
         * 为了这个知识点所以继承自LinearLayout(可直接继承自View)
         */
//         setBackgroundColor(Color.GRAY);
        setWillNotDraw(false);
    }


    /**
     * 自定义View的测量方法
     * 布局的宽高都是由这个方法指定
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取宽高的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // 1.确定的值，这个时候不需要计算，给的多少就是多少
        int width = MeasureSpec.getSize(widthMeasureSpec);
        // 2.给的是wrap_content 需要计算
        if (widthMode == MeasureSpec.AT_MOST) {
            // 计算的宽度 与 字体的长度有关  与字体的大小  用画笔来测量
            Rect bounds = new Rect();
            // 获取文本的Rect
            mPaint.getTextBounds(mText, 0, mText.length(), bounds);
            width = bounds.width() + getPaddingLeft() + getPaddingRight();
        }

        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST) {
            // 计算的宽度 与 字体的长度有关  与字体的大小  用画笔来测量
            Rect bounds = new Rect();
            // 获取文本的Rect
            mPaint.getTextBounds(mText, 0, mText.length(), bounds);
            height = bounds.height() + getPaddingTop() + getPaddingBottom();
        }
        // 设置控件的宽高
        setMeasuredDimension((int) (width * 1.05), (int) (height * 1.2));
    }

    /**
     * 用于绘制
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        //dy 代表的是：高度的一半到 baseLine的距离
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        // top 是一个负值  bottom 是一个正值    top，bttom的值代表是  bottom是baseLine到文字底部的距离（正值）
        // 必须要清楚的，可以自己打印就好
        int top = fontMetrics.top;
        int ascent = fontMetrics.ascent;
        int leading = fontMetrics.leading;
        int descent = fontMetrics.descent;
        int bottom = fontMetrics.bottom;
        Log.e("", "onDraw: top" + top);
        Log.e("", "onDraw: ascent" + ascent);
        Log.e("", "onDraw: leading" + leading);
        Log.e("", "onDraw: descent" + descent);
        Log.e("", "onDraw: bottom" + bottom);
        int dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        int baseLine = getHeight() / 2 + dy;
        int x = getPaddingLeft();
        /**
         * 使用指定的颜料绘制文本，原点为文本左下角（x，y）
         *
         * @param text要绘制的文本
         * @param x正在绘制的文本原点的x坐标
         * @param y正在绘制的文本基线的y坐标
         * @param paint用于文本的颜料（例如颜色，大小，样式）
         */
        canvas.drawText(mText, x, baseLine, mPaint);
    }

    /**
     * 处理跟用户交互的，手指触摸等等
     *
     * @param event 事件分发事件拦截
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 手指按下
                Log.e("TAG", "手指按下");
                break;

            case MotionEvent.ACTION_MOVE:
                // 手指移动
                Log.e("TAG", "手指移动");
                break;

            case MotionEvent.ACTION_UP:
                // 手指抬起
                Log.e("TAG", "手指抬起");
                break;
        }
        invalidate();
        return super.onTouchEvent(event);
    }

    /**
     * sp->px
     *
     * @param sp
     * @return
     */
    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                getResources().getDisplayMetrics());
    }
}
