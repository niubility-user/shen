package com.jingdong.app.mall.home.floor.view.adapter;

import android.content.Context;
import com.jingdong.app.mall.home.r.f.a.o;

/* loaded from: classes4.dex */
public class a {
    public static MallIconBaseAdapter a(Context context, o oVar) {
        if (oVar.B0()) {
            return new MallIconFloorElderAdapter(oVar, context);
        }
        return new MallIconFloorNewAdapter(oVar, context);
    }
}
