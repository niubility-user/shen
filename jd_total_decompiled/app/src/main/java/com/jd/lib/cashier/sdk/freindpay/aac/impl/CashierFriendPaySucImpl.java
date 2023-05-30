package com.jd.lib.cashier.sdk.freindpay.aac.impl;

import android.view.Window;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.layoutmanager.WrapContentLinearLayoutManager;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.freindpay.aac.viewmodel.FriendPayViewModel;
import com.jd.lib.cashier.sdk.freindpay.adapter.FriendPayAdapter;
import com.jd.lib.cashier.sdk.freindpay.view.FriendPayActivity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class CashierFriendPaySucImpl implements b, Observer<com.jd.lib.cashier.sdk.f.a.b.b> {

    /* renamed from: g */
    private RecyclerView f3365g;

    /* renamed from: h */
    private FriendPayActivity f3366h;

    /* renamed from: i */
    private FriendPayAdapter f3367i;

    /* renamed from: j */
    private List<com.jd.lib.cashier.sdk.d.a.e.a> f3368j = new ArrayList();

    public CashierFriendPaySucImpl(FriendPayActivity friendPayActivity) {
        this.f3366h = friendPayActivity;
    }

    private void a(List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        List<com.jd.lib.cashier.sdk.d.a.e.a> list2 = this.f3368j;
        if (list2 == null || this.f3365g == null) {
            return;
        }
        list2.clear();
        this.f3368j.addAll(list);
        FriendPayAdapter friendPayAdapter = new FriendPayAdapter(this.f3366h, l(), this.f3368j);
        this.f3367i = friendPayAdapter;
        friendPayAdapter.setEnableLoadMore(false);
        this.f3365g.setAdapter(this.f3367i);
    }

    private void c(List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        if (this.f3367i == null) {
            a(list);
        } else {
            n(list);
        }
    }

    private com.jd.lib.cashier.sdk.f.c.a l() {
        if (g0.a(this.f3366h)) {
            return ((FriendPayViewModel) ViewModelProviders.of(this.f3366h).get(FriendPayViewModel.class)).b().c();
        }
        return null;
    }

    private void m() {
        j0.b(this.f3365g);
    }

    private void n(List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        if (this.f3367i != null) {
            this.f3368j.clear();
            this.f3368j.addAll(list);
            this.f3367i.notifyDataSetChanged();
        }
    }

    private void q() {
        j0.d(this.f3365g);
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            ((FriendPayViewModel) ViewModelProviders.of(fragmentActivity).get(FriendPayViewModel.class)).g().observe(fragmentActivity, this);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.c
    public void h(Window window) {
        if (window != null) {
            WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(this.f3366h);
            wrapContentLinearLayoutManager.setOrientation(1);
            RecyclerView recyclerView = (RecyclerView) window.findViewById(R.id.lib_cashier_friend_pay_recycler_view);
            this.f3365g = recyclerView;
            recyclerView.setLayoutManager(wrapContentLinearLayoutManager);
            window.getDecorView().setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
        }
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: o */
    public void onChanged(@Nullable com.jd.lib.cashier.sdk.f.a.b.b bVar) {
        p(bVar);
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        List<com.jd.lib.cashier.sdk.d.a.e.a> list;
        RecyclerView recyclerView = this.f3365g;
        if (recyclerView != null) {
            recyclerView.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
        }
        ArrayList arrayList = new ArrayList(this.f3368j);
        if (this.f3367i == null || (list = this.f3368j) == null) {
            return;
        }
        list.clear();
        this.f3368j.addAll(arrayList);
        this.f3367i.notifyDataSetChanged();
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f3366h != null) {
            this.f3366h = null;
        }
        if (this.f3365g != null) {
            this.f3365g = null;
        }
        FriendPayAdapter friendPayAdapter = this.f3367i;
        if (friendPayAdapter != null) {
            friendPayAdapter.onDestroy();
            this.f3367i = null;
        }
        List<com.jd.lib.cashier.sdk.d.a.e.a> list = this.f3368j;
        if (list != null) {
            list.clear();
            this.f3368j = null;
        }
    }

    public void p(com.jd.lib.cashier.sdk.f.a.b.b bVar) {
        if (bVar != null) {
            int i2 = bVar.a;
            if (i2 == 0) {
                q();
                c(bVar.b);
                return;
            } else if (i2 != 8) {
                return;
            } else {
                m();
                return;
            }
        }
        m();
    }
}
