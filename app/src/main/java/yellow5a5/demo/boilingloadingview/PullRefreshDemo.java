package yellow5a5.demo.boilingloadingview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import yellow5a5.demo.boilingloadingview.View.BoilingListView;

/**
 * Created by Weiwu on 16/1/10.
 */
public class PullRefreshDemo extends Activity {


    private BoilingListView mBoilingListView;

    private ArrayList mArrayList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pullrefresh_layout);

        mBoilingListView = (BoilingListView) findViewById(R.id.boil_list);

        mBoilingListView.setAdapter(new ArrayAdapter(this, R.layout.test_item, getData()));
    }

    private ArrayList<String> getData() {
        mArrayList.add("测试数据1");
        mArrayList.add("测试数据2");
        mArrayList.add("测试数据3");
        mArrayList.add("测试数据4");
        mArrayList.add("测试数据5");
        mArrayList.add("测试数据6");
        mArrayList.add("测试数据7");
        mArrayList.add("测试数据8");
        mArrayList.add("测试数据9");
        mArrayList.add("测试数据1");
        mArrayList.add("测试数据2");
        mArrayList.add("测试数据3");
        mArrayList.add("测试数据4");
        mArrayList.add("测试数据5");
        mArrayList.add("测试数据6");
        mArrayList.add("测试数据7");
        mArrayList.add("测试数据8");
        mArrayList.add("测试数据9");
        mArrayList.add("测试数据1");
        mArrayList.add("测试数据2");
        mArrayList.add("测试数据3");
        mArrayList.add("测试数据4");
        mArrayList.add("测试数据5");
        mArrayList.add("测试数据6");
        mArrayList.add("测试数据7");
        mArrayList.add("测试数据8");
        mArrayList.add("测试数据9");
        return mArrayList;
    }
}
