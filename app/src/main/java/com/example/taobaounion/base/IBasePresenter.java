package com.example.taobaounion.base;

/**
 * @ProjectName: TaobaoUnion
 * @Package: com.example.taobaounion.base
 * @ClassName: IBasePresenter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020-02-27 15:47
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020-02-27 15:47
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface IBasePresenter<T> {

    /**
     * 注册UI通知接口
     * @param callback
     */
    void registerViewCallback(T callback);

    /**
     * 取消注册通知接口
     * @param callBack
     */
    void unregisterViewCallback(T callBack);
}
