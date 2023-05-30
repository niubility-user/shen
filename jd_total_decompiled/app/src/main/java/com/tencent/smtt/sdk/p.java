package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.tbs.video.interfaces.IUserStateChangedListener;

/* loaded from: classes9.dex */
class p {

    /* renamed from: e  reason: collision with root package name */
    private static p f17857e;
    r a;
    Context b;

    /* renamed from: c  reason: collision with root package name */
    com.tencent.tbs.video.interfaces.a f17858c;
    IUserStateChangedListener d;

    private p(Context context) {
        this.a = null;
        this.b = context.getApplicationContext();
        this.a = new r(this.b);
    }

    public static synchronized p a(Context context) {
        p pVar;
        synchronized (p.class) {
            if (f17857e == null) {
                f17857e = new p(context);
            }
            pVar = f17857e;
        }
        return pVar;
    }

    public void a(int i2, int i3, Intent intent) {
        com.tencent.tbs.video.interfaces.a aVar = this.f17858c;
        if (aVar != null) {
            aVar.a(i2, i3, intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Activity activity, int i2) {
        this.a.a(activity, i2);
    }

    public boolean a() {
        this.a.a();
        return this.a.b();
    }

    public boolean a(String str, Bundle bundle, com.tencent.tbs.video.interfaces.a aVar) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("videoUrl", str);
        }
        if (aVar != null) {
            this.a.a();
            if (!this.a.b()) {
                return false;
            }
            this.f17858c = aVar;
            IUserStateChangedListener iUserStateChangedListener = new IUserStateChangedListener() { // from class: com.tencent.smtt.sdk.p.1
                @Override // com.tencent.tbs.video.interfaces.IUserStateChangedListener
                public void onUserStateChanged() {
                    p.this.a.c();
                }
            };
            this.d = iUserStateChangedListener;
            this.f17858c.a(iUserStateChangedListener);
            bundle.putInt("callMode", 3);
        } else {
            bundle.putInt("callMode", 1);
        }
        this.a.a(bundle, aVar == null ? null : this);
        return true;
    }
}
