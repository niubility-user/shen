package com.jd.lib.cashier.sdk.pay.aac.impl.channel;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.recyclerview.CustomDrawOrderRecyclerView;
import java.util.HashMap;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class n extends AbsCashierPayItemDecorationProxy {

    /* renamed from: c */
    private final HashMap<Integer, Boolean> f3723c;

    public n(@Nullable CustomDrawOrderRecyclerView customDrawOrderRecyclerView) {
        super(customDrawOrderRecyclerView);
        this.f3723c = new HashMap<>();
    }

    @Override // com.jd.lib.cashier.sdk.pay.aac.impl.channel.AbsCashierPayItemDecorationProxy
    public void c(@NotNull Rect rect, @NotNull View view, @NotNull RecyclerView recyclerView) {
        if (view.getId() == R.id.lib_cashier_pay_channel_grid_floor_root) {
            if (Intrinsics.areEqual(this.f3723c.get(Integer.valueOf(recyclerView.getChildAdapterPosition(view))), Boolean.TRUE)) {
                rect.left = DpiUtil.dip2px(recyclerView.getContext(), 10.0f);
                rect.right = DpiUtil.dip2px(recyclerView.getContext(), 3.5f);
            } else {
                rect.left = DpiUtil.dip2px(recyclerView.getContext(), 3.5f);
                rect.right = DpiUtil.dip2px(recyclerView.getContext(), 10.0f);
            }
            rect.bottom = DpiUtil.dip2px(recyclerView.getContext(), 7.0f);
            return;
        }
        if (view.getId() == R.id.lib_cashier_pay_channel_grid_bt_plan_floor_root) {
            rect.bottom = DpiUtil.dip2px(recyclerView.getContext(), 7.0f);
        }
        rect.left = DpiUtil.dip2px(recyclerView.getContext(), 10.0f);
        rect.right = DpiUtil.dip2px(recyclerView.getContext(), 10.0f);
    }

    @Override // com.jd.lib.cashier.sdk.pay.aac.impl.channel.AbsCashierPayItemDecorationProxy
    public void e(@Nullable List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list) {
        if (list != null) {
            if (list == null || list.isEmpty()) {
                return;
            }
            this.f3723c.clear();
            int size = list.size();
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                com.jd.lib.cashier.sdk.d.a.e.a aVar = list.get(i4);
                if (aVar instanceof x) {
                    x xVar = (x) aVar;
                    if (200001 == xVar.getItemType()) {
                        if (com.jd.lib.cashier.sdk.h.h.g.n(xVar.a().code)) {
                            i3++;
                            this.f3723c.put(Integer.valueOf(i4), Boolean.valueOf(i3 % 2 != 0));
                        } else {
                            i2++;
                            this.f3723c.put(Integer.valueOf(i4), Boolean.valueOf(i2 % 2 != 0));
                        }
                    }
                }
            }
        }
    }
}
