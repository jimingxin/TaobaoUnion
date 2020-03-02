package com.example.taobaounion.model;

import com.example.taobaounion.model.domain.Categories;
import com.example.taobaounion.model.domain.HomePageContent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * @ProjectName: TaobaoUnion
 * @Package: com.example.taobaounion.model
 * @ClassName: Api
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020-02-26 11:42
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020-02-26 11:42
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface Api {

    @GET("discovery/categories")
    Call<Categories> getCategories();


    @GET
    Call<HomePageContent> getHomePageContent(@Url String url);
}
