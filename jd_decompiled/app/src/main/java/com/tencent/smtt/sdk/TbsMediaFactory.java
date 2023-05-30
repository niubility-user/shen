package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.export.external.DexLoader;

/* loaded from: classes9.dex */
public class TbsMediaFactory {
    private Context a;
    private s b = null;

    /* renamed from: c  reason: collision with root package name */
    private DexLoader f17765c = null;

    public TbsMediaFactory(Context context) {
        this.a = null;
        this.a = context.getApplicationContext();
        a();
    }

    private void a() {
        if (this.a == null) {
            return;
        }
        if (this.b == null) {
            f.a(true).a(this.a, false, false);
            s a = f.a(true).a();
            this.b = a;
            if (a != null) {
                this.f17765c = a.c();
            }
        }
        if (this.b == null || this.f17765c == null) {
            throw new RuntimeException("tbs core dex(s) load failure !!!");
        }
    }

    public TbsMediaPlayer createPlayer() {
        DexLoader dexLoader;
        if (this.b == null || (dexLoader = this.f17765c) == null) {
            throw new RuntimeException("tbs core dex(s) did not loaded !!!");
        }
        return new TbsMediaPlayer(new n(dexLoader, this.a));
    }
}
