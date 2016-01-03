package yellow5a5.demo.boilingloadingview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import yellow5a5.demo.boilingloadingview.View.BoilingPanView;

public class MainActivity extends Activity {

    private Button button1;
    private Button button2;
    private BoilingPanView mBoilingPanView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBoilingPanView = (BoilingPanView) findViewById(R.id.loading_view);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBoilingPanView.beginAnim();
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
