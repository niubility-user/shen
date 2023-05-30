package com.jingdong.aura.a.c;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import com.jingdong.aura.core.reflection.c;

/* loaded from: classes4.dex */
public class a extends c.a {
    private static final com.jingdong.aura.core.util.l.b b = com.jingdong.aura.core.util.l.c.a("ActivityManagerDelegate");

    private void a(Intent intent, int i2, IBinder iBinder, Object[] objArr) {
        com.jingdong.aura.core.util.l.b bVar = b;
        bVar.a("wrapperIntent for activity result! targetIntent:" + intent + "requestCode:" + i2 + " token:" + iBinder);
        try {
            Bundle bundle = new Bundle();
            if (Build.VERSION.SDK_INT >= 18 && iBinder != null) {
                bundle.putBinder("mToken", iBinder);
                if (intent != null) {
                    intent.putExtras(bundle);
                    intent.putExtra("requestCode", i2);
                }
                for (int i3 = 0; i3 < objArr.length; i3++) {
                    Object obj = objArr[i3];
                    if (obj instanceof Integer) {
                        b.a("find requestCode:" + obj + " change to:-1");
                        objArr[i3] = -1;
                        return;
                    }
                }
                return;
            }
            bVar.a("wrapperIntent sdk int < 18");
        } catch (Throwable th) {
            b.d("wrapperIntent fail!!\uff01" + th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:155:0x02c0 A[EDGE_INSN: B:155:0x02c0->B:135:0x02c0 BREAK  A[LOOP:0: B:97:0x01e2->B:134:0x02bc], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0105 A[Catch: all -> 0x02c0, TryCatch #1 {all -> 0x02c0, blocks: (B:4:0x0017, B:6:0x0021, B:14:0x004a, B:16:0x0056, B:18:0x005b, B:20:0x0064, B:22:0x006c, B:26:0x008d, B:25:0x0071, B:28:0x0092, B:30:0x009c, B:33:0x00a2, B:7:0x0032, B:9:0x003e, B:12:0x0043, B:37:0x00c3, B:39:0x00cd, B:49:0x00f9, B:51:0x0105, B:53:0x010a, B:55:0x0113, B:57:0x011b, B:61:0x013c, B:60:0x0120, B:63:0x0141, B:65:0x014b, B:68:0x0164, B:67:0x0150, B:41:0x00df, B:43:0x00eb, B:46:0x00f0), top: B:138:0x0015, inners: #3, #4, #6, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x010a A[Catch: all -> 0x02c0, TryCatch #1 {all -> 0x02c0, blocks: (B:4:0x0017, B:6:0x0021, B:14:0x004a, B:16:0x0056, B:18:0x005b, B:20:0x0064, B:22:0x006c, B:26:0x008d, B:25:0x0071, B:28:0x0092, B:30:0x009c, B:33:0x00a2, B:7:0x0032, B:9:0x003e, B:12:0x0043, B:37:0x00c3, B:39:0x00cd, B:49:0x00f9, B:51:0x0105, B:53:0x010a, B:55:0x0113, B:57:0x011b, B:61:0x013c, B:60:0x0120, B:63:0x0141, B:65:0x014b, B:68:0x0164, B:67:0x0150, B:41:0x00df, B:43:0x00eb, B:46:0x00f0), top: B:138:0x0015, inners: #3, #4, #6, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01e5  */
    @Override // com.jingdong.aura.core.reflection.c.a, java.lang.reflect.InvocationHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object invoke(java.lang.Object r13, java.lang.reflect.Method r14, java.lang.Object[] r15) {
        /*
            Method dump skipped, instructions count: 709
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.aura.a.c.a.invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
    }
}
