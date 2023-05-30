package com.jd.lib.cashier.sdk.pay.aac.impl.channel;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.pay.recyclerview.CustomDrawOrderRecyclerView;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class d extends AbsCashierPayItemDecorationProxy {

    /* renamed from: c  reason: collision with root package name */
    private int f3701c;

    public d(@Nullable CustomDrawOrderRecyclerView customDrawOrderRecyclerView) {
        super(customDrawOrderRecyclerView);
        this.f3701c = -1;
    }

    @Override // com.jd.lib.cashier.sdk.pay.aac.impl.channel.AbsCashierPayItemDecorationProxy
    public void c(@NotNull Rect rect, @NotNull View view, @NotNull RecyclerView recyclerView) {
        if (this.f3701c != -1 && recyclerView.getChildAdapterPosition(view) == this.f3701c) {
            rect.top = DpiUtil.dip2px(recyclerView.getContext(), -20.0f);
        }
        rect.left = DpiUtil.dip2px(recyclerView.getContext(), 8.0f);
        rect.right = DpiUtil.dip2px(recyclerView.getContext(), 8.0f);
    }

    @Override // com.jd.lib.cashier.sdk.pay.aac.impl.channel.AbsCashierPayItemDecorationProxy
    public void e(@Nullable List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list) {
        if (list != null) {
            if (list == null || list.isEmpty()) {
                return;
            }
            this.f3701c = -1;
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (600004 == list.get(i2).getItemType()) {
                    this.f3701c = i2 + 1;
                }
            }
        }
    }
}
