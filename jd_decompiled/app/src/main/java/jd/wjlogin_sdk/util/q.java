package jd.wjlogin_sdk.util;

import android.content.Context;
import java.io.File;
import java.util.Date;

/* loaded from: classes.dex */
public class q {
    private final String a;
    private final String b;

    /* renamed from: c  reason: collision with root package name */
    private final String f19979c;
    private File d;

    /* renamed from: e  reason: collision with root package name */
    private d0 f19980e = new d0("com.wjlogin.analytics.LoggerSaver");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Runnable {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                k.a(this.a, q.this.d, true);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public q(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.f19979c = str3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Context context) {
        if (context == null) {
            return;
        }
        File file = new File(context.getExternalFilesDir(this.a), this.b);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        this.d = new File(file, i.a(new Date()) + "-" + this.f19979c);
    }

    public void a(String str) {
        try {
            this.f19980e.post(new a(str));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
