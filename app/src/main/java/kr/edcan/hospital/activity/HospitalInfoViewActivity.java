package kr.edcan.hospital.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import kr.edcan.hospital.R;
import kr.edcan.hospital.utils.SlidingTabLayout;
import kr.edcan.hospital.utils.ViewPager2Adapter;

public class HospitalInfoViewActivity extends AppCompatActivity {

    ViewPager viewPager;
    ViewPager2Adapter adapter;
    SlidingTabLayout tabs;
    String Titles[] = {"정보", "후기"};
    int tab_count = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_info_view);
        setActionbar();
        setDefault();
    }

    private void setDefault() {
        adapter = new ViewPager2Adapter(getSupportFragmentManager(), Titles, tab_count);
        viewPager = (ViewPager) findViewById(R.id.view_viewpager);
        viewPager.setAdapter(adapter);
        tabs = (SlidingTabLayout) findViewById(R.id.sliding_layout);
        tabs.setDistributeEvenly(true);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabs_color);
            }
        });
        tabs.setViewPager(viewPager);
    }

    private void setActionbar() {
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle("병원 정보");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
