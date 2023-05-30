package com.jingdong.jdsdk.d.c.a;

import androidx.fragment.app.Fragment;
import com.jingdong.sdk.platform.lib.openapi.ui.IXView;

/* loaded from: classes14.dex */
public class r implements IXView {
    private static r a;

    private r() {
    }

    public static synchronized r a() {
        r rVar;
        synchronized (r.class) {
            if (a == null) {
                a = new r();
            }
            rVar = a;
        }
        return rVar;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.ui.IXView
    public void showFirstPurchaseXView(Fragment fragment, String str) {
    }
}
