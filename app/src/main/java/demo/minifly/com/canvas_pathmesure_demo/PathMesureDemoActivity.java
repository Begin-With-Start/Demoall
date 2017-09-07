package demo.minifly.com.canvas_pathmesure_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import demo.minifly.com.R;

public class PathMesureDemoActivity extends AppCompatActivity {

    private LightPathCanvasView mPathmesureViewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_mesure_demo);
        initView();
    }

    private void initView() {
        mPathmesureViewId = (LightPathCanvasView) findViewById(R.id.pathmesure_view_id);
    }
}
