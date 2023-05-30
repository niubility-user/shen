package com.unionpay;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class b implements Runnable {
    @Override // java.lang.Runnable
    public final void run() {
        Handler handler;
        com.unionpay.a.d dVar;
        Context q;
        Handler handler2;
        Handler handler3;
        Handler handler4;
        Handler handler5;
        try {
            handler = UPPayAssistEx.W;
            handler.sendEmptyMessageDelayed(1001, 800L);
            dVar = UPPayAssistEx.V;
            q = UPPayAssistEx.q();
            com.unionpay.a.c cVar = new com.unionpay.a.c(dVar, com.unionpay.utils.b.a(q));
            cVar.a();
            String b = cVar.b();
            handler2 = UPPayAssistEx.W;
            if (handler2 != null) {
                handler3 = UPPayAssistEx.W;
                Message obtainMessage = handler3.obtainMessage();
                obtainMessage.what = 1002;
                obtainMessage.obj = b;
                handler4 = UPPayAssistEx.W;
                handler4.removeMessages(1001);
                handler5 = UPPayAssistEx.W;
                handler5.sendMessage(obtainMessage);
            }
        } catch (Exception unused) {
        }
    }
}
