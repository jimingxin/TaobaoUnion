package com.example.taobaounion.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taobaounion.R;
import com.example.taobaounion.utils.LogUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @ProjectName: TaobaoUnion
 * @Package: com.example.taobaounion.base
 * @ClassName: BaseFragment
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020-02-25 19:47
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020-02-25 19:47
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public abstract class BaseFragment extends Fragment {

    private View mLoadErrorView;
    private View mLoadEmptyView;

    public enum State{
        NONE,LOADING,SUCCESS,ERROR,EMPTY
    }

    private State currentState = State.NONE;
    private Unbinder mBind;
    private FrameLayout mBaseContainer;
    private View mSuccessView;
    private View mLoadingView;

    @OnClick(R.id.network_error_tips)
    public void ertry(){
        // 点击了重新加载内容
        LogUtils.d(this,"点击了网络重新加载");

        onNetwrotErrorRetry();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = loadRootView(inflater,container);
        mBaseContainer = rootView.findViewById(R.id.base_container);

        // 加载各种状态的View
        loadStateView(inflater,container);
        mBind = ButterKnife.bind(this, rootView);
        initView(rootView);
        initPresenter();
        loadData();

        return rootView;
    }

    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
       return inflater.inflate(R.layout.base_fragmnet_layout, container, false);
    }

    private void loadStateView(LayoutInflater inflater, ViewGroup container) {
        // 成功的View
        mSuccessView = loadSuccessView(inflater, container);
        mBaseContainer.addView(mSuccessView);

        // 加载中的View
        mLoadingView = loadLoadingView(inflater, container);
        mBaseContainer.addView(mLoadingView);

        // 错误页面
        mLoadErrorView = loadErrorView(inflater, container);
        mBaseContainer.addView(mLoadErrorView);

        mLoadEmptyView = loadEmptyView(inflater, container);
        mBaseContainer.addView(mLoadEmptyView);

        // 设置初始的状态
        setupStates(State.NONE);
    }



    /**
     * 子类通过这个状态来进行切换
     * @param state
     */
    public void setupStates(State state){

        this.currentState = state;
        mSuccessView.setVisibility(currentState == State.SUCCESS ? View.VISIBLE:View.GONE);

        mLoadingView.setVisibility(currentState == State.LOADING ? View.VISIBLE:View.GONE);

        mLoadErrorView.setVisibility(currentState == State.ERROR ? View.VISIBLE:View.GONE);

        mLoadEmptyView.setVisibility(currentState == State.EMPTY ? View.VISIBLE:View.GONE);

    }

    protected View loadEmptyView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.framgment_empty,container,false);
    }

    protected View loadErrorView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.framgment_error,container,false);
    }

    /**
     * 加载各种状态的View
     * @param inflater
     * @param container
     * @return
     */
    protected View loadLoadingView(LayoutInflater inflater, ViewGroup container) {
       return inflater.inflate(R.layout.framgment_loading,container,false);
    }



    protected View loadSuccessView(LayoutInflater inflater, ViewGroup container) {

        int resId = getRootViewResId();

        return inflater.inflate(resId, container, false);
    }

    protected void initView(View view) {
    }

    protected void initPresenter() {

    }

    protected void loadData() {
        // 加载数据
    }

    protected abstract int getRootViewResId();

    /**
     * 如果子类网络错误，覆盖此方法进行
     */
    protected void onNetwrotErrorRetry() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBind != null) {
            mBind.unbind();
        }
        release();
    }

    protected void release() {
    }
}
