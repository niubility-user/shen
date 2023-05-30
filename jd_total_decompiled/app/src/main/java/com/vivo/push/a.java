package com.vivo.push;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes11.dex */
public final class a {
    private Bundle a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f18236c;

    public a(String str, String str2, Bundle bundle) {
        this.b = str;
        this.f18236c = str2;
        this.a = bundle;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x001b, code lost:
        if (android.text.TextUtils.isEmpty(r3) == false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static a a(Intent intent) {
        String str;
        if (intent == null) {
            com.vivo.push.util.p.a("BundleWapper", "create error : intent is null");
            return null;
        }
        Bundle extras = intent.getExtras();
        if (extras != null) {
            str = extras.getString("client_pkgname");
        }
        str = null;
        if (TextUtils.isEmpty(str)) {
            com.vivo.push.util.p.b("BundleWapper", "create warning: pkgName is null");
        }
        String str2 = intent.getPackage();
        if (TextUtils.isEmpty(str2)) {
            String packageName = intent.getComponent() != null ? intent.getComponent().getPackageName() : null;
            if (TextUtils.isEmpty(packageName)) {
                com.vivo.push.util.p.b("BundleWapper", "create warning: targetPkgName is null");
            }
            str2 = packageName;
        }
        return new a(str, str2, extras);
    }

    public final int b(String str, int i2) {
        Bundle bundle = this.a;
        return bundle == null ? i2 : bundle.getInt(str, i2);
    }

    public final ArrayList<String> c(String str) {
        Bundle bundle = this.a;
        if (bundle == null) {
            return null;
        }
        return bundle.getStringArrayList(str);
    }

    public final Serializable d(String str) {
        Bundle bundle = this.a;
        if (bundle == null) {
            return null;
        }
        return bundle.getSerializable(str);
    }

    public final boolean e(String str) {
        Bundle bundle = this.a;
        if (bundle == null) {
            return false;
        }
        return bundle.getBoolean(str, false);
    }

    public final byte[] b(String str) {
        Bundle bundle = this.a;
        if (bundle == null) {
            return null;
        }
        return bundle.getByteArray(str);
    }

    public final long b(String str, long j2) {
        Bundle bundle = this.a;
        return bundle == null ? j2 : bundle.getLong(str, j2);
    }

    public final Bundle b() {
        return this.a;
    }

    public final void a(String str, int i2) {
        if (this.a == null) {
            this.a = new Bundle();
        }
        this.a.putInt(str, i2);
    }

    public final void a(String str, long j2) {
        if (this.a == null) {
            this.a = new Bundle();
        }
        this.a.putLong(str, j2);
    }

    public final void a(String str, String str2) {
        if (this.a == null) {
            this.a = new Bundle();
        }
        this.a.putString(str, str2);
    }

    public final void a(String str, byte[] bArr) {
        if (this.a == null) {
            this.a = new Bundle();
        }
        this.a.putByteArray(str, bArr);
    }

    public final void a(String str, Serializable serializable) {
        if (this.a == null) {
            this.a = new Bundle();
        }
        this.a.putSerializable(str, serializable);
    }

    public final void a(String str, boolean z) {
        if (this.a == null) {
            this.a = new Bundle();
        }
        this.a.putBoolean(str, z);
    }

    public final void a(String str, ArrayList<String> arrayList) {
        if (this.a == null) {
            this.a = new Bundle();
        }
        this.a.putStringArrayList(str, arrayList);
    }

    public final String a(String str) {
        Bundle bundle = this.a;
        if (bundle == null) {
            return null;
        }
        return bundle.getString(str);
    }

    public final String a() {
        return this.b;
    }
}
