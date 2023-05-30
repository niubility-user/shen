package com.jd.lib.cashier.sdk.core.commonfloor.floor;

import android.view.View;
import com.jd.lib.cashier.sdk.core.commonfloor.viewholder.BaseViewHolder;
import com.jd.lib.cashier.sdk.d.a.c.a;
import com.jd.lib.cashier.sdk.d.a.e.a;

/* loaded from: classes14.dex */
public abstract class AbstractFloor<FloorData extends a, Template extends com.jd.lib.cashier.sdk.d.a.e.a> extends BaseViewHolder {
    public AbstractFloor(View view) {
        super(view);
        initView(view);
    }

    public abstract void b(FloorData floordata, Template template);

    public abstract void initView(View view);
}
