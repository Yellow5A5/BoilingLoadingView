package yellow5a5.demo.boilingloadingview.View;

import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
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
    private Context mContext;

    private WaterView mWaterView;
    private ImageView mPanView;
    private ImageView mCoverView;
    private ClipDrawable mWaterDrawable;
    private Handler mHandle = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0X0000){
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
        mContext = context;
        mView = LayoutInflater.from(context).inflate(R.layout.boiling_pan, this, true);
        initView();
    }

    private void initView() {
        mWaterView = (WaterView) mView.findViewById(R.id.img_water);
        mPanView = (ImageView) mView.findViewById(R.id.img_pan);
        mCoverView = (ImageView) mView.findViewById(R.id.img_cover);

        mWaterDrawable = (ClipDrawable) mWaterView.getDrawable();
    }


    public void beginAnim(){
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandle.sendEmptyMessage(0X0000);
                if (mWaterDrawable.getLevel() >= 10000){
                    timer.cancel();
                }
            }
        },0,50);
    }

    public void resetAnim(){
        mWaterDrawable.setLevel(0);
        mWaterView.resetBubbleAnim();
    }
}
