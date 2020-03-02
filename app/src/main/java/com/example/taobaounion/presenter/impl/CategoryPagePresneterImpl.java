package com.example.taobaounion.presenter.impl;

import com.example.taobaounion.model.Api;
import com.example.taobaounion.model.domain.HomePageContent;
import com.example.taobaounion.presenter.ICategoryPagePresenter;
import com.example.taobaounion.utils.LogUtils;
import com.example.taobaounion.utils.RetorfitManager;
import com.example.taobaounion.utils.UrlUtils;
import com.example.taobaounion.view.ICategoryPagerCallBack;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @ProjectName: TaobaoUnion
 * @Package: com.example.taobaounion.presenter.impl
 * @ClassName: CategoryPagePresneterImpl
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020-02-27 15:52
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020-02-27 15:52
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class CategoryPagePresneterImpl implements ICategoryPagePresenter {

    private Map<Integer, Integer> pagesInfo = new HashMap<>();

    private ArrayList<ICategoryPagerCallBack> mCallBacks = new ArrayList<>();

    public static final int DEFAULT_PAGE = 1;

    private CategoryPagePresneterImpl() {

    }

    private static ICategoryPagePresenter sInstance = null;

    public static ICategoryPagePresenter getInstance() {
        if (sInstance == null) {
            sInstance = new CategoryPagePresneterImpl();

        }
        return sInstance;
    }


    @Override
    public void getContentByCategoryId(int categoryId) {
        // 网络请求一开始状态
        for (ICategoryPagerCallBack callback : mCallBacks) {
            if (callback.getCategoryId() == categoryId) {
                callback.onLoading();
            }

        }
        //加载内容
        // 加载分类内容
        Retrofit retrofit = RetorfitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);

        Integer targetPage = pagesInfo.get(categoryId);
        if (targetPage == null) {
            targetPage = DEFAULT_PAGE;
            pagesInfo.put(categoryId, targetPage);
        }
        String homePgerUrl = UrlUtils.createHomePgerUrl(categoryId, targetPage);
        LogUtils.d(this, "home page url -->" + homePgerUrl);
        Call<HomePageContent> task = api.getHomePageContent(homePgerUrl);
        task.enqueue(new Callback<HomePageContent>() {
            @Override
            public void onResponse(Call<HomePageContent> call, Response<HomePageContent> response) {
                int code = response.code();
                LogUtils.d(this, "code---->" + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    HomePageContent homePageContent = response.body();
                    LogUtils.d(this, "homePageContent--->" + homePageContent.toString());
                    // 更新UI
                    handleHomePageContentResult(homePageContent, categoryId);

                } else {

                    //TODO:
                    handleNetworkError(categoryId);

                }
            }

            @Override
            public void onFailure(Call<HomePageContent> call, Throwable t) {
                LogUtils.d(this, "onFailure---->" + t);
                handleNetworkError(categoryId);
            }
        });
    }

    private void handleNetworkError(int categoryId) {

        for (ICategoryPagerCallBack callBack : mCallBacks) {
            if (callBack.getCategoryId() == categoryId) {
                callBack.onNetworkError();
            }
        }
    }

    private void handleHomePageContentResult(HomePageContent homePageContent, int categoryId) {


        for (ICategoryPagerCallBack callback : mCallBacks) {

            if (callback.getCategoryId() == categoryId) {

                if (homePageContent == null || homePageContent.getData().size() == 0) {


                    callback.onEmpty();
                }
            } else {
                callback.onContentLoaded(homePageContent.getData(), categoryId);


            }


        }
    }

    @Override
    public void loadMore(int categoryId) {

    }

    @Override
    public void reLoad(int categoryId) {

    }


    @Override
    public void registerViewCallback(ICategoryPagerCallBack callback) {

        if (!mCallBacks.contains(callback)) {
            mCallBacks.add(callback);
        }
    }

    @Override
    public void unregisterViewCallback(ICategoryPagerCallBack callBack) {
        if (mCallBacks.contains(callBack)) {
            mCallBacks.remove(callBack);
        }
    }
}
