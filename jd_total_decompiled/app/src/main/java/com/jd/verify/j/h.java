package com.jd.verify.j;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

/* loaded from: classes18.dex */
public class h {
    static final Handler a = new Handler(Looper.getMainLooper());

    /* loaded from: classes18.dex */
    class a implements Runnable {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(com.jd.verify.d.a(), this.a, 0).show();
        }
    }

    public static void a(String str) {
        Handler handler;
        if (!com.jd.verify.a.a() || TextUtils.isEmpty(str) || (handler = a) == null) {
            return;
        }
        handler.post(new a(str));
    }
}
