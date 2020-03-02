package com.example.taobaounion.utils;

/**
 * @ProjectName: TaobaoUnion
 * @Package: com.example.taobaounion.utils
 * @ClassName: TextUtils
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020-02-27 16:48
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020-02-27 16:48
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class UrlUtils {

    public static String createHomePgerUrl(int materialId, int page) {
        return "discovery/" + materialId + "/" + page;
    }

    public static String getCoverPath(String pict_url) {
        return "https:" + pict_url;
    }
}
