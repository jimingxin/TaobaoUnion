package com.example.taobaounion.presenter;

import com.example.taobaounion.base.IBasePresenter;
import com.example.taobaounion.view.IHomeCallBack;

/**
 * @ProjectName: TaobaoUnion
 * @Package: com.example.taobaounion.presenter
 * @ClassName: IHomePresenter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020-02-26 11:15
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020-02-26 11:15
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface IHomePresenter extends IBasePresenter<IHomeCallBack> {

    /**
     * 获取商品分类
     */
    void getCategories();


}
