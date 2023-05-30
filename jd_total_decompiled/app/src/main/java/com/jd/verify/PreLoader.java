package com.jd.verify;

import android.content.Context;

/* loaded from: classes18.dex */
public class PreLoader {
    g a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Context context, String str) {
        try {
            g gVar = new g(context);
            this.a = gVar;
            gVar.a(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onDestroy() {
        try {
            g gVar = this.a;
            if (gVar != null) {
                gVar.b();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
