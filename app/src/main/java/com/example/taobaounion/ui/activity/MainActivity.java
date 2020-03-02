package com.example.taobaounion.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.taobaounion.R;
import com.example.taobaounion.base.BaseFragment;
import com.example.taobaounion.ui.fragment.HomeFragment;
import com.example.taobaounion.ui.fragment.RedPacketFragment;
import com.example.taobaounion.ui.fragment.SearchFragment;
import com.example.taobaounion.ui.fragment.SelectedFragment;
import com.example.taobaounion.utils.LogUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation_bar)
    public BottomNavigationView mbottomNavigationView;
    private HomeFragment mHomeFragment;
    private RedPacketFragment mRedPacketFragment;
    private SelectedFragment mSelectedFragment;
    private SearchFragment mSearchFragment;
    private FragmentManager mFm;
    private Unbinder mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 黄油刀设置
        mBind = ButterKnife.bind(this);
        initFragments();
        initListener();
    }

    private void initFragments() {
        mHomeFragment = new HomeFragment();
        mRedPacketFragment = new RedPacketFragment();
        mSelectedFragment = new SelectedFragment();
        mSearchFragment = new SearchFragment();
        mFm = getSupportFragmentManager();
        switchFragment(mHomeFragment);
    }



    private void initListener() {
        mbottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.home) {
                    LogUtils.d(MainActivity.class,"切换到首页");
                    switchFragment(mHomeFragment);

                }else if (item.getItemId() == R.id.selected){
                    LogUtils.d(MainActivity.class,"切换到精选");
                    switchFragment(mSelectedFragment);
                }else if (item.getItemId() == R.id.red_packet){
                    LogUtils.d(MainActivity.class,"切换到特惠");
                    switchFragment(mRedPacketFragment);
                }else if (item.getItemId() == R.id.search){
                    LogUtils.d(MainActivity.class,"切换到搜索");
                    switchFragment(mSearchFragment);
                }

                return true;
            }
        });
    }

    private void switchFragment(BaseFragment target) {
        FragmentTransaction transaction = mFm.beginTransaction();
        transaction.replace(R.id.main_page_content,target);
        transaction.commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
    }
}
