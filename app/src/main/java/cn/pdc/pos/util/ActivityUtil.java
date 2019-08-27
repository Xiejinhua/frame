package cn.pdc.pos.util;

import android.app.Activity;

/**
 * 由于保存当前活动的Activity
 *
 * @author Alex
 * @since 2018/8/27
 */
public class ActivityUtil {
    private static Activity mActivity;
    private static Activity mainActivity;

    public static void init(Activity activity) {
        mActivity = activity;
    }

    public static Activity getActivity() {
        return mActivity;
    }

    public static void initMainActivity(Activity activity) {
        mainActivity = activity;
    }

    public static Activity getMainActivity() {
        return mainActivity;
    }

}
