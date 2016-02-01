package yellow5a5.demo.boilingloadingview;

/**
 * Created by Weiwu on 16/2/1.
 */

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import yellow5a5.demo.boilingloadingview.View.BoilingPanView;

/**
 * Created by Weiwu on 15/12/26.
 */
public class BoilingDialog extends Dialog {

    protected View mContentView;

    public BoilingDialog(Context context) {
        super(context);
    }

    public BoilingDialog(Context context, int theme) {
        super(context, theme);
    }


    @Override
    public void show() {
        this.setContentView(mContentView);
        super.show();
    }

    public static class Builder {
        protected Context context;
        protected BoilingDialog dialog;
        protected View dialogLayout;
        protected String titleText;
        protected String positiveText;
        protected int gifResId;

        public Builder(Context context){
            this.context = context;
        }



        public void setupViews(){
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialogLayout = LayoutInflater.from(context).inflate(R.layout.dialog_layout, (ViewGroup)null);

            BoilingPanView boilingView = (BoilingPanView)dialogLayout.findViewById(R.id.boiling_show);
            boilingView.beginFirstInAnim();
            dialog.mContentView = dialogLayout;
        }

        public BoilingDialog build() {
            dialog = new BoilingDialog(this.context,R.style.Translucent_NoTitle);
            setupViews();
            return dialog;
        }

    }

}
