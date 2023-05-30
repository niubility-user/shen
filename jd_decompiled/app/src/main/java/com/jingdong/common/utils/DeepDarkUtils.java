package com.jingdong.common.utils;

import android.graphics.Color;
import android.text.TextUtils;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Map;

/* loaded from: classes6.dex */
public class DeepDarkUtils {
    private static final String TAG = "DeepDarkUtils";
    private Map<String, Object> listKeyMaps;

    /* loaded from: classes6.dex */
    private static class SingletonHolder {
        private static final DeepDarkUtils INSTANCE = new DeepDarkUtils();

        private SingletonHolder() {
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int getDarkColor(String str) {
        char c2;
        String str2;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        String lowerCase = str.toLowerCase();
        lowerCase.hashCode();
        switch (lowerCase.hashCode()) {
            case -1814238801:
                if (lowerCase.equals(JDDarkUtil.COLOR_262626)) {
                    c2 = 0;
                    break;
                }
                c2 = '\uffff';
                break;
            case -1600683228:
                if (lowerCase.equals(JDDarkUtil.COLOR_8C8C8C)) {
                    c2 = 1;
                    break;
                }
                c2 = '\uffff';
                break;
            case -394232913:
                if (lowerCase.equals(JDDarkUtil.COLOR_BFBFBF)) {
                    c2 = 2;
                    break;
                }
                c2 = '\uffff';
                break;
            case -329216089:
                if (lowerCase.equals(JDDarkUtil.COLOR_F2270C)) {
                    c2 = 3;
                    break;
                }
                c2 = '\uffff';
                break;
            case -279597021:
                if (lowerCase.equals(JDDarkUtil.COLOR_FFFFFFF)) {
                    c2 = 4;
                    break;
                }
                c2 = '\uffff';
                break;
            case 261109460:
                if (lowerCase.equals("#f2f2f2_bg1")) {
                    c2 = 5;
                    break;
                }
                c2 = '\uffff';
                break;
            case 261109462:
                if (lowerCase.equals("#f2f2f2_bg3")) {
                    c2 = 6;
                    break;
                }
                c2 = '\uffff';
                break;
            default:
                c2 = '\uffff';
                break;
        }
        switch (c2) {
            case 0:
                str2 = "#ececec";
                break;
            case 1:
                str2 = JDDarkUtil.COLOR_848383;
                break;
            case 2:
                str2 = JDDarkUtil.COLOR_555353;
                break;
            case 3:
                str2 = "#ff3826";
                break;
            case 4:
                str2 = "#1d1b1b";
                break;
            case 5:
                str2 = JDDarkUtil.COLOR_141212;
                break;
            case 6:
                str2 = "#302e2e";
                break;
            default:
                return -1;
        }
        return Color.parseColor(str2);
    }

    public static int getDarkColor_262626() {
        return Color.parseColor("#ececec");
    }

    public static int getDarkColor_8C8C8C() {
        return Color.parseColor(JDDarkUtil.COLOR_848383);
    }

    public static int getDarkColor_BFBFBF() {
        return Color.parseColor(JDDarkUtil.COLOR_555353);
    }

    public static int getDarkColor_F2270C() {
        return Color.parseColor("#ff3826");
    }

    public static int getDarkColor_F2F2F2_bg1() {
        return Color.parseColor(JDDarkUtil.COLOR_141212);
    }

    public static int getDarkColor_F2F2F2_bg3() {
        return Color.parseColor("#302e2e");
    }

    public static int getDarkColor_FFFFFF() {
        return Color.parseColor("#1d1b1b");
    }

    public static DeepDarkUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public int getDarkColorFromJson(String str) {
        Map<String, Object> map;
        if (!TextUtils.isEmpty(str) && (map = this.listKeyMaps) != null && !map.isEmpty()) {
            try {
                Object obj = this.listKeyMaps.get(str.toLowerCase());
                String str2 = obj instanceof String ? (String) obj : "";
                if (OKLog.D) {
                    OKLog.d(TAG, "uiMode=" + DeepDarkChangeManager.getInstance().getUIMode() + "key=" + str + " darkColor=" + str2);
                }
                if (DeepDarkChangeManager.getInstance().getUIMode() == 1) {
                    if (TextUtils.isEmpty(str2)) {
                        return -1;
                    }
                    return Color.parseColor(str2);
                }
                if (str.length() > 7) {
                    str = str.substring(0, 7);
                }
                return Color.parseColor(str);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        }
        return -1;
    }

    private DeepDarkUtils() {
        this.listKeyMaps = ReadAssetsJsonUtil.listKeyMaps(ReadAssetsJsonUtil.getJson("dark_common_color.json", JdSdk.getInstance().getApplicationContext()));
    }
}
