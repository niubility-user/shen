package com.hihonor.push.sdk;

import android.os.Handler;
import android.os.Message;

/* loaded from: classes12.dex */
public class p implements Handler.Callback {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ t f1107g;

    public p(t tVar) {
        this.f1107g = tVar;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message == null || message.what != 1001) {
            return false;
        }
        this.f1107g.b(8002003);
        return true;
    }
}
