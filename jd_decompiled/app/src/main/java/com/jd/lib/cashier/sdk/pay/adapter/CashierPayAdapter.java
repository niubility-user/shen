package com.jd.lib.cashier.sdk.pay.adapter;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.core.commonfloor.adapter.AbstractFloorAdapter;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.h.b.q;
import com.jd.lib.cashier.sdk.h.b.r;
import com.jd.lib.cashier.sdk.pay.aac.impl.channel.AbsCashierPayItemDecorationProxy;
import com.jd.lib.cashier.sdk.pay.aac.impl.channel.j;
import com.jd.lib.cashier.sdk.pay.aac.impl.channel.n;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.floors.CashierPayEmptyFloor;
import com.jd.lib.cashier.sdk.pay.recyclerview.CustomDrawOrderRecyclerView;
import com.jd.lib.cashier.sdk.pay.recyclerview.GridLayoutCBRCSpanSizeLookUp;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class CashierPayAdapter extends AbstractFloorAdapter<com.jd.lib.cashier.sdk.h.d.a> {
    private FragmentActivity B;
    private GridLayoutCBRCSpanSizeLookUp C;
    private final CustomDrawOrderRecyclerView D;
    private AbsCashierPayItemDecorationProxy E;
    private com.jd.lib.cashier.sdk.pay.recyclerview.a F;
    private com.jd.lib.cashier.sdk.pay.aac.impl.c G;

    public CashierPayAdapter(FragmentActivity fragmentActivity, CustomDrawOrderRecyclerView customDrawOrderRecyclerView, com.jd.lib.cashier.sdk.h.d.a aVar, List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        super(fragmentActivity, aVar, list);
        this.B = fragmentActivity;
        this.D = customDrawOrderRecyclerView;
        u(customDrawOrderRecyclerView);
        s(fragmentActivity, customDrawOrderRecyclerView);
        t(fragmentActivity);
    }

    private void r(List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        com.jd.lib.cashier.sdk.pay.aac.impl.c cVar = this.G;
        if (cVar != null) {
            cVar.c(list);
        }
    }

    private void s(FragmentActivity fragmentActivity, RecyclerView recyclerView) {
        this.F = new com.jd.lib.cashier.sdk.pay.recyclerview.a(fragmentActivity, recyclerView);
    }

    private void t(FragmentActivity fragmentActivity) {
        if (fragmentActivity instanceof CashierPayActivity) {
            this.G = new com.jd.lib.cashier.sdk.pay.aac.impl.c((CashierPayActivity) fragmentActivity);
        }
    }

    private void u(RecyclerView recyclerView) {
        this.C = new GridLayoutCBRCSpanSizeLookUp();
        if (recyclerView == null || !(recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
            return;
        }
        ((GridLayoutManager) recyclerView.getLayoutManager()).setSpanSizeLookup(this.C);
    }

    private void v(List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(this.B).get(CashierPayViewModel.class);
        AbsCashierPayItemDecorationProxy absCashierPayItemDecorationProxy = this.E;
        if (absCashierPayItemDecorationProxy != null) {
            absCashierPayItemDecorationProxy.f();
        }
        if (cashierPayViewModel.b().E) {
            this.E = new j(this.D);
        } else if (cashierPayViewModel.b().F) {
            this.E = new com.jd.lib.cashier.sdk.pay.aac.impl.channel.d(this.D);
        } else {
            this.E = new n(this.D);
        }
        this.E.b(list);
    }

    private void w(List<com.jd.lib.cashier.sdk.d.a.e.a> list, boolean z, boolean z2) {
        com.jd.lib.cashier.sdk.pay.recyclerview.a aVar = this.F;
        if (aVar != null) {
            aVar.f(list, z, z2);
        }
    }

    private void x(List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        GridLayoutCBRCSpanSizeLookUp gridLayoutCBRCSpanSizeLookUp = this.C;
        if (gridLayoutCBRCSpanSizeLookUp != null) {
            gridLayoutCBRCSpanSizeLookUp.b(list);
        }
    }

    public void A(List<com.jd.lib.cashier.sdk.d.a.e.a> list, boolean z, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(list);
        x(list);
        setNewData(arrayList);
        r(list);
        w(list, z, z2);
        notifyDataSetChanged();
    }

    public void B(List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        C(list, true);
    }

    public void C(List<com.jd.lib.cashier.sdk.d.a.e.a> list, boolean z) {
        z(list, z);
        v(list);
    }

    public void D(List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(list);
        x(list);
        setNewData(arrayList);
        notifyDataSetChanged();
        v(list);
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.adapter.AbstractFloorAdapter
    public AbstractFloor n(View view) {
        return new CashierPayEmptyFloor(view);
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.adapter.AbstractFloorAdapter
    public com.jd.lib.cashier.sdk.d.a.b.a<com.jd.lib.cashier.sdk.h.d.a> o() {
        return new q();
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.adapter.AbstractFloorAdapter, com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        super.onDestroy();
        if (this.B != null) {
            this.B = null;
        }
        com.jd.lib.cashier.sdk.pay.aac.impl.c cVar = this.G;
        if (cVar != null) {
            cVar.onDestroy();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.adapter.AbstractFloorAdapter
    public com.jd.lib.cashier.sdk.d.a.b.b p() {
        return new r();
    }

    public void y(List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        z(list, false);
    }

    public void z(List<com.jd.lib.cashier.sdk.d.a.e.a> list, boolean z) {
        A(list, z, false);
    }
}
