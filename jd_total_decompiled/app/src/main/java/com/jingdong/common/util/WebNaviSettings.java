package com.jingdong.common.util;

import android.graphics.Color;
import android.graphics.Typeface;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.navigatorholder.R;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.wireless.iconfont.JDIconFontUtil;

/* loaded from: classes6.dex */
public class WebNaviSettings {
    private static final String TAG = "NavigatorHolder-Settings";
    public static int bgDarkModeColor;
    public static int bgWhiteModeColor;
    public static int iconDarkModeColor;
    public static int iconWhiteModeColor;
    private static Typeface iconfontCommonTypeface;
    public static int titleDarkModeColor;
    public static int titleWhiteModeColor;

    static {
        try {
            bgWhiteModeColor = JdSdk.getInstance().getApplicationContext().getResources().getColor(R.color.web_navi_bg_white_mode);
            bgDarkModeColor = JdSdk.getInstance().getApplicationContext().getResources().getColor(R.color.web_navi_bg_dark_mode);
            iconWhiteModeColor = JdSdk.getInstance().getApplicationContext().getResources().getColor(R.color.web_navi_icon_color_white_mode);
            iconDarkModeColor = JdSdk.getInstance().getApplicationContext().getResources().getColor(R.color.web_navi_icon_color_dark_mode);
            titleWhiteModeColor = JdSdk.getInstance().getApplicationContext().getResources().getColor(R.color.web_navi_title_color_white_mode);
            titleDarkModeColor = JdSdk.getInstance().getApplicationContext().getResources().getColor(R.color.web_navi_title_color_dark_mode);
        } catch (Exception e2) {
            bgWhiteModeColor = Color.parseColor("#FFFFFF");
            bgDarkModeColor = Color.parseColor(JDDarkUtil.COLOR_0000000);
            iconWhiteModeColor = Color.parseColor("#232326");
            iconDarkModeColor = Color.parseColor(JDDarkUtil.COLOR_ECECEC);
            titleWhiteModeColor = Color.parseColor("#232326");
            titleDarkModeColor = Color.parseColor(JDDarkUtil.COLOR_ECECEC);
            OKLog.e(TAG, e2);
        }
    }

    private WebNaviSettings() {
    }

    public static Typeface getIconfontCommonTypeface() {
        if (iconfontCommonTypeface == null) {
            try {
                iconfontCommonTypeface = Typeface.createFromAsset(JdSdk.getInstance().getApplicationContext().getAssets(), JDIconFontUtil.COMMON_PATH);
            } catch (Exception unused) {
                JDIconFontUtil.sendErrMta(JdSdk.getInstance().getApplicationContext(), JDIconFontUtil.COMMON_PATH, "");
            }
        }
        return iconfontCommonTypeface;
    }
}
