package com.jingdong.common.web.javainterface;

import android.content.Intent;
import com.jingdong.common.web.IRouterParams;

/* loaded from: classes6.dex */
public class SettingsRouter {
    public static String navigate(IRouterParams iRouterParams) {
        try {
            Intent intent = new Intent(iRouterParams.getRouterParam());
            intent.setFlags(268435456);
            iRouterParams.getContext().startActivity(intent);
            return "1";
        } catch (Exception unused) {
            return "0";
        }
    }
}
