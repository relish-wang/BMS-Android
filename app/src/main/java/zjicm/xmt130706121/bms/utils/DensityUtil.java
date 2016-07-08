package zjicm.xmt130706121.bms.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import zjicm.xmt130706121.bms.App;


/**
 * dp与px互相转换的工具类
 *
 * @author 王鑫
 */
public class DensityUtil {

    private static int screenWidth;
    private static int screenHeight;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = App.CONTEXT.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取设备的屏幕宽度
     *
     * @param activity Activity
     * @return px数据
     */
    public static int getScreenWidth(Activity activity) {
        if (screenWidth <= 0) {
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            screenWidth = dm.widthPixels;
        }
        return screenWidth;
    }

    /**
     * 获取设备的屏幕高度
     *
     * @param activity Activity
     * @return px数据
     */
    public static int getScreenHeight(Activity activity) {
        if (screenHeight <= 0) {
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            screenHeight = dm.heightPixels;
        }
        return screenHeight;
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param context （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param context （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
    /**
     * 获取手机的状态栏高度(单位为px像素)
     */
    public static int getStatusBarHeight(Context ctx) {
        int height = 0;
        int resId = ctx.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            height = ctx.getResources().getDimensionPixelSize(resId);
        }
        return height;
    }
}
