package com.jingdong.common.login;

import android.content.Context;
import com.jd.sec.LogoManager;

/* loaded from: classes.dex */
public class DeviceFinger {
    public static String getFinger(Context context) {
        return context == null ? "" : LogoManager.getInstance(context).getLogo();
    }
}
