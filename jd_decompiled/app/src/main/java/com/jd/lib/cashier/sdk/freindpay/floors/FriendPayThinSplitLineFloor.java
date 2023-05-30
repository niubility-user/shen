package com.jd.lib.cashier.sdk.freindpay.floors;

import android.view.View;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.f.c.a;
import com.jd.lib.cashier.sdk.f.f.k;

/* loaded from: classes14.dex */
public class FriendPayThinSplitLineFloor extends AbstractFloor<a, k> {

    /* renamed from: i  reason: collision with root package name */
    private View f3403i;

    public FriendPayThinSplitLineFloor(View view) {
        super(view);
    }

    private void d() {
        try {
            this.f3403i.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2F2F2));
        } catch (Exception e2) {
            r.d("FriendPaySplitLineFloor", e2.getMessage());
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public void b(a aVar, k kVar) {
        d();
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        this.f3403i = view.findViewById(R.id.lib_cashier_friend_pay_thin_split_line);
    }
}
