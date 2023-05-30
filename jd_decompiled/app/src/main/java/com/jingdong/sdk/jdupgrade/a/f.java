package com.jingdong.sdk.jdupgrade.a;

import com.jingdong.sdk.jdupgrade.JDMallUpgradeInfoCallBack;
import com.jingdong.sdk.jdupgrade.a.j.j;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public class f {
    private JDMallUpgradeInfoCallBack a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class a implements Runnable {
        a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:41:0x0117 A[Catch: all -> 0x0136, TryCatch #0 {all -> 0x0136, blocks: (B:18:0x0056, B:20:0x0098, B:23:0x00c7, B:25:0x00d1, B:30:0x00df, B:32:0x00f3, B:34:0x00fd, B:39:0x010b, B:41:0x0117, B:43:0x0121, B:46:0x012c), top: B:54:0x0056 }] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 339
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.jdupgrade.a.f.a.run():void");
        }
    }

    public f(JDMallUpgradeInfoCallBack jDMallUpgradeInfoCallBack) {
        this.a = jDMallUpgradeInfoCallBack;
    }

    public void b() {
        j.c().execute(new a());
    }
}
