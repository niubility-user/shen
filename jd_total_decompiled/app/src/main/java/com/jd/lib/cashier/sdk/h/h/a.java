package com.jd.lib.cashier.sdk.h.h;

import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.pay.adapter.CashierPayAdapter;
import java.util.ArrayList;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class a {
    private static ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> a;
    public static final a b = new a();

    private a() {
    }

    @JvmStatic
    @NotNull
    public static final a a(@NotNull Function1<? super ArrayList<com.jd.lib.cashier.sdk.d.a.e.a>, ? extends ArrayList<com.jd.lib.cashier.sdk.d.a.e.a>> function1) {
        ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> arrayList = a;
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        a = function1.invoke(arrayList);
        return b;
    }

    @JvmStatic
    public static final void b() {
        a = null;
    }

    @JvmStatic
    public static final void c(@Nullable RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
        d(adapter, Boolean.FALSE);
    }

    @JvmStatic
    public static final void d(@Nullable RecyclerView.Adapter<RecyclerView.ViewHolder> adapter, @Nullable Boolean bool) {
        boolean booleanValue = bool != null ? bool.booleanValue() : false;
        if (!(adapter instanceof CashierPayAdapter)) {
            adapter = null;
        }
        CashierPayAdapter cashierPayAdapter = (CashierPayAdapter) adapter;
        if (cashierPayAdapter != null) {
            cashierPayAdapter.A(a, false, booleanValue);
        }
        b();
    }
}
