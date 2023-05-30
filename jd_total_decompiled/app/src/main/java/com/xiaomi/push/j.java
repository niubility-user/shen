package com.xiaomi.push;

import android.content.SharedPreferences;
import com.xiaomi.push.i;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class j extends i.b {

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ boolean f18763h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ String f18764i;

    /* renamed from: j  reason: collision with root package name */
    final /* synthetic */ i f18765j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(i iVar, i.a aVar, boolean z, String str) {
        super(aVar);
        this.f18765j = iVar;
        this.f18763h = z;
        this.f18764i = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.xiaomi.push.i.b
    public void a() {
        super.a();
    }

    @Override // com.xiaomi.push.i.b
    void b() {
        SharedPreferences sharedPreferences;
        if (this.f18763h) {
            return;
        }
        sharedPreferences = this.f18765j.d;
        sharedPreferences.edit().putLong(this.f18764i, System.currentTimeMillis()).commit();
    }
}
