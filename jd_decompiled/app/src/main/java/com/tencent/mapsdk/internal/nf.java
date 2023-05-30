package com.tencent.mapsdk.internal;

import android.content.Context;
import android.os.AsyncTask;
import com.tencent.map.tools.net.NetResponse;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public class nf extends AsyncTask<Object, Void, Boolean> {
    private WeakReference<Context> a;
    private WeakReference<a> b;

    /* renamed from: c */
    private uh f16889c;

    /* loaded from: classes9.dex */
    public interface a {
        void a(boolean z, uh uhVar);
    }

    public nf(h1 h1Var, a aVar) {
        this.a = new WeakReference<>(h1Var.j());
        this.f16889c = h1Var.l().u();
        this.b = new WeakReference<>(aVar);
    }

    private boolean a(Context context, String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return this.f16889c.b(context, str);
    }

    @Override // android.os.AsyncTask
    /* renamed from: a */
    public Boolean doInBackground(Object... objArr) {
        if (this.a.get() == null) {
            return Boolean.FALSE;
        }
        Context context = this.a.get();
        try {
            NetResponse checkAuth = ((z2) ((n3) l2.a(n3.class)).d()).checkAuth(b7.t(), b7.F(), this.f16889c.j(), b7.A(), this.f16889c.a(), 3, this.f16889c.e());
            if (checkAuth != null && checkAuth.available()) {
                return Boolean.valueOf(a(context, checkAuth.toString()));
            }
            return Boolean.FALSE;
        } catch (Throwable th) {
            ma.a(th.getMessage(), th);
            return Boolean.FALSE;
        }
    }

    @Override // android.os.AsyncTask
    /* renamed from: a */
    public void onPostExecute(Boolean bool) {
        super.onPostExecute(bool);
        WeakReference<a> weakReference = this.b;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.b.get().a(bool.booleanValue(), this.f16889c);
    }
}
