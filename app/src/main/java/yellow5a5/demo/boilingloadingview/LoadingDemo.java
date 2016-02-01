package yellow5a5.demo.boilingloadingview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import yellow5a5.demo.boilingloadingview.View.BoilingPanView;

public class LoadingDemo extends Activity {

    private Button buttonInit;
    private Button button1;
    private Button resetBtn;
    private BoilingPanView mBoilingPanView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_layout);
        initView();
        initListener();

    }

    private void initView() {
        buttonInit = (Button) findViewById(R.id.btn_init);
        resetBtn = (Button) findViewById(R.id.btn_reset);

        mBoilingPanView = (BoilingPanView) findViewById(R.id.loading_view);
    }

    private void initListener() {
        buttonInit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mBoilingPanView.beginFirstInAnim();
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBoilingPanView.resetAnim();
            }
        });
    }
}
