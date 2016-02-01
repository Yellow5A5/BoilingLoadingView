package yellow5a5.demo.boilingloadingview.View;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

import yellow5a5.demo.boilingloadingview.R;

/**
 * Created by Weiwu on 16/1/4.
 */
public class FlameView extends View {

    private Paint mPaint;
    private Path mPath;

    private int mFlameNum;
    private float mSumWidth;
    private float mSumHeight;

    private float mSingleWidth;
    private float mFlameSingleHeight;

    private boolean isFirstAnim = true;
    private float mAnimPare = 0;
    private ValueAnimator mLayerAnim;

    private Random mRandom;
    private float CHANGE_FACTOR = 20;

    //是否已测量得到宽高值
    private boolean isFinishMeasure = false;

    public FlameView(Context context) {
        this(context, null);
    }

    public FlameView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.FlameView);
        mFlameNum = array.getInteger(R.styleable.FlameView_FlameView_flameNum, 5);
        initData();
        initAnim();
    }

    public void startFlaming() {
        mLayerAnim.start();
        this.setVisibility(View.VISIBLE);
    }

    public void stopFlaming() {
        mAnimPare = 0;
        isFirstAnim = true;
        mLayerAnim.end();
        this.setVisibility(View.INVISIBLE);
    }

    private void initData() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPath = new Path();
        mRandom = new Random();
    }

    private void initAnim() {
        mLayerAnim = ValueAnimator.ofFloat(0f, 1f, 0.8f).setDuration(3000);
        mLayerAnim.setRepeatMode(ValueAnimator.RESTART);
        mLayerAnim.setRepeatCount(-1);
        mLayerAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (isFirstAnim)
                    mAnimPare = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        mLayerAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
                if (isFirstAnim) {
                    isFirstAnim = false;
                }
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isFinishMeasure) {
            mSumWidth = getMeasuredWidth();
            mSumHeight = getMeasuredHeight();
            mSingleWidth = mSumWidth / mFlameNum;
            mFlameSingleHeight = mSumHeight / 4;
            isFinishMeasure = true;
        }
        updataFlame(canvas);
    }

    private void updataFlame(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(0, mSumHeight);
        for (int i = 1; i <= mFlameNum; i++) {
            mPath.quadTo(mSingleWidth * i - mSingleWidth / 2, mFlameSingleHeight * 3 * mAnimPare, mSingleWidth * i, mSumHeight);
        }
        for (int i = mFlameNum; i >= 1; i--) {
            mPath.quadTo(mSingleWidth * i - mSingleWidth / 2 + ((1 - mRandom.nextFloat()) * CHANGE_FACTOR), -mAnimPare * mSumHeight, mSingleWidth * (i - 1), mSumHeight);
        }
        //颜色变化
        mPaint.setShader(new LinearGradient(mSumWidth / 2, mSumHeight, mSumWidth / 2, 0, Color.YELLOW, Color.RED, Shader.TileMode.REPEAT));
        canvas.drawPath(mPath, mPaint);
        //原本想绘制三层火焰颜色，发现效果不好，以下：
//        for (int layer = 3; layer > 0; layer--) {
//            for (int i = 1; i <= mFlameNum; i++) {
//                mPath.quadTo(mSingleWidth * i - mSingleWidth / 2, mFlameSingleHeight * (layer), mSingleWidth * i, mSumHeight);
//            }
//            for (int i = mFlameNum; i >= 1; i--) {
//                mPath.quadTo(mSingleWidth * i - mSingleWidth / 2, mFlameSingleHeight * (layer - 1), mSingleWidth * (i - 1), mSumHeight);
//            }
//            switch (layer) {
//                case 1:
//                    mPaint.setColor(Color.RED);
//                    break;
//                case 2:
//                    mPaint.setColor(Color.YELLOW);
//                    break;
//                case 3:
//                    mPaint.setColor(Color.BLUE);
//                    break;
//            }
//            canvas.drawPath(mPath, mPaint);
//            mPath.reset();
//            mPath.moveTo(0, mSumHeight);
//        }
    }
}
