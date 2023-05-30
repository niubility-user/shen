package com.jingdong.app.mall.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class l {
    public static ArrayList<a> a = new ArrayList<>();

    /* loaded from: classes4.dex */
    public interface a {
        void a(boolean z);
    }

    public static void a(a aVar) {
        if (aVar == null || a.contains(aVar)) {
            return;
        }
        a.add(aVar);
    }

    public static String b() {
        return SharedPreferencesUtil.getString("jdVersionTrackInfo", "");
    }

    private static void c(boolean z) {
        ArrayList<a> arrayList = a;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        Iterator<a> it = a.iterator();
        while (it.hasNext()) {
            it.next().a(z);
        }
    }

    public static void d() {
        try {
            SharedPreferences.Editor edit = SharedPreferencesUtil.getSharedPreferences().edit();
            String string = SharedPreferencesUtil.getString("lastVersion", "unknow");
            int i2 = SharedPreferencesUtil.getInt("lastBuild", -1);
            String appVersionName = BaseInfo.getAppVersionName();
            int appVersionCode = BaseInfo.getAppVersionCode();
            if (i2 == appVersionCode && TextUtils.equals(string, appVersionName)) {
                c(false);
            }
            String string2 = SharedPreferencesUtil.getString("lastChannel", "unknow");
            String string3 = SharedPreferencesUtil.getString("lastArm64Bit", "unknow");
            String property = Configuration.getProperty(Configuration.PARTNER, "unknow");
            edit.putString("lastVersion", appVersionName);
            edit.putInt("lastBuild", appVersionCode);
            edit.putString("lastChannel", property);
            edit.putString("lastArm64Bit", DYConstants.DY_FALSE);
            edit.putString("jdVersionTrackInfo", "{lastVersion:" + string + "(" + i2 + "),lastChannel:" + string2 + DYConstants.DY_REGEX_COMMA + "lastArm64Bit:" + string3 + DYConstants.DY_REGEX_COMMA + "}");
            edit.apply();
            c(true);
        } catch (Exception unused) {
        }
    }
}
