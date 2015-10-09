package kr.edcan.hospital.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.afollestad.materialdialogs.MaterialDialog;

import kr.edcan.hospital.R;
import kr.edcan.hospital.utils.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ViewPager pager;
    ViewPagerAdapter adapter;
    int tab_count = 2, tab_on[], tab_off[];
    RelativeLayout m1, m2, actionbar_1, actionbar_2;
    ImageView tab_main, tab_search, tabs[], settings, search;
    LinearLayout tab_bar1, tab_bar2, tab_bars[];
    MaterialDialog progress;
    String asdf[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDefault();
        setTab(0);
    }

    public void loadData(){
        progress = new MaterialDialog.Builder(getApplicationContext())
                .progress(true, 0)
                .title("데이터를 로딩중입니다")
                .content("잠시만 기다려주세요")
                .show();
        // TODO: Load Data from Server
        progress.dismiss();
    }
    public void setDefault() {
        asdf = new String[]{"asdf"};
        actionbar_1 = (RelativeLayout) findViewById(R.id.main_actionbar_1);
        actionbar_2 = (RelativeLayout) findViewById(R.id.main_actionbar_2);
        settings = (ImageView) findViewById(R.id.main_settings);
        settings.setOnClickListener(this);
        search = (ImageView) findViewById(R.id.main_search);
        search.setOnClickListener(this);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), asdf, tab_count);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
        tab_bar1 = (LinearLayout) findViewById(R.id.main_tab_under_bar_1);
        tab_bar2 = (LinearLayout) findViewById(R.id.main_tab_under_bar_2);
        tab_bars = new LinearLayout[]{tab_bar1, tab_bar2};
        m1 = (RelativeLayout) findViewById(R.id.main_tab_main);
        m2 = (RelativeLayout) findViewById(R.id.main_tab_search);
        m1.setOnClickListener(this);
        m2.setOnClickListener(this);
        tab_main = (ImageView) findViewById(R.id.main_tab_main_image);
        tab_search = (ImageView) findViewById(R.id.main_tab_search_image);
        tabs = new ImageView[]{tab_main, tab_search};
        tab_on = new int[]{R.drawable.ic_tab_home_on, R.drawable.ic_tab_hospital_on};
        tab_off = new int[]{R.drawable.ic_tab_home_off, R.drawable.ic_tab_hospital_off};
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setTab(int position) {
        if (position == 0) {
            actionbar_1.setVisibility(View.VISIBLE);
            actionbar_2.setVisibility(View.GONE);
        } else {
            actionbar_1.setVisibility(View.GONE);
            actionbar_2.setVisibility(View.VISIBLE);
        }
        for (int i = 0; i < tab_count; i++) {
            if (i == position) {
                tabs[i].setImageResource(tab_on[i]);
                tab_bars[i].setVisibility(View.VISIBLE);
            } else {
                tabs[i].setImageResource(tab_off[i]);
                tab_bars[i].setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_tab_main:
                pager.setCurrentItem(0, true);
                break;
            case R.id.main_tab_search:
                pager.setCurrentItem(1, true);
                break;
            case R.id.main_settings:
                startActivity(new Intent(getApplicationContext(), Settings.class));
                break;
            case R.id.main_search:
                break;
        }
    }

    public void onResume() {
        super.onResume();
        setDefault();
        setTab(0);
    }
}
