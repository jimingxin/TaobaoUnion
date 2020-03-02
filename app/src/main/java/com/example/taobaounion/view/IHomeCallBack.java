package com.example.taobaounion.view;

import com.example.taobaounion.base.IBaseCallBack;
import com.example.taobaounion.model.domain.Categories;

/**
 * @ProjectName: TaobaoUnion
 * @Package: com.example.taobaounion.view
 * @ClassName: IHomeCallBack
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020-02-26 11:16
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020-02-26 11:16
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface IHomeCallBack extends IBaseCallBack {

    /**
     * 获取商品分类
     * @param categories
     */
    void onCategoriesLoaded(Categories categories);

}
