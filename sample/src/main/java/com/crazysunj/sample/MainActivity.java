package com.crazysunj.sample;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crazysunj.sample.adapter.MyHelperAdapter;
import com.crazysunj.sample.adapter.helper.MyAdapterHelper;
import com.crazysunj.sample.entity.ItemEntity1;
import com.crazysunj.sample.entity.ItemEntity2;
import com.crazysunj.sample.entity.ItemEntity3;
import com.crazysunj.sample.entity.ItemEntity4;
import com.crazysunj.sample.util.SnackBarUtil;
import com.google.android.material.appbar.AppBarLayout;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int RANDOM_DELAY_TIME = 1000;
    private Random mRandom = new Random();
    private ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();

    private MyHelperAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private MaterialSearchView mSearchView;
    private Drawable mNavigationIcon;
    private Drawable mSearchItemIcon;
    private TextView mTitle;
    private Toolbar mToolbar;
    private AppBarLayout mAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleTranslucent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setSupportActionBar(mToolbar);
        initBar();
        initSearchView();
        initRV();
        refreshLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.release();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        mSearchItemIcon = searchItem.getIcon();
        mSearchView.setMenuItem(searchItem);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mSearchView.isSearchOpen()) {
            mSearchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mSearchView = (MaterialSearchView) findViewById(R.id.search);
        mTitle = (TextView) findViewById(R.id.title);
        mAppBar = (AppBarLayout) findViewById(R.id.appbar);
    }

    private void initBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mToolbar.setNavigationIcon(R.mipmap.ic_scan);
        mToolbar.setNavigationOnClickListener(v -> {
            refreshLoading();
        });
        mNavigationIcon = mToolbar.getNavigationIcon();
        mAppBar.addOnOffsetChangedListener((AppBarLayout appBarLayout, int verticalOffset) -> {
            int totalScrollRange = appBarLayout.getTotalScrollRange();
            float percent = Math.abs(verticalOffset * 1.0f / totalScrollRange);
            if (mNavigationIcon != null) {
                mNavigationIcon.setColorFilter((int) mArgbEvaluator.evaluate(percent, Color.WHITE, Color.BLACK), PorterDuff.Mode.SRC_IN);
            }
            if (mSearchItemIcon != null) {
                mSearchItemIcon.setColorFilter((int) mArgbEvaluator.evaluate(percent, Color.WHITE, Color.BLACK), PorterDuff.Mode.SRC_IN);
            }
            mTitle.setTextColor((int) mArgbEvaluator.evaluate(percent, Color.WHITE, Color.BLACK));
        });
    }

    private void handleTranslucent() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.setNavigationBarColor(Color.WHITE);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void initSearchView() {
        mSearchView.setVoiceSearch(false);
        mSearchView.setCursorDrawable(R.drawable.shape_cursor);
        mSearchView.setEllipsize(true);
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SnackBarUtil.show(MainActivity.this, String.format(Locale.getDefault(), "哥，别搜%s了", query));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    private void initRV() {
        mAdapter = new MyHelperAdapter(level -> {
            if (level == 0) {
                mRecyclerView.scrollToPosition(0);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void refreshLoading() {
        mAdapter.notifyLoading();
        mRecyclerView.postDelayed(() -> {
            refreshHead();
            refreshItem1();
            refreshItem2();
            refreshItem3();
            refreshItem4();
            refreshFoot();
        }, 3000);
    }

    private void refreshFoot() {
        mRecyclerView.postDelayed(() -> {
            mAdapter.registerModule(MyAdapterHelper.LEVEL_FOOT)
                    .type(MyAdapterHelper.TYPE_FOOT)
                    .layoutResId(R.layout.footer_home)
                    .register();
            mAdapter.notifyFoot();
        }, mRandom.nextInt(RANDOM_DELAY_TIME));
    }

    private void refreshHead() {
        mRecyclerView.postDelayed(() -> {
            mAdapter.registerModule(MyAdapterHelper.LEVEL_HEAD)
                    .type(MyAdapterHelper.TYPE_HEAD)
                    .layoutResId(R.layout.head_home)
                    .register();
            mAdapter.notifyHead();
        }, mRandom.nextInt(RANDOM_DELAY_TIME));
    }

    private boolean isChanged1 = true;
    private boolean isChanged2 = true;

    private void refreshItem1() {

        mAdapter.registerModule(isChanged1 ? MyAdapterHelper.LEVEL_1 : MyAdapterHelper.LEVEL_2)
                .type(ItemEntity1.TYPE_1)
                .layoutResId(R.layout.item_1)
                .headerResId(R.layout.header_common)
                .footerResId(R.layout.item_footer)
                .isFolded(true)
                .minSize(2)
                .register();

        mRecyclerView.postDelayed(() -> {
            List<ItemEntity1> list = new ArrayList<ItemEntity1>();
            list.add(new ItemEntity1("冠心病康复指导", "课程提醒", mRandom.nextInt(4) - 1));
            list.add(new ItemEntity1("血管扩张剂输液", "输液提醒", mRandom.nextInt(4) - 1));
            list.add(new ItemEntity1("服用银杏叶制剂", "用药提醒", mRandom.nextInt(4) - 1));
            list.add(new ItemEntity1("血管", "输液", mRandom.nextInt(4) - 1));
            list.add(new ItemEntity1("冠心", "课程", mRandom.nextInt(4) - 1));
            mAdapter.setChanged1(isChanged1);
            mAdapter.notifyType1(list);
            isChanged1 = !isChanged1;
        }, mRandom.nextInt(RANDOM_DELAY_TIME));
    }

    private void refreshItem2() {

        mAdapter.registerModule(isChanged2 ? MyAdapterHelper.LEVEL_2 : MyAdapterHelper.LEVEL_1)
                .type(ItemEntity2.TYPE_2)
                .layoutResId(R.layout.item_2)
                .headerResId(R.layout.header_common)
                .register();

        mRecyclerView.postDelayed(() -> {
            List<ItemEntity2> list = new ArrayList<ItemEntity2>();
            list.add(new ItemEntity2(R.mipmap.ic_bf_1, "住院康复营养A餐", "白粥+香菇肉丝+芹菜干丝", "￥ 26/餐"));
            list.add(new ItemEntity2(R.mipmap.ic_bf_2, "住院康复营养B餐", "猪肉玉米粥+菌菇汤", "￥ 24/餐"));
            mAdapter.setChanged2(isChanged2);
            mAdapter.notifyType2(list);
            isChanged2 = !isChanged2;
        }, mRandom.nextInt(RANDOM_DELAY_TIME));
    }

    private void refreshItem3() {
        mRecyclerView.postDelayed(() -> {
            mAdapter.registerModule(MyAdapterHelper.LEVEL_3)
                    .type(ItemEntity3.TYPE_3)
                    .layoutResId(R.layout.item_3)
                    .register();
            mAdapter.notifyType3(new ItemEntity3());
        }, mRandom.nextInt(RANDOM_DELAY_TIME));
    }


    private void refreshItem4() {
        mAdapter.registerModule(MyAdapterHelper.LEVEL_4)
                .type(ItemEntity4.TYPE_4)
                .layoutResId(R.layout.item_4)
                .headerResId(R.layout.header_common)
                .register();
        mRecyclerView.postDelayed(() -> {
            List<ItemEntity4> list = new ArrayList<ItemEntity4>();
            list.add(new ItemEntity4(R.mipmap.ic_doctor_1, "怎样系统预防及治疗冠心病", "刘飞", "首都医科大学宣武医院主治医师", "健康行家", "25人见过", "￥ 150/次"));
            list.add(new ItemEntity4(R.mipmap.ic_doctor_2, "冠心病患者术后的综合管理", "刘淑芬", "北京协和医院主治医师", "健康行家", "32人见过", "￥ 300/次"));
            list.add(new ItemEntity4(R.mipmap.ic_doctor_3, "心血管疾病怎么吃", "白元", "美国密歇根大学营养科学硕士", "养生行家", "28人见过", "￥ 280/次"));
            mAdapter.notifyType4(list);
        }, mRandom.nextInt(RANDOM_DELAY_TIME));
    }

    public void onClick(View view) {
        SnackBarUtil.show(this, "哥，别点了");
    }

    public void scrollTop(View view) {
        mRecyclerView.smoothScrollToPosition(0);
    }
}
