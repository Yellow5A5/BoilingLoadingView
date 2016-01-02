package yellow5a5.demo.boilingloadingview.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import yellow5a5.demo.boilingloadingview.R;

/**
 * Created by Weiwu on 16/1/2.
 */
public class WaterView extends View {

    private int mSpeed;
    private Drawable mDrawable;


    public WaterView(Context context) {
        this(context, null);
    }

    public WaterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.WaterView);
        mDrawable = array.getDrawable(R.styleable.WaterView_WaterView_src);
        mSpeed = array.getInteger(R.styleable.WaterView_WaterView_speed, 200);
        initData();
    }

    private void initData() {
//        mDrawable.getIntrinsicWidth()

    }

    private void setDrawable(Drawable drawable){
        mDrawable = drawable;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDrawable == null)
            return;

    }
}
