package com.jd.lib.cashier.sdk.pay.aac.impl.channel;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.pay.recyclerview.CustomDrawOrderRecyclerView;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public abstract class AbsCashierPayItemDecorationProxy {
    private RecyclerView.ItemDecoration a;
    private CustomDrawOrderRecyclerView b;

    public AbsCashierPayItemDecorationProxy(@Nullable CustomDrawOrderRecyclerView customDrawOrderRecyclerView) {
        this.b = customDrawOrderRecyclerView;
    }

    private final void a() {
        CustomDrawOrderRecyclerView customDrawOrderRecyclerView;
        if (this.a == null) {
            d();
        }
        RecyclerView.ItemDecoration itemDecoration = this.a;
        if (itemDecoration == null || (customDrawOrderRecyclerView = this.b) == null) {
            return;
        }
        if (itemDecoration == null) {
            Intrinsics.throwNpe();
        }
        customDrawOrderRecyclerView.addItemDecoration(itemDecoration);
    }

    private final void d() {
        if (this.a == null) {
            this.a = new RecyclerView.ItemDecoration() { // from class: com.jd.lib.cashier.sdk.pay.aac.impl.channel.AbsCashierPayItemDecorationProxy$initItemDecoration$1
                @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
                public void getItemOffsets(@NotNull Rect outRect, @NotNull View view, @NotNull RecyclerView parent, @NotNull RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);
                    if (view.getVisibility() == 0) {
                        AbsCashierPayItemDecorationProxy.this.c(outRect, view, parent);
                    }
                }
            };
        }
    }

    public final void b(@Nullable List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list) {
        e(list);
        a();
    }

    public abstract void c(@NotNull Rect rect, @NotNull View view, @NotNull RecyclerView recyclerView);

    public abstract void e(@Nullable List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list);

    public final void f() {
        CustomDrawOrderRecyclerView customDrawOrderRecyclerView;
        RecyclerView.ItemDecoration itemDecoration = this.a;
        if (itemDecoration == null || (customDrawOrderRecyclerView = this.b) == null) {
            return;
        }
        if (itemDecoration == null) {
            Intrinsics.throwNpe();
        }
        customDrawOrderRecyclerView.removeItemDecoration(itemDecoration);
    }
}
