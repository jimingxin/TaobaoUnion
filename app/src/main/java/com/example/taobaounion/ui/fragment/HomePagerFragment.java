package com.example.taobaounion.ui.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taobaounion.R;
import com.example.taobaounion.base.BaseFragment;
import com.example.taobaounion.model.domain.Categories;
import com.example.taobaounion.model.domain.HomePageContent;
import com.example.taobaounion.presenter.ICategoryPagePresenter;
import com.example.taobaounion.presenter.impl.CategoryPagePresneterImpl;
import com.example.taobaounion.ui.apapter.HomePageContainerAdapter;
import com.example.taobaounion.utils.Constrants;
import com.example.taobaounion.utils.LogUtils;
import com.example.taobaounion.view.ICategoryPagerCallBack;

import java.util.List;

import butterknife.BindView;

/**
 * @ProjectName: TaobaoUnion
 * @Package: com.example.taobaounion.ui.fragment
 * @ClassName: HomePagerFragment
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020-02-26 13:10
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020-02-26 13:10
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class HomePagerFragment extends BaseFragment implements ICategoryPagerCallBack {

    private ICategoryPagePresenter mCategoryPagePresenter;
    private int mMaterialId;
    private HomePageContainerAdapter mHomePageContainerAdapter;

    public static HomePagerFragment newInstance(Categories.DataBean dataBean){
        HomePagerFragment homePagerFragment = new HomePagerFragment();

        // 设置需要的数据
        Bundle bundle = new Bundle();
        bundle.putString(Constrants.KEY_HOME_PAGER_TITLE,dataBean.getTitle());
        bundle.putInt(Constrants.KEY_HOME_PAGER_MATERITAL_ID,dataBean.getId());
        homePagerFragment.setArguments(bundle);

        return homePagerFragment;
    }

    @BindView(R.id.home_page_content_list)
    public RecyclerView mContentList;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    protected void initView(View view) {
        mContentList.setLayoutManager(new LinearLayoutManager(getContext()));
        mHomePageContainerAdapter = new HomePageContainerAdapter();
        mContentList.setAdapter(mHomePageContainerAdapter);
        // 设置recycleview间隔
        mContentList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = 10;
                outRect.bottom = 10;
            }
        });

    }

    @Override
    protected void initPresenter() {
        mCategoryPagePresenter = CategoryPagePresneterImpl.getInstance();
        mCategoryPagePresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        Bundle argments = getArguments();
        String title = argments.getString(Constrants.KEY_HOME_PAGER_TITLE);
        mMaterialId = argments.getInt(Constrants.KEY_HOME_PAGER_MATERITAL_ID);
        LogUtils.d(this,"title--->"+title);
        LogUtils.d(this,"materialID--->"+ mMaterialId);
        //TODO: 加载数据
        if (mCategoryPagePresenter != null) {
            mCategoryPagePresenter.getContentByCategoryId(mMaterialId);
        }
    }


    @Override
    protected void release() {
        if (mCategoryPagePresenter != null) {
            mCategoryPagePresenter.unregisterViewCallback(this);
        }
    }

    @Override
    public int getCategoryId() {
        return mMaterialId;
    }

    @Override
    public void onContentLoaded(List<HomePageContent.DataBean> contents,int categoryId) {
        // 数据加载成功了
        mHomePageContainerAdapter.setData(contents);
        setupStates(State.SUCCESS);
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
    public void onLoadMoreError(int categoryId) {

    }

    @Override
    public void onLoadMoreEmpty(int categoryId) {

    }

    @Override
    public void onLoadMoreLoaded(List<HomePageContent.DataBean> contents,int categoryId) {

    }

    @Override
    public void onLooperListLoaded(List<HomePageContent.DataBean> contents,int categoryId) {

    }
}
