package com.wjlogin.onekey.sdk.common.a.a;

import com.wjlogin.onekey.sdk.util.LogUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class c implements Runnable {
    final /* synthetic */ g a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(g gVar) {
        this.a = gVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z;
        boolean z2;
        b bVar;
        this.a.f18333j = true;
        z = this.a.f18334k;
        if (!z) {
            z2 = this.a.f18335l;
            if (!z2) {
                if (LogUtil.enableLog) {
                    LogUtil.LogI("WJLogin.OneKey.TelecomHttpExecut", "\u5012\u8ba1\u65f6\u7ed3\u675f\uff0c\u5df2\u8d85\u65f6\uff0c\u7ed9\u51fa\u8d85\u65f6\u7684\u63d0\u793a");
                }
                bVar = this.a.f18331h;
                bVar.a(com.wjlogin.onekey.sdk.util.a.a(com.wjlogin.onekey.sdk.common.a.b.c.d, "\u7f51\u7edc\u5728\u5f00\u5c0f\u5dee\uff0c\u68c0\u67e5\u540e\u518d\u8bd5\u5427", "CT", com.wjlogin.onekey.sdk.common.a.b.c.f18345j));
                return;
            }
        }
        if (LogUtil.enableLog) {
            LogUtil.LogI("WJLogin.OneKey.TelecomHttpExecut", "\u5012\u8ba1\u65f6\u7ed3\u675f\u65f6\uff0c\u5df2\u8fd4\u56de\u8bf7\u6c42\u7ed3\u679c\u6216\u8005\u9519\u8bef\uff0c\u4e0d\u518d\u7ed9\u8d85\u65f6\u63d0\u793a");
        }
    }
}
