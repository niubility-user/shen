package com.jingdong.app.mall.home.r.e;

import android.content.Context;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.i.t;
import com.jingdong.app.mall.home.floor.common.i.u;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class i extends d {
    public static AtomicInteger z = new AtomicInteger(0);

    public i(Context context, h hVar, t tVar, boolean z2) {
        super(hVar, tVar, context, z2);
    }

    public static ArrayList<d> x(Context context, h hVar, boolean z2) {
        ArrayList<d> arrayList = null;
        try {
            JDJSONArray j2 = hVar.j();
            if (j2 == null || j2.size() == 0) {
                return null;
            }
            ArrayList<d> arrayList2 = new ArrayList<>();
            for (int i2 = 0; i2 < j2.size(); i2++) {
                try {
                    JDJSONObject jSONObject = j2.getJSONObject(i2);
                    if (jSONObject != null) {
                        i iVar = new i(jSONObject, z2);
                        iVar.mParentModel = hVar;
                        iVar.t(u.c(iVar), context);
                        arrayList2.add(iVar);
                    }
                } catch (Exception e2) {
                    e = e2;
                    arrayList = arrayList2;
                    e.printStackTrace();
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (Exception e3) {
            e = e3;
        }
    }

    @Override // com.jingdong.app.mall.home.r.e.d
    public void t(t tVar, Context context) {
        super.t(tVar, context);
        if (t.UNKNOWN != this.q) {
            this.t = z.getAndIncrement();
        }
    }

    public i(JDJSONObject jDJSONObject, boolean z2) {
        super(jDJSONObject, z2);
    }
}
