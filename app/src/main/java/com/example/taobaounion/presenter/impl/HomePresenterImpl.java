package com.example.taobaounion.presenter.impl;

import com.example.taobaounion.model.Api;
import com.example.taobaounion.model.domain.Categories;
import com.example.taobaounion.presenter.IHomePresenter;
import com.example.taobaounion.utils.LogUtils;
import com.example.taobaounion.utils.RetorfitManager;
import com.example.taobaounion.view.IHomeCallBack;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @ProjectName: TaobaoUnion
 * @Package: com.example.taobaounion.presenter.impl
 * @ClassName: HomePresenterImpl
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020-02-26 11:28
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020-02-26 11:28
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class HomePresenterImpl implements IHomePresenter {

    private IHomeCallBack mCallBack;



    @Override
    public void getCategories() {

        if (mCallBack != null) {
            mCallBack.onLoading();
        }
        // 加载分类内容
        Retrofit retrofit = RetorfitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<Categories> task = api.getCategories();
        task.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                //数据加载成功
                int code = response.code();
                LogUtils.d(this,"分类数据code" + code);
                if (code == HttpURLConnection.HTTP_OK){

                    if (mCallBack != null) {

                        Categories categories = response.body();
                        LogUtils.d(this,categories.toString());

                        if (categories == null || categories.getData().size() == 0) {
                            mCallBack.onEmpty();
                        }else {

                            mCallBack.onCategoriesLoaded(categories);
                        }

                    }


               }else {
                   // 请求失败
                    LogUtils.d(this,"分类数据请求失败code" + code);
                    if (mCallBack != null) {
                        mCallBack.onNetworkError();
                    }
               }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                // 加载失败的接口
                // 请求失败
                LogUtils.e(this,"分类数据请求失败:"+t);
                if (mCallBack != null) {
                    mCallBack.onNetworkError();
                }
            }
        });
    }

    @Override
    public void registerViewCallback(IHomeCallBack callback) {
        mCallBack = callback;
    }

    @Override
    public void unregisterViewCallback(IHomeCallBack callback) {
        mCallBack = null;
    }
}
