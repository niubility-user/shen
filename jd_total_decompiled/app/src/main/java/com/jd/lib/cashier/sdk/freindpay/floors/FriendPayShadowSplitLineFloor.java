package com.jd.lib.cashier.sdk.freindpay.floors;

import android.view.View;
import android.widget.LinearLayout;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.f.c.a;
import com.jd.lib.cashier.sdk.f.f.h;

/* loaded from: classes14.dex */
public class FriendPayShadowSplitLineFloor extends AbstractFloor<a, h> {

    /* renamed from: i  reason: collision with root package name */
    private View f3388i;

    /* renamed from: j  reason: collision with root package name */
    private LinearLayout f3389j;

    public FriendPayShadowSplitLineFloor(View view) {
        super(view);
    }

    private void d() {
        try {
            if (JDDarkUtil.isDarkMode()) {
                this.f3389j.setBackgroundColor(-16777216);
                this.f3388i.setBackgroundResource(R.drawable.lib_cashier_sdk_bottom_corner_dark_bg);
            } else {
                this.f3389j.setBackgroundResource(R.drawable.lib_cashier_sdk_friend_pay_shadow_split_line);
                this.f3388i.setBackgroundColor(0);
            }
        } catch (Exception e2) {
            r.d("FriendPayShadowSplitLineFloor", e2.getMessage());
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public void b(a aVar, h hVar) {
        d();
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        this.f3389j = (LinearLayout) getView(R.id.lib_cashier_friend_pay_shadow_split_line_root);
        this.f3388i = getView(R.id.lib_cashier_friend_pay_shadow_bottom_corner);
    }
}
