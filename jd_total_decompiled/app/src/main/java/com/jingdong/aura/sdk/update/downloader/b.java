package com.jingdong.aura.sdk.update.downloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.jingdong.aura.sdk.update.b.c;
import com.jingdong.aura.sdk.update.b.j;

/* loaded from: classes4.dex */
public class b {
    static final String a = "b";

    /* renamed from: c  reason: collision with root package name */
    public static boolean f12259c = true;
    a b = new a() { // from class: com.jingdong.aura.sdk.update.downloader.b.1
        @Override // com.jingdong.aura.sdk.update.downloader.b.a
        public final void a() {
        }
    };
    BroadcastReceiver d = new BroadcastReceiver() { // from class: com.jingdong.aura.sdk.update.downloader.b.2
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (!b.f12259c) {
                c.a(b.a + " -->> onReceive():" + j.c(com.jingdong.aura.sdk.update.a.a().a.context));
                b.this.b.a();
            }
            b.f12259c = false;
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public interface a {
        void a();
    }
}
