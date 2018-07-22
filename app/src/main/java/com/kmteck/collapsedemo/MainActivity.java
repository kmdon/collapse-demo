package com.kmteck.collapsedemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kmteck.collapsedemo.adapter.ItemAdapter;
import com.kmteck.collapsedemo.viewhelper.FooterLayoutBehaviour;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        RecyclerView rvItems = findViewById(R.id.rvItems);
        rvItems.setLayoutManager(new GridLayoutManager(this, 2));
        rvItems.setAdapter(new ItemAdapter());

        setUpTab();
    }

    private void setUpTab() {
        final TabLayout tabLayout = findViewById(R.id.filterTab);
        TabLayout.Tab filterTab = tabLayout.newTab();
        filterTab.setCustomView(getTabView("Filter", android.R.drawable.btn_dialog));
        TabLayout.Tab sortTag = tabLayout.newTab();
        sortTag.setCustomView(getTabView("Sort", android.R.drawable.btn_star));
        tabLayout.addTab(filterTab);
        tabLayout.addTab(sortTag);
        final LinearLayout footer = findViewById(R.id.footerLayout);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) footer.getLayoutParams();
        final FooterLayoutBehaviour behavior = new FooterLayoutBehaviour();
        layoutParams.setBehavior(behavior);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                behavior.setHeight(footer.getHeight());
            }
        }, 500);
    }

    public View getTabView(String title, int iconSource) {
        View v = LayoutInflater.from(this).inflate(R.layout.custom_tab_view, null);
        TextView tv = (TextView) v.findViewById(R.id.tvTitle);
        ImageView iv = v.findViewById(R.id.ivIcon);
        tv.setText(title);
        iv.setImageResource(iconSource);
        return v;
    }
}
