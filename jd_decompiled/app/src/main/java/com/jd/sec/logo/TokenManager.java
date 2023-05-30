package com.jd.sec.logo;

import android.content.Context;
import com.jdcn.risk.cpp.LoadDoor;

/* loaded from: classes.dex */
public class TokenManager {
    public static String getToken(Context context) {
        return LoadDoor.e().h(context);
    }
}
