package com.zc.commonutilslib;

import android.content.Context;

/**
 * 此工具类用来重启APP，只是单纯的重启，不做任何处理。
 * Created by 13itch on 2016/8/5.
 */
public class RestartAPPTool {
    static String TAG = "RestartAPPTool";
    /**
     * 重启整个APP
     * @param context
     * @param Delayed 延迟多少毫秒
     */
    public static void restartAPP(Context context, long Delayed, String crash){
        /**开启一个新的服务，用来重启本APP*/
        /*Intent intent1=new Intent(context,killSelfService.class);
        intent1.putExtra("CrashLog",crash);
        intent1.putExtra("PackageName",context.getPackageName());
        intent1.putExtra("Delayed",Delayed);
        context.startService(intent1);*/

        /**杀死整个进程**/
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    /***重启整个APP*/
    public static void restartAPP(Context context, String crash){
        MLog.i(TAG, "restartAPP: "+crash);
        restartAPP(context,1000,crash);
    }
}
