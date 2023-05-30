package com.jd.lib.cashier.sdk.pay.aac.impl.channel;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.adapter.CashierPayAdapter;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.UpdatePaymentListEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class f implements com.jd.lib.cashier.sdk.core.aac.d, com.jd.lib.cashier.sdk.d.d.a {
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    private CashierPayAdapter f3702g;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"com/jd/lib/cashier/sdk/pay/aac/impl/channel/f$a", "Lcom/jd/lib/cashier/sdk/b/i/g;", "Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/UpdatePaymentListEvent;", "", "action", "eventData", "", "onEvent", "(Ljava/lang/String;Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/UpdatePaymentListEvent;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class a implements com.jd.lib.cashier.sdk.b.i.g<UpdatePaymentListEvent> {
        a() {
        }

        @Override // com.jd.lib.cashier.sdk.b.i.g
        public void onEvent(@NotNull String action, @NotNull UpdatePaymentListEvent eventData) {
            if (TextUtils.equals(action, "UPDATE_PAYMENT")) {
                f.this.n(eventData.getPayment());
            } else if (TextUtils.equals(action, "UPDATE_PAYMENT_LIST")) {
                f.this.m(Boolean.valueOf(eventData.getDefaultInit()));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0000j\b\u0012\u0004\u0012\u00020\u0001`\u00022\u0016\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0000j\b\u0012\u0004\u0012\u00020\u0001`\u0002H\n\u00a2\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Ljava/util/ArrayList;", "Lcom/jd/lib/cashier/sdk/d/a/e/a;", "Lkotlin/collections/ArrayList;", "baseTemplateEntityList", "invoke", "(Ljava/util/ArrayList;)Ljava/util/ArrayList;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class b extends Lambda implements Function1<ArrayList<com.jd.lib.cashier.sdk.d.a.e.a>, ArrayList<com.jd.lib.cashier.sdk.d.a.e.a>> {
        b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        @NotNull
        public final ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> invoke(@NotNull ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> arrayList) {
            Collection data;
            ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> arrayList2 = new ArrayList<>();
            if (arrayList.size() <= 0) {
                CashierPayAdapter h2 = f.this.h();
                if (h2 != null && (data = h2.getData()) != null) {
                    Iterator it = data.iterator();
                    while (it.hasNext()) {
                        arrayList2.add((com.jd.lib.cashier.sdk.d.a.e.a) it.next());
                    }
                }
            } else {
                arrayList2.addAll(arrayList);
            }
            return arrayList2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void m(Boolean bool) {
        com.jd.lib.cashier.sdk.h.h.a.a(new b());
        com.jd.lib.cashier.sdk.h.h.a.d(this.f3702g, bool);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void n(Payment payment) {
        List<Payment> list;
        Collection data;
        ArrayList arrayList = new ArrayList();
        CashierPayAdapter cashierPayAdapter = this.f3702g;
        if (cashierPayAdapter != null && (data = cashierPayAdapter.getData()) != null) {
            Iterator it = data.iterator();
            while (it.hasNext()) {
                arrayList.add((com.jd.lib.cashier.sdk.d.a.e.a) it.next());
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            com.jd.lib.cashier.sdk.d.a.e.a aVar = (com.jd.lib.cashier.sdk.d.a.e.a) it2.next();
            if (aVar instanceof x) {
                x xVar = (x) aVar;
                xVar.a().selected = Intrinsics.areEqual(xVar.a(), payment);
            } else if ((aVar instanceof com.jd.lib.cashier.sdk.h.g.o) && (list = ((com.jd.lib.cashier.sdk.h.g.o) aVar).a) != null) {
                int size = list.size();
                for (int i2 = 0; i2 < size; i2++) {
                    list.get(i2).selected = Intrinsics.areEqual(list.get(i2), payment);
                }
            }
        }
        CashierPayAdapter cashierPayAdapter2 = this.f3702g;
        if (cashierPayAdapter2 != null) {
            cashierPayAdapter2.y(arrayList);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(@NotNull FragmentActivity fragmentActivity) {
        com.jd.lib.cashier.sdk.b.i.e.c("SEND_UPDATE_PAYMENT_LIST", new a());
    }

    @Nullable
    public final CashierPayAdapter h() {
        return this.f3702g;
    }

    public final void l(@Nullable CashierPayAdapter cashierPayAdapter) {
        this.f3702g = cashierPayAdapter;
    }
}
