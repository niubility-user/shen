package com.jd.verify;

import android.content.Context;
import java.io.File;
import java.util.Date;

/* loaded from: classes18.dex */
public class f {
    private final String a;
    private final String b;

    /* renamed from: c  reason: collision with root package name */
    private final String f7171c;
    private File d;

    /* renamed from: e  reason: collision with root package name */
    private i f7172e = new i("com.jd.stat.analytics.LoggerSaver");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class a implements Runnable {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                e.a(this.a, f.this.d, true);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public f(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.f7171c = str3;
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
        this.d = new File(file, c.a(new Date()) + "-" + this.f7171c);
    }

    public void a(String str) {
        try {
            this.f7172e.post(new a(str));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
