package project.scode.com.menuselectdemo;

import android.content.res.Resources;

/**
 * Created by Administrator on 2018/8/12.
 */

public class DimenUtils {
    public static int px2dip(int pxValue)
    {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static float dip2px(float dipValue)
    {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return  (dipValue * scale + 0.5f);
    }
}
