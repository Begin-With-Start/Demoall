package demo.minifly.com.fuction_demo.canvas_test.canvas_new;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.List;

import demo.minifly.com.R;

public class CanvasAllDealViewActivity extends AppCompatActivity {

    private CanvasPieView mCanvasAllPieviewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_all_deal_view);
        initView();
    }

    private void initView() {
        mCanvasAllPieviewId = (CanvasPieView) findViewById(R.id.canvas_all_pieview_id);

        List<CanvasPieView.PipPoint> list = new LinkedList<>();
        CanvasPieView.PipPoint pipPoint = null;
        pipPoint = new CanvasPieView.PipPoint(100,"恩");
        list.add(pipPoint);
        pipPoint = new CanvasPieView.PipPoint(200,"恩");
        list.add(pipPoint);
        pipPoint = new CanvasPieView.PipPoint(300,"恩");
        list.add(pipPoint);
        pipPoint = new CanvasPieView.PipPoint(400,"恩");
        list.add(pipPoint);

        mCanvasAllPieviewId.setData(list);
    }
}
