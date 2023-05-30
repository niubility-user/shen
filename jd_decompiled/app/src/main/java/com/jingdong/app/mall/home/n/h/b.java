package com.jingdong.app.mall.home.n.h;

import android.content.Context;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.common.entity.JumpEntity;

/* loaded from: classes4.dex */
public class b {
    public static void a(Context context, JumpEntity jumpEntity) {
        b(context, jumpEntity, null);
    }

    public static void b(Context context, JumpEntity jumpEntity, com.jingdong.app.mall.home.n.g.v.c cVar) {
        c(context, jumpEntity, cVar, -1);
    }

    public static void c(Context context, JumpEntity jumpEntity, com.jingdong.app.mall.home.n.g.v.c cVar, int i2) {
        if (jumpEntity != null) {
            if (cVar != null) {
                cVar.B(i2);
            }
            l.d(context, jumpEntity, i2);
        }
    }

    public static void d(Context context, com.jingdong.app.mall.home.n.g.u.e eVar) {
        if (eVar == null) {
            return;
        }
        b(context, eVar.d(), eVar.k());
    }

    public static void e(Context context, JumpEntity jumpEntity, com.jingdong.app.mall.home.n.g.v.c cVar, int i2) {
        if (cVar != null) {
            cVar.G(i2);
        }
        c(context, jumpEntity, cVar, i2);
    }
}
