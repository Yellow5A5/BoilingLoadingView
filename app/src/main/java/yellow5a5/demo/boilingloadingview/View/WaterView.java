package yellow5a5.demo.boilingloadingview.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import yellow5a5.demo.boilingloadingview.Util.Bubble;

/**
 * Created by Weiwu on 16/1/2.
 */
public class WaterView extends ImageView {

    private final int MAX_BUBBLE_SIZE = 8;
    private final int RISE_SPEED = 15;
    private final int LARGEN_SPEED = 1;
    private final int BUBBLE_TIME_INTERVAL = 300;

    private Paint mPaint;
    private List mBubbleList;
    private Drawable mDrawable;

    private int mWidth;
    private int mHeight;
    private Random mRandom;
    private boolean isFinishMeasure = false;
    private boolean isSetAnim = false;

    public WaterView(Context context) {
        this(context, null);
    }

    public WaterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.WaterView);
//        mDrawable = array.getDrawable(R.styleable.WaterView_WaterView_src);
//        mSpeed = array.getInteger(R.styleable.WaterView_WaterView_speed, 200);
        initPaint();
        initData();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    private void initData() {
        mDrawable = getDrawable();
        mRandom = new Random();
        mBubbleList = new ArrayList<Bubble>();
    }

    private void setDrawable(Drawable drawable) {
        mDrawable = drawable;
        setImageDrawable(mDrawable);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDrawable == null)
            return;
        if (!isFinishMeasure) {
            mWidth = getWidth();
            mHeight = getHeight();
            isFinishMeasure = true;
            return;
        }
        if (mDrawable.getLevel() != 0 && !isSetAnim) {
            beginAnimation();
            isSetAnim = true;
        }
        setbubble(mBubbleList, canvas);
    }

    private void beginAnimation() {
        for (int i = 0; i < 8; i++) {
            addBubble(BUBBLE_TIME_INTERVAL * i);
        }
    }

    private void addBubble(int delay) {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mBubbleList.size() < MAX_BUBBLE_SIZE) {
                    float centerX = mWidth * mRandom.nextFloat();
                    Bubble bubble = new Bubble(centerX, mHeight, 0);
                    bubble.setRiseSpeed(RISE_SPEED);
                    bubble.setLargenSpeed(LARGEN_SPEED);
                    mBubbleList.add(bubble);
                }
            }
        }, delay);
    }

    private void setbubble(List<Bubble> list, Canvas canvas) {
        if (list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            Bubble bubble = list.get(i);
            if (bubble.getCenterY() < mHeight - mHeight * mDrawable.getLevel() / 10000f) {
                mBubbleList.remove(bubble);
                addBubble(0);
            } else {
                bubble.beenLargen();
                bubble.beenRise();
            }
            canvas.drawCircle(bubble.getCenterX(), bubble.getCenterY(), bubble.getRadius(), mPaint);
        }
        invalidate();
    }

    public void resetBubbleAnim() {
        //清空消息队列
        getHandler().removeCallbacksAndMessages(null);
        isSetAnim = false;
        mBubbleList.clear();
    }
}
