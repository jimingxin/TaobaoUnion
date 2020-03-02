package com.example.taobaounion.base;

/**
 * @ProjectName: TaobaoUnion
 * @Package: com.example.taobaounion.base
 * @ClassName: IBaseCallBack
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020-02-27 15:41
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020-02-27 15:41
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface IBaseCallBack {


    /**
     * 网络请求失败回调
     */
    void onNetworkError();

    /**
     * 网络请求中
     */
    void onLoading();

    /**
     * 请求数据为空时回调
     */
    void onEmpty();

}
