package yellow5a5.demo.boilingloadingview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import yellow5a5.demo.boilingloadingview.View.BoilingPanView;

public class LoadingDemo extends Activity {

    private Button buttonInit;
    private Button button1;
    private Button button2;
    private BoilingPanView mBoilingPanView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_layout);
        initView();
        initListener();

    }

    private void initView() {
        buttonInit = (Button) findViewById(R.id.init_button);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        mBoilingPanView = (BoilingPanView) findViewById(R.id.loading_view);
        mBoilingPanView.setBoilingAnimListener(new BoilingPanView.BoilingAnimListener() {
            @Override
            public void onFirstAnimEnd() {
                mBoilingPanView.beginBoilingAnim();
            }
        });
    }

    private void initListener() {
        buttonInit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mBoilingPanView.beginFirstInAnim();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBoilingPanView.beginBoilingAnim();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBoilingPanView.resetAnim();
            }
        });
    }
}
