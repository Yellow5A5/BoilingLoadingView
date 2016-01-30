package yellow5a5.demo.boilingloadingview.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by Weiwu on 16/1/10.
 */
public class BoilingListView extends ListView implements AbsListView.OnScrollListener {

    private BoilingPanView mHeadView;

    private int mHeadHeight;
    private int mFirstVisibleItem;

    private int mScrollState;
    private boolean isTouchFirstFlag = false;

    public BoilingListView(Context context) {
        this(context, null);
    }

    public BoilingListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BoilingListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mHeadView = new BoilingPanView(context);
        measureView(mHeadView);
        mHeadHeight = mHeadView.getMeasuredHeight();
        setTopPadding(-mHeadHeight);
        mHeadView.invalidate();
        this.addHeaderView(mHeadView, null, false);
        this.setOnScrollListener(this);
    }

    //此方法可以参考ListView的measureItem源码。
    private void measureView(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int width = ViewGroup.getChildMeasureSpec(0, 0, params.width);
        int height;
        int tempHeight = params.height;
        if (tempHeight > 0) {
            height = MeasureSpec.makeMeasureSpec(tempHeight, MeasureSpec.EXACTLY);
        } else {
            height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        view.measure(width, height);
    }

    private void setTopPadding(int topPadding) {
        mHeadView.setPadding(0, topPadding, 0, 0);
        mHeadView.setScaleX(topPadding / (float) mHeadHeight + 1);
        mHeadView.setScaleY(topPadding / (float) mHeadHeight + 1);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        this.mScrollState = scrollState;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.mFirstVisibleItem = firstVisibleItem;
        Log.e("mFirstVisibleItem:", " " + mFirstVisibleItem);
    }

    private int startY;
    private int state;
    final int IN_NORMAL = 0;//
    final int IN_PULL = 1;//
    final int IN_MAX = 2;//
    final int IN_LOADING = 3;//


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mFirstVisibleItem == 0) {
                    isTouchFirstFlag = true;
                    startY = (int) ev.getY();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                onMove(ev);
                break;
            case MotionEvent.ACTION_UP:
                if (state == IN_MAX) {
                    state = IN_LOADING;
                    setTopPadding(0);
                    //刷新
                } else if (state == IN_PULL) {
                    state = IN_NORMAL;
                    isTouchFirstFlag = false;
                    setTopPadding(-mHeadHeight);
                }
        }

        return super.onTouchEvent(ev);
    }

    private float percent = 0;

    private void onMove(MotionEvent event) {
        if (!isTouchFirstFlag) {
            return;
        }
        int tempY = (int) event.getY();
        int space = tempY - startY;
        int topPadding = space - mHeadHeight;
        Log.e("topPadding:", topPadding + " " + tempY + "  space:" + space);
        switch (state) {
            case IN_NORMAL:
                if (space > 0) {
//                    percent =
                    state = IN_PULL;
//                    reflashViewByState();
                }
                break;
            case IN_PULL:
                setTopPadding(topPadding);
                if (space > mHeadHeight
                        && mScrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    state = IN_MAX;
//                    reflashViewByState();
                }
                break;
            case IN_MAX:
                setTopPadding(topPadding);
                if (space < mHeadHeight) {
                    state = IN_PULL;
//                    reflashViewByState();
                } else if (space <= 0) {
                    state = IN_LOADING;
                    isTouchFirstFlag = false;
//                    reflashViewByState();
                }
                break;
        }
    }
}
