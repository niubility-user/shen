package com.jingdong.app.mall.home;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.jdsdk.JdSdk;
import java.io.File;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class JDHomeRouter {
    private static final String TAB_STATE = "JDHomeShowNearby";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setShowTopTab(boolean z) {
        File file = new File(JdSdk.getInstance().getApplication().getFilesDir(), TAB_STATE);
        if (z) {
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        if (!z && file.exists()) {
            file.deleteOnExit();
        }
    }

    static boolean showTopTab() {
        return new File(JdSdk.getInstance().getApplication().getFilesDir(), TAB_STATE).exists();
    }

    public void currentShowNearby(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (callBackListener instanceof CallBackWithReturnListener) {
            ((CallBackWithReturnListener) callBackListener).onComplete(showTopTab() ? "1" : "0");
        }
    }
}
