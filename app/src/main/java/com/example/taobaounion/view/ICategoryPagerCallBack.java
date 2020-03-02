package com.example.taobaounion.view;

import com.example.taobaounion.base.IBaseCallBack;
import com.example.taobaounion.model.domain.HomePageContent;

import java.util.List;

/**
 * @ProjectName: TaobaoUnion
 * @Package: com.example.taobaounion.view
 * @ClassName: ICategoryPagerCallBack
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020-02-27 15:33
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020-02-27 15:33
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface ICategoryPagerCallBack extends IBaseCallBack {

    /**
     * 获取当前的id
     * @return
     */
    int getCategoryId();

    /**
     * 首次数据加载成功后回调
     * @param contents
     * @param categoryId
     */
    void onContentLoaded(List<HomePageContent.DataBean> contents,int categoryId);


    /**
     * 加载更多网络错误
     * @param categoryId
     */
    void onLoadMoreError(int categoryId);

    /**
     * 加载更多数据为空
     * @param categoryId
     */
    void onLoadMoreEmpty(int categoryId);

    /**
     * 加载了更多内容
     * @param contents
     */
    void onLoadMoreLoaded(List<HomePageContent.DataBean> contents,int categoryId);

    /**
     * 加载到轮播图
     * @param contents
     */
    void onLooperListLoaded(List<HomePageContent.DataBean> contents,int categoryId);

}
