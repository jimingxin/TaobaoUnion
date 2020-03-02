package com.example.taobaounion.presenter;

import com.example.taobaounion.base.IBasePresenter;
import com.example.taobaounion.view.ICategoryPagerCallBack;

/**
 * @ProjectName: TaobaoUnion
 * @Package: com.example.taobaounion.presenter
 * @ClassName: ICategoryPagePresenter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020-02-27 15:29
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020-02-27 15:29
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface ICategoryPagePresenter extends IBasePresenter<ICategoryPagerCallBack> {

    /**
     * 根据分类id获取分类内容
     * @param categoryId
     */
    void getContentByCategoryId(int categoryId);

    /**
     * 加载更过
     * @param categoryId
     */
    void loadMore(int categoryId);

    /**
     * 重新加载
     * @param categoryId
     */
    void reLoad(int categoryId);

}
