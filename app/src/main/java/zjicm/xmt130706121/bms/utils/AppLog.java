package zjicm.xmt130706121.bms.utils;

import android.util.Log;

/**
 * Log的管理类
 *
 * @author 王鑫
 *         Created by 鑫 on 2015/9/21.
 */
public class AppLog {

    public static void v(String className, String methodName, String msg) {
        Log.v(className ,"#" + methodName + ": " + msg);
    }

    public static void d(String className, String methodName, String msg) {
        Log.d(className ,"#" + methodName + ": " + msg);
    }

    public static void i(String className, String methodName, String msg) {
        Log.i(className ,"#" + methodName + ": " + msg);
    }

    public static void w(String className, String methodName, String msg) {
        Log.w(className ,"#" + methodName + ": " + msg);
    }

    public static void e(String className, String methodName, String msg) {
        Log.e(className ,"#" + methodName + ": " + msg);
    }
}
