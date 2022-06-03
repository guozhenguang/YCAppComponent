package com.yc.apprestartlib;

import android.content.Context;

/**
 * <pre>
 *     @author yangchong
 *     GitHub : https://github.com/yangchong211/YCCommonLib
 *     email : yangchong211@163.com
 *     time  : 2018/11/9
 *     desc  : 重启APP帮助类
 *     revise:
 * </pre>
 */
public class RestartAppHelper {

    public static void restartApp(Context context, String type) {
        IRestartApp iRestartApp = RestartFactory.create(type);
        iRestartApp.restartApp(context);
    }

}
