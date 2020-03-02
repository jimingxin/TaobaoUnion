package com.example.taobaounion.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.taobaounion.utils.Constrants.BASE_URL;

/**
 * @ProjectName: TaobaoUnion
 * @Package: com.example.taobaounion.utils
 * @ClassName: RetorfitManager
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020-02-26 11:22
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020-02-26 11:22
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class RetorfitManager {
    private static final RetorfitManager ourInstance = new RetorfitManager();
    private final Retrofit mRetrofit;

    public static RetorfitManager getInstance() {
        return ourInstance;
    }

    private RetorfitManager() {
        // 创建retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit(){
        return mRetrofit;
    }
}
