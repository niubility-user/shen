package com.jd.lib.cashier.sdk.btcombinationpay.impl;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.btcombinationpay.aac.viewmodel.BtCombinationPayViewModel;
import com.jd.lib.cashier.sdk.btcombinationpay.adapter.BtCombinationPayAdapter;
import com.jd.lib.cashier.sdk.btcombinationpay.bean.BTSkuCalculateEntity;
import com.jd.lib.cashier.sdk.btcombinationpay.bean.SplitSkuInfo;
import com.jd.lib.cashier.sdk.btcombinationpay.ui.BtCombinationErrorView;
import com.jd.lib.cashier.sdk.btcombinationpay.view.BtCombinationPayActivity;
import com.jd.lib.cashier.sdk.core.aac.e;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.m0;
import com.jingdong.common.utils.LangUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class CashierBtCombinationPayImpl implements Object, Observer<com.jd.lib.cashier.sdk.a.a.b.a>, com.jd.lib.cashier.sdk.d.d.a, e, Observer {

    /* renamed from: g  reason: collision with root package name */
    private BtCombinationPayActivity f2885g;

    /* renamed from: h  reason: collision with root package name */
    private RecyclerView f2886h;

    /* renamed from: i  reason: collision with root package name */
    private List<SplitSkuInfo> f2887i;

    /* renamed from: j  reason: collision with root package name */
    private BtCombinationPayAdapter f2888j;

    /* renamed from: k  reason: collision with root package name */
    private ViewGroup f2889k;

    /* renamed from: l  reason: collision with root package name */
    private ViewGroup f2890l;

    /* renamed from: m  reason: collision with root package name */
    private ViewGroup f2891m;

    /* renamed from: n  reason: collision with root package name */
    private ImageView f2892n;
    private TextView o;
    private TextView p;
    private TextView q;
    private TextView r;
    private TextView s;
    private ViewGroup t;
    private ViewGroup u;
    private BtCombinationErrorView v;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (g0.a(CashierBtCombinationPayImpl.this.f2885g)) {
                CashierBtCombinationPayImpl.this.f2885g.onBackPressed();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CashierBtCombinationPayImpl.this.w();
            BtCombinationPayViewModel btCombinationPayViewModel = (BtCombinationPayViewModel) ViewModelProviders.of(CashierBtCombinationPayImpl.this.f2885g).get(BtCombinationPayViewModel.class);
            com.jd.lib.cashier.sdk.a.d.a.b().c(CashierBtCombinationPayImpl.this.f2885g, btCombinationPayViewModel.b().d);
            btCombinationPayViewModel.g().a(CashierBtCombinationPayImpl.this.f2885g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((BtCombinationPayViewModel) ViewModelProviders.of(CashierBtCombinationPayImpl.this.f2885g).get(BtCombinationPayViewModel.class)).g().b(CashierBtCombinationPayImpl.this.f2885g);
        }
    }

    public CashierBtCombinationPayImpl(BtCombinationPayActivity btCombinationPayActivity) {
        this.f2885g = btCombinationPayActivity;
    }

    private void l() {
        r();
        if (this.f2890l != null) {
            if (JDDarkUtil.isDarkMode()) {
                this.f2890l.setBackgroundResource(R.drawable.lib_cashier_sdk_bt_combination_top_corner_dark_bg_grey);
            } else {
                this.f2890l.setBackgroundResource(R.drawable.lib_cashier_sdk_bt_combination_top_corner_bg_grey);
            }
        }
        if (this.f2891m != null) {
            if (JDDarkUtil.isDarkMode()) {
                this.f2891m.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_0A0909, false));
            } else {
                this.f2891m.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2F2F2, false));
            }
        }
        if (this.u != null) {
            if (JDDarkUtil.isDarkMode()) {
                this.u.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_161515, false));
            } else {
                this.u.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F9F9F9, false));
            }
        }
        TextView textView = this.o;
        if (textView != null) {
            textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080, JDDarkUtil.COLOR_848383));
        }
        TextView textView2 = this.p;
        if (textView2 != null) {
            textView2.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080, JDDarkUtil.COLOR_848383));
        }
        TextView textView3 = this.q;
        if (textView3 != null) {
            textView3.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080, JDDarkUtil.COLOR_848383));
        }
        BtCombinationErrorView btCombinationErrorView = this.v;
        if (btCombinationErrorView != null) {
            btCombinationErrorView.onHandModeSkin();
        }
    }

    private void m() {
        RecyclerView recyclerView = this.f2886h;
        if (recyclerView != null && recyclerView.getVisibility() == 0) {
            this.f2886h.setVisibility(8);
        }
        ViewGroup viewGroup = this.u;
        if (viewGroup == null || viewGroup.getVisibility() != 0) {
            return;
        }
        this.u.setVisibility(8);
    }

    private void n() {
        BtCombinationErrorView btCombinationErrorView = this.v;
        if (btCombinationErrorView == null || btCombinationErrorView.getVisibility() != 0) {
            return;
        }
        this.v.setVisibility(8);
    }

    private void o() {
        ViewGroup viewGroup = this.t;
        if (viewGroup == null || viewGroup.getVisibility() != 4) {
            return;
        }
        this.t.setVisibility(8);
    }

    private void p() {
        this.f2887i = new ArrayList();
        this.f2886h.setLayoutManager(new LinearLayoutManager(this.f2885g));
        BtCombinationPayAdapter btCombinationPayAdapter = new BtCombinationPayAdapter(this.f2885g, this.f2887i);
        this.f2888j = btCombinationPayAdapter;
        this.f2886h.setAdapter(btCombinationPayAdapter);
    }

    private void q() {
        this.f2892n.setOnClickListener(new a());
        this.r.setOnClickListener(new b());
        this.v.setErrorButtonListener(new c());
    }

    private void r() {
        if (this.f2889k != null) {
            if (JDDarkUtil.isDarkMode()) {
                this.f2889k.setBackgroundResource(R.drawable.lib_cashier_sdk_bt_combination_top_corner_dark_bg_grey);
            } else {
                this.f2889k.setBackgroundResource(R.drawable.lib_cashier_sdk_bt_combination_top_corner_bg_grey);
            }
        }
        TextView textView = this.s;
        if (textView != null) {
            textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A, JDDarkUtil.COLOR_ECECEC));
        }
    }

    private void t(BTSkuCalculateEntity bTSkuCalculateEntity) {
        if (bTSkuCalculateEntity == null) {
            return;
        }
        String string = TextUtils.isEmpty(bTSkuCalculateEntity.buttonText) ? this.f2885g.getResources().getString(R.string.lib_cashier_sdk_credit_pay_confirm_to_pay_btn_text) : bTSkuCalculateEntity.buttonText;
        this.o.setText(bTSkuCalculateEntity.totalAmountText + LangUtils.SINGLE_SPACE + bTSkuCalculateEntity.totalFeeAmountText);
        m0.a(this.o, (byte) 3);
        this.p.setText(bTSkuCalculateEntity.splitCountText);
        m0.a(this.p, (byte) 3);
        this.r.setText(string);
        this.q.setText(bTSkuCalculateEntity.repayDate);
    }

    private void u() {
        RecyclerView recyclerView = this.f2886h;
        if (recyclerView != null && recyclerView.getVisibility() != 0) {
            this.f2886h.setVisibility(0);
        }
        ViewGroup viewGroup = this.u;
        if (viewGroup == null || viewGroup.getVisibility() == 0) {
            return;
        }
        this.u.setVisibility(0);
    }

    private void v() {
        BtCombinationErrorView btCombinationErrorView = this.v;
        if (btCombinationErrorView == null || btCombinationErrorView.getVisibility() == 0) {
            return;
        }
        this.v.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        ViewGroup viewGroup = this.t;
        if (viewGroup == null || viewGroup.getVisibility() != 8) {
            return;
        }
        this.t.setVisibility(4);
    }

    private void x() {
        BtCombinationPayAdapter btCombinationPayAdapter = this.f2888j;
        if (btCombinationPayAdapter != null) {
            if (btCombinationPayAdapter.getDataList() != null && this.f2887i != null) {
                this.f2888j.getDataList().clear();
                if (this.f2887i != null) {
                    this.f2888j.getDataList().addAll(this.f2887i);
                }
            }
            this.f2888j.notifyDataSetChanged();
        }
    }

    public void f(FragmentActivity fragmentActivity) {
        if (g0.a(this.f2885g)) {
            ((BtCombinationPayViewModel) ViewModelProviders.of(fragmentActivity).get(BtCombinationPayViewModel.class)).f().observe(fragmentActivity, this);
        }
    }

    public void h(Window window) {
        if (window == null) {
            return;
        }
        this.f2889k = (ViewGroup) window.findViewById(R.id.lib_cashier_bt_combination_pay_frame_root);
        this.f2890l = (ViewGroup) window.findViewById(R.id.lib_cashier_bt_combination_pay_top_layout);
        this.f2891m = (ViewGroup) window.findViewById(R.id.lib_cashier_bt_combination_pay_content_layout);
        this.f2886h = (RecyclerView) window.findViewById(R.id.lib_cashier_bt_combination_pay_recyclerview);
        this.f2892n = (ImageView) window.findViewById(R.id.lib_cashier_bt_combination_pay_close);
        this.s = (TextView) window.findViewById(R.id.lib_cashier_bt_combination_pay_title_tv);
        this.o = (TextView) window.findViewById(R.id.lib_cashier_bt_combination_pay_price);
        this.p = (TextView) window.findViewById(R.id.lib_cashier_bt_combination_pay_count);
        this.r = (TextView) window.findViewById(R.id.lib_cashier_bt_combination_pay_confirm_button);
        this.q = (TextView) window.findViewById(R.id.lib_cashier_bt_combination_pay_repayment);
        this.u = (ViewGroup) window.findViewById(R.id.lib_cashier_bt_combination_pay_bottom_layout);
        this.t = (ViewGroup) window.findViewById(R.id.lib_cashier_bt_combination_pay_state_loading);
        this.v = (BtCombinationErrorView) window.findViewById(R.id.lib_cashier_bt_combination_pay_error_view_root);
        r();
        p();
        q();
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        l();
        BtCombinationPayAdapter btCombinationPayAdapter = this.f2888j;
        if (btCombinationPayAdapter != null) {
            btCombinationPayAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f2885g != null) {
            this.f2885g = null;
        }
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: s  reason: merged with bridge method [inline-methods] */
    public void onChanged(com.jd.lib.cashier.sdk.a.a.b.a aVar) {
        if (aVar == null) {
            return;
        }
        int i2 = aVar.a;
        if (1000 == i2) {
            u();
            n();
            o();
            t(aVar.f2790c);
            BTSkuCalculateEntity bTSkuCalculateEntity = aVar.f2790c;
            this.f2887i = bTSkuCalculateEntity == null ? null : bTSkuCalculateEntity.splitSkuPlanInfoVoList;
            x();
            l();
        } else if (1003 == i2) {
            o();
        } else if (1004 == i2) {
            if (g0.a(this.f2885g)) {
                this.f2885g.onBackPressed();
            }
        } else if (1002 == i2) {
            BtCombinationErrorView btCombinationErrorView = this.v;
            if (btCombinationErrorView != null) {
                btCombinationErrorView.setMegText(aVar.b);
            }
            v();
            o();
            m();
        } else if (1005 == i2 && g0.a(this.f2885g)) {
            w();
        }
    }
}
