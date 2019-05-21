package demo.minifly.com.fuction_demo.transition_element;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import demo.minifly.com.R;

public class FrameFragmentAcitity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    private RelativeLayout mContainer;

    static String VIEW_NAME_HEADER_IMAGE = "image";
    static String VIEW_NAME_HEADER_TITLE = "txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_fragment_acitity);
        initView();
    }

    private void initView() {
        mContainer = (RelativeLayout) findViewById(R.id.container);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        Fragment to = new ItemFragment();
        ft.replace(R.id.container, to);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onListFragmentInteraction(MyItemRecyclerViewAdapter.ViewHolder holder) {

        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                new Pair<View, String>(holder.imageView,
                        VIEW_NAME_HEADER_IMAGE),
                new Pair<View, String>(holder.imageView,
                        VIEW_NAME_HEADER_TITLE)
        );
        Intent intent = new Intent();

        intent.setClass(this,TransitionElement2Activity.class);

        ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
    }
}
