package yellow5a5.demo.boilingloadingview.View;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

import yellow5a5.demo.boilingloadingview.R;

/**
 * Created by Weiwu on 16/1/2.
 */
public class BoilingPanView extends RelativeLayout {


    private View mView;
    private ClipDrawable mWaterDrawable;

    private WaterView mWaterView;
    private FlameView mFlameView;

    private View mPea1;
    private View mPea2;
    private ImageView mPotato;
    private ImageView mCarrot;
    private ImageView mCoverView;

    private Animation mLeftInAnim;
    private Animation mRightInAnim;

    private boolean isRightRotate = true;
    private ValueAnimator mCoverAnim;

    private BoilingAnimListener mBoilingAnimListener;

    public interface BoilingAnimListener {
        //初始动画结束监听
        void onFirstAnimEnd();
    }

    public void setBoilingAnimListener(BoilingAnimListener l) {
        this.mBoilingAnimListener = l;
    }

    private Handler mHandle = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0X0000) {
                mWaterDrawable.setLevel(mWaterDrawable.getLevel() + 800);
            }
            return false;
        }
    });

    public BoilingPanView(Context context) {
        this(context, null);
    }

    public BoilingPanView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BoilingPanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mView = LayoutInflater.from(context).inflate(R.layout.boiling_pan, this, true);
        initView();
        initStartAnim();
        initCoverAnim();
    }

    private void initView() {
        mWaterView = (WaterView) mView.findViewById(R.id.img_water);
        mFlameView = (FlameView) mView.findViewById(R.id.flame);
        mCoverView = (ImageView) mView.findViewById(R.id.img_cover);
        mPea1 = mView.findViewById(R.id.img_pea1);
        mPea2 = mView.findViewById(R.id.img_pea2);
        mPotato = (ImageView) mView.findViewById(R.id.img_potato);
        mCarrot = (ImageView) mView.findViewById(R.id.img_carrot);
        mWaterDrawable = (ClipDrawable) mWaterView.getDrawable();
    }

    private void initStartAnim() {
        mLeftInAnim = AnimationUtils.loadAnimation(getContext(), R.anim.left_in_anim);
        mRightInAnim = AnimationUtils.loadAnimation(getContext(), R.anim.right_in_anim);
    }

    /*
    抖动的盖子
     */
    private void initCoverAnim() {
        mCoverAnim = ValueAnimator.ofFloat(0f, 1f, 0f).setDuration(800);
        mCoverAnim.setRepeatMode(Animation.REVERSE);
        mCoverAnim.setRepeatCount(-1);
        mCoverAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                if (isRightRotate) {
                    mCoverView.setRotation(value * 5);
                } else {
                    mCoverView.setRotation(-value * 5);
                }
                mCoverView.setTranslationY(-value * TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
            }
        });
        mCoverAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
                isRightRotate = !isRightRotate;
            }
        });
    }

    /*
    开始启动的动画
     */
    public void beginFirstInAnim() {
        mPea1.setVisibility(VISIBLE);
        mPea2.setVisibility(VISIBLE);
        mPotato.setVisibility(VISIBLE);
        mCarrot.setVisibility(VISIBLE);
        mCoverView.setVisibility(VISIBLE);
        mPea1.startAnimation(mLeftInAnim);
        mPea2.startAnimation(mLeftInAnim);
        mPotato.startAnimation(mLeftInAnim);
        mCarrot.startAnimation(mRightInAnim);
        mCoverView.startAnimation(mRightInAnim);
        mRightInAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mBoilingAnimListener != null) {
                    //这里是为了给外部留有操作的空间
                    mBoilingAnimListener.onFirstAnimEnd();
                } else {
                    beginBoilingAnim();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /*
    开始加水燃火动画
     */
    public void beginBoilingAnim() {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandle.sendEmptyMessage(0X0000);
                if (mWaterDrawable.getLevel() >= 10000) {
                    timer.cancel();
                }
            }
        }, 0, 50);
        mFlameView.startFlaming();
        mCoverAnim.start();
    }

    /*
    重置动画
     */
    public void resetAnim() {
        mWaterDrawable.setLevel(0);
        mWaterView.resetBubbleAnim();
        mFlameView.stopFlaming();
        mPea1.setVisibility(INVISIBLE);
        mPea2.setVisibility(INVISIBLE);
        mPotato.setVisibility(INVISIBLE);
        mCarrot.setVisibility(INVISIBLE);
        mCoverView.setVisibility(INVISIBLE);

    }
}
