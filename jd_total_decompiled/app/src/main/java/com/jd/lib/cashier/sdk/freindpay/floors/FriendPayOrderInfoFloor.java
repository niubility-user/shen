package com.jd.lib.cashier.sdk.freindpay.floors;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoaderOptions;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.h0;
import com.jd.lib.cashier.sdk.core.utils.k;
import com.jd.lib.cashier.sdk.core.utils.m0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.f.c.a;
import com.jd.lib.cashier.sdk.f.f.e;
import com.jd.lib.cashier.sdk.freindpay.bean.WareInfo;

/* loaded from: classes14.dex */
public class FriendPayOrderInfoFloor extends AbstractFloor<a, e> {

    /* renamed from: m  reason: collision with root package name */
    private static final String f3376m = "FriendPayOrderInfoFloor";

    /* renamed from: i  reason: collision with root package name */
    private ImageView f3377i;

    /* renamed from: j  reason: collision with root package name */
    private TextView f3378j;

    /* renamed from: k  reason: collision with root package name */
    private TextView f3379k;

    /* renamed from: l  reason: collision with root package name */
    private TextView f3380l;

    public FriendPayOrderInfoFloor(View view) {
        super(view);
    }

    private void c(String str) {
        if (this.f3377i == null) {
            return;
        }
        ImageLoaderOptions imageLoaderOptions = new ImageLoaderOptions();
        imageLoaderOptions.placeHolderType = 17;
        imageLoaderOptions.showDefault = true;
        k.b(this.f3377i, str, imageLoaderOptions, false);
    }

    private void d() {
        try {
            this.f3378j.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1D1E1E));
            this.f3379k.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
            this.f3380l.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_8C8C8C));
            getConvertView().setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
        } catch (Exception e2) {
            r.d(f3376m, e2.getMessage());
        }
    }

    private void f(String str) {
        TextView textView = this.f3378j;
        if (textView == null) {
            return;
        }
        textView.setText(str);
    }

    private void g(String str) {
        if (this.f3380l == null) {
            return;
        }
        Context context = getConvertView().getContext();
        if (context != null) {
            str = context.getString(R.string.lib_cashier_sdk_friend_pay_order_info_num) + str;
        }
        this.f3380l.setText(str);
        m0.a(this.f3380l, (byte) 3);
    }

    private void h(String str) {
        Context context;
        if (this.f3379k == null || (context = getConvertView().getContext()) == null) {
            return;
        }
        this.f3379k.setText(h0.a(context, str, "\u00a5"));
        m0.a(this.f3379k, (byte) 3);
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public void b(a aVar, e eVar) {
        WareInfo wareInfo;
        if (eVar == null || (wareInfo = eVar.a) == null) {
            return;
        }
        g(wareInfo.count);
        f(eVar.a.wareName);
        c(eVar.a.imageUrl);
        h(eVar.a.price);
        d();
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        this.f3377i = (ImageView) getView(R.id.lib_cashier_friend_pay_order_info_product_pic);
        this.f3378j = (TextView) getView(R.id.lib_cashier_friend_pay_order_info_product_info);
        this.f3379k = (TextView) getView(R.id.lib_cashier_friend_pay_order_info_product_price);
        this.f3380l = (TextView) getView(R.id.lib_cashier_friend_pay_order_info_product_num);
    }
}
