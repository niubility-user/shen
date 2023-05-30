package com.jingdong.jdsdk.e;

import com.jingdong.jdsdk.utils.SharedPreferencesUtil;

/* loaded from: classes14.dex */
public class a {
    public static boolean a() {
        return SharedPreferencesUtil.getSharedPreferences().getBoolean("plug_on_off", true);
    }
}
