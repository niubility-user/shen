package com.jingdong.app.mall.main;

import android.app.Activity;
import android.content.IntentFilter;
import com.jingdong.aura.wrapper.AuraConfig;

/* loaded from: classes4.dex */
public class d {
    private a a;
    private boolean b;

    /* loaded from: classes4.dex */
    public interface a {
        void m();
    }

    public boolean a() {
        return this.b;
    }

    public void b(Activity activity, a aVar) {
        this.a = aVar;
        this.b = false;
        new IntentFilter().addAction(AuraConfig.ACTION_BROADCAST_BUNDLES_INSTALLED);
        com.jingdong.app.mall.aura.e.o();
        this.b = true;
        a aVar2 = this.a;
        if (aVar2 != null) {
            aVar2.m();
        }
    }
}
