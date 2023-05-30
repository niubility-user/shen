package com.jd.lib.babel.task.viewkit;

import android.content.Context;
import com.jd.lib.babel.servicekit.iservice.BabelLoginCallback;
import com.jd.lib.babel.servicekit.iservice.ILogin;
import java.util.Map;

/* loaded from: classes14.dex */
public class JDVKitLoginUtil {

    /* loaded from: classes14.dex */
    public interface CallBack {
        void onSuccess();
    }

    public static void toLogin(Context context, ILogin iLogin) {
        toLogin(context, iLogin, null);
    }

    public static void toLogin(Context context, ILogin iLogin, final CallBack callBack) {
        if (context == null || iLogin == null) {
            return;
        }
        iLogin.executeLogin(context, new BabelLoginCallback() { // from class: com.jd.lib.babel.task.viewkit.JDVKitLoginUtil.1
            @Override // com.jd.lib.babel.servicekit.iservice.BabelLoginCallback
            public void onError() {
            }

            @Override // com.jd.lib.babel.servicekit.iservice.BabelLoginCallback
            public void onSuccess(Map<String, String> map) {
                CallBack callBack2 = CallBack.this;
                if (callBack2 != null) {
                    callBack2.onSuccess();
                }
            }
        });
    }
}
