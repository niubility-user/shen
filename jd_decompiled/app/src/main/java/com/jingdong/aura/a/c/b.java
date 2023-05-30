package com.jingdong.aura.a.c;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import com.jingdong.aura.core.reflection.c;

/* loaded from: classes4.dex */
public class b extends c.a {
    private static final com.jingdong.aura.core.util.l.b b = com.jingdong.aura.core.util.l.c.a("ActivityTaskManagerDelegate");

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

    /* JADX WARN: Removed duplicated region for block: B:31:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0160 A[EDGE_INSN: B:77:0x0160->B:67:0x0160 BREAK  A[LOOP:0: B:29:0x0081->B:66:0x015c], SYNTHETIC] */
    @Override // com.jingdong.aura.core.reflection.c.a, java.lang.reflect.InvocationHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object invoke(java.lang.Object r12, java.lang.reflect.Method r13, java.lang.Object[] r14) {
        /*
            Method dump skipped, instructions count: 357
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.aura.a.c.b.invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
    }
}
