package com.jd.lib.cashier.sdk.freindpay.floors;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.f.c.a;
import com.jd.lib.cashier.sdk.f.f.f;

/* loaded from: classes14.dex */
public class FriendPayOrderTitleFloor extends AbstractFloor<a, f> {

    /* renamed from: i  reason: collision with root package name */
    private TextView f3381i;

    public FriendPayOrderTitleFloor(View view) {
        super(view);
    }

    private void d() {
        try {
            this.f3381i.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
            getConvertView().setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
        } catch (Exception e2) {
            r.d("FriendPayOrderTitleFloor", e2.getMessage());
        }
    }

    private void f(String str) {
        if (this.f3381i == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.f3381i.setText(str);
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public void b(a aVar, f fVar) {
        f(fVar.a);
        d();
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        this.f3381i = (TextView) getView(R.id.lib_cashier_friend_pay_order_info_title);
    }
}
