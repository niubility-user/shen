package com.jd.lib.cashier.sdk.freindpay.floors;

import android.view.View;
import android.widget.LinearLayout;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.f.c.a;
import com.jd.lib.cashier.sdk.f.f.j;

/* loaded from: classes14.dex */
public class FriendPaySplitLineFloor extends AbstractFloor<a, j> {

    /* renamed from: i  reason: collision with root package name */
    private LinearLayout f3400i;

    /* renamed from: j  reason: collision with root package name */
    private View f3401j;

    /* renamed from: k  reason: collision with root package name */
    private View f3402k;

    public FriendPaySplitLineFloor(View view) {
        super(view);
    }

    private void d() {
        try {
            this.f3400i.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2F2F2));
            if (JDDarkUtil.isDarkMode()) {
                this.f3401j.setBackgroundResource(R.drawable.lib_cashier_sdk_top_corner_dark_bg);
                this.f3402k.setBackgroundResource(R.drawable.lib_cashier_sdk_bottom_corner_dark_bg);
            } else {
                this.f3401j.setBackgroundResource(R.drawable.lib_cashier_sdk_top_corner_bg);
                this.f3402k.setBackgroundResource(R.drawable.lib_cashier_sdk_bottom_corner_bg);
            }
        } catch (Exception e2) {
            r.d("FriendPaySplitLineFloor", e2.getMessage());
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public void b(a aVar, j jVar) {
        d();
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        this.f3400i = (LinearLayout) getView(R.id.lib_cashier_friend_pay_split_line_root);
        this.f3401j = getView(R.id.lib_cashier_friend_pay_split_line_top);
        this.f3402k = getView(R.id.lib_cashier_friend_pay_split_line_bottom);
    }
}
