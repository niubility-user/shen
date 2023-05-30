package com.jd.lib.cashier.sdk.freindpay.floors;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.h0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.f.c.a;
import java.util.List;

/* loaded from: classes14.dex */
public class FriendPayShareActionFloor extends AbstractFloor<a, com.jd.lib.cashier.sdk.f.f.a> {

    /* renamed from: i  reason: collision with root package name */
    private TextView f3390i;

    /* renamed from: j  reason: collision with root package name */
    private LinearLayout f3391j;

    public FriendPayShareActionFloor(View view) {
        super(view);
    }

    private void d() {
        try {
            this.f3390i.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
            getConvertView().setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
        } catch (Exception e2) {
            r.d("FriendPayShareActionFloor", e2.getMessage());
        }
    }

    private void f(List<String> list) {
        Context context;
        if (this.f3391j == null || (context = getConvertView().getContext()) == null) {
            return;
        }
        this.f3391j.removeAllViews();
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            TextView textView = new TextView(context);
            textView.setSingleLine(false);
            textView.setGravity(3);
            h0.f(textView, 2, 11.0f);
            h0.e(textView, list.get(i2));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            if (i2 == 0) {
                layoutParams.setMargins(0, DpiUtil.dip2px(12.0f), 0, 0);
            } else {
                layoutParams.setMargins(0, DpiUtil.dip2px(6.0f), 0, 0);
            }
            textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_8C8C8C));
            this.f3391j.addView(textView, layoutParams);
        }
    }

    private void g(String str) {
        TextView textView = this.f3390i;
        if (textView == null) {
            return;
        }
        textView.setText(str);
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public void b(a aVar, com.jd.lib.cashier.sdk.f.f.a aVar2) {
        if (aVar2 != null) {
            g(aVar2.a);
            f(aVar2.b);
            d();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        this.f3390i = (TextView) getView(R.id.lib_cashier_friend_pay_action_des_title_label);
        this.f3391j = (LinearLayout) getView(R.id.lib_cashier_friend_pay_action_des_container);
    }
}
