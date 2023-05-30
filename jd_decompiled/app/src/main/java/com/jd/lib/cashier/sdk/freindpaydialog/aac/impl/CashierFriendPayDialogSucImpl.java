package com.jd.lib.cashier.sdk.freindpaydialog.aac.impl;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.layoutmanager.WrapContentLinearLayoutManager;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.freindpaydialog.aac.viewmodel.FriendPayDialogViewModel;
import com.jd.lib.cashier.sdk.freindpaydialog.adapter.FriendPayDialogFloorAdapter;
import com.jd.lib.cashier.sdk.freindpaydialog.view.FriendPayDialogActivity;
import com.jd.lib.cashier.sdk.g.g.c;
import java.util.List;

/* loaded from: classes14.dex */
public class CashierFriendPayDialogSucImpl implements b, Observer<com.jd.lib.cashier.sdk.g.a.b.b> {

    /* renamed from: g */
    private ViewGroup f3419g;

    /* renamed from: h */
    private ViewGroup f3420h;

    /* renamed from: i */
    private RecyclerView f3421i;

    /* renamed from: j */
    private ImageView f3422j;

    /* renamed from: k */
    private TextView f3423k;

    /* renamed from: l */
    private FriendPayDialogActivity f3424l;

    /* renamed from: m */
    private FriendPayDialogFloorAdapter f3425m;

    /* renamed from: n */
    private com.jd.lib.cashier.sdk.g.a.b.b f3426n;
    private com.jd.lib.cashier.sdk.g.d.a o;

    /* loaded from: classes14.dex */
    class a implements View.OnClickListener {
        a() {
            CashierFriendPayDialogSucImpl.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (CashierFriendPayDialogSucImpl.this.f3424l != null) {
                CashierFriendPayDialogSucImpl.this.f3424l.onBackPressed();
            }
        }
    }

    public CashierFriendPayDialogSucImpl(FriendPayDialogActivity friendPayDialogActivity) {
        this.f3424l = friendPayDialogActivity;
        o();
    }

    private void c() {
        if (this.f3419g != null) {
            if (JDDarkUtil.isDarkMode()) {
                this.f3423k.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
                this.f3419g.setBackgroundResource(R.drawable.lib_cashier_sdk_top_corner_dark_bg_grey);
            } else {
                this.f3423k.setTextColor(Color.parseColor(JDDarkUtil.COLOR_1A1A1A));
                this.f3419g.setBackgroundResource(R.drawable.lib_cashier_sdk_top_corner_bg_grey);
            }
        }
        com.jd.lib.cashier.sdk.g.a.b.b bVar = this.f3426n;
        if (bVar == null || bVar.b == null) {
            return;
        }
        l(bVar);
    }

    private void l(com.jd.lib.cashier.sdk.g.a.b.b bVar) {
        if (bVar == null || bVar.b == null) {
            return;
        }
        this.f3426n = bVar;
        this.f3421i.setLayoutManager(new LinearLayoutManager(this.f3424l));
        FriendPayDialogFloorAdapter friendPayDialogFloorAdapter = new FriendPayDialogFloorAdapter(this.f3424l, bVar.b);
        this.f3425m = friendPayDialogFloorAdapter;
        this.f3421i.setAdapter(friendPayDialogFloorAdapter);
        this.o.d(m(bVar.b));
    }

    private c m(List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        c cVar = new c();
        if (list == null || list.size() <= 0) {
            return cVar;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2).getItemType() == 1004) {
                return (c) list.get(i2);
            }
        }
        return cVar;
    }

    private void n() {
        j0.b(this.f3421i);
        j0.b(this.f3420h);
    }

    private void o() {
        this.o = new com.jd.lib.cashier.sdk.g.d.a(this.f3424l);
    }

    private void r() {
        j0.d(this.f3421i);
        j0.d(this.f3420h);
        com.jd.lib.cashier.sdk.g.e.a.e(this.f3424l);
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            ((FriendPayDialogViewModel) ViewModelProviders.of(fragmentActivity).get(FriendPayDialogViewModel.class)).h().observe(fragmentActivity, this);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.c
    public void h(Window window) {
        if (window != null) {
            new WrapContentLinearLayoutManager(this.f3424l).setOrientation(1);
            this.f3421i = (RecyclerView) window.findViewById(R.id.lib_cashier_friend_pay_dialog_recycler);
            this.f3420h = (ViewGroup) window.findViewById(R.id.lib_cashier_friend_pay_dialog_share_button);
            this.f3419g = (ViewGroup) window.findViewById(R.id.lib_cashier_friend_pay_dialog_frame_root);
            this.f3423k = (TextView) window.findViewById(R.id.lib_cashier_friend_pay_dialog_title_tv);
            ImageView imageView = (ImageView) window.findViewById(R.id.lib_cashier_friend_pay_dialog_close);
            this.f3422j = imageView;
            imageView.setOnClickListener(new a());
            c();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        c();
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        List<com.jd.lib.cashier.sdk.d.a.e.a> list;
        if (this.f3424l != null) {
            this.f3424l = null;
        }
        com.jd.lib.cashier.sdk.g.a.b.b bVar = this.f3426n;
        if (bVar == null || (list = bVar.b) == null) {
            return;
        }
        list.clear();
        this.f3426n = null;
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: p */
    public void onChanged(@Nullable com.jd.lib.cashier.sdk.g.a.b.b bVar) {
        q(bVar);
        c();
    }

    public void q(com.jd.lib.cashier.sdk.g.a.b.b bVar) {
        if (bVar != null) {
            int i2 = bVar.a;
            if (i2 == 0) {
                r();
                l(bVar);
                return;
            } else if (i2 != 8) {
                return;
            } else {
                n();
                return;
            }
        }
        n();
    }
}
