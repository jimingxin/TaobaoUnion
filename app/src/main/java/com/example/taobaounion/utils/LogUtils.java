package com.example.taobaounion.utils;

import android.util.Log;

/**
 * @ProjectName: TaobaoUnion
 * @Package: com.example.taobaounion.utils
 * @ClassName: LogUtils
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020-02-25 19:30
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020-02-25 19:30
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class LogUtils {

    private static int currentLev = 4;
    private static final int DEBUG_LEV = 4;
    private static final int INFO_LEV = 3;
    private static final int WARNING_LEV = 2;
    private static final int ERROR_LEV = 1;


    public static void d(Object object, String log) {
        if (currentLev >= DEBUG_LEV) {
            Log.d(object.getClass().getSimpleName(), log);
        }
    }

    public static void i(Object object, String log) {
        if (currentLev >= INFO_LEV) {
            Log.i(object.getClass().getSimpleName(), log);
        }
    }

    public static void w(Object object, String log) {
        if (currentLev >= WARNING_LEV) {
            Log.w(object.getClass().getSimpleName(), log);
        }
    }

    public static void e(Object object, String log) {
        if (currentLev >= ERROR_LEV) {
            Log.e(object.getClass().getSimpleName(), log);
        }
    }

}
