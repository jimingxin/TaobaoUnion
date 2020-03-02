package com.example.taobaounion.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.example.taobaounion.R;
import com.example.taobaounion.base.BaseFragment;
import com.example.taobaounion.model.domain.Categories;
import com.example.taobaounion.presenter.impl.HomePresenterImpl;
import com.example.taobaounion.ui.apapter.HomePageAdapter;
import com.example.taobaounion.view.IHomeCallBack;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

/**
 * @ProjectName: TaobaoUnion
 * @Package: com.example.taobaounion.ui.fragment
 * @ClassName: HomeFragment
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020-02-25 19:49
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020-02-25 19:49
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class HomeFragment extends BaseFragment implements IHomeCallBack {


    private HomePresenterImpl mHomePresenter;
    @BindView(R.id.home_indicator)
    public TabLayout mTabLayout;

    @BindView(R.id.home_pager)
    public ViewPager mViewPager;
    private HomePageAdapter mHomePageAdapter;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.base_home_fragmnet_layout,container,false);
    }

    @Override
    protected void loadData() {
        // 加载数据
        mHomePresenter.getCategories();

    }

    @Override
    protected void initPresenter() {
        // 创建Presenter
        mHomePresenter = new HomePresenterImpl();
        mHomePresenter.registerViewCallback(this);
    }

    @Override
    protected void initView(View view) {
        mTabLayout.setupWithViewPager(mViewPager);
        // 设置适配器
        mHomePageAdapter = new HomePageAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mHomePageAdapter);
    }

    @Override
    public void onCategoriesLoaded(Categories categories) {

        setupStates(State.SUCCESS);
        // 数据加载成功后进入这个页面
        if (mHomePageAdapter != null){
            mHomePageAdapter.setCategories(categories);
        }

    }

    @Override
    protected void onNetwrotErrorRetry() {
        if (mHomePresenter != null){
            mHomePresenter.getCategories();
        }
    }

    @Override
    public void onNetworkError() {
        setupStates(State.ERROR);

    }

    @Override
    public void onLoading() {
        setupStates(State.LOADING);
    }

    @Override
    public void onEmpty() {
        setupStates(State.EMPTY);
    }

    @Override
    protected void release() {
        if (mHomePresenter != null) {
            mHomePresenter.unregisterViewCallback(this);
        }
    }
}
