package com.jd.lib.cashier.sdk.freindpay.adapter;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.core.commonfloor.adapter.AbstractFloorAdapter;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.f.b.b;
import com.jd.lib.cashier.sdk.f.b.d;
import com.jd.lib.cashier.sdk.f.c.a;
import com.jd.lib.cashier.sdk.freindpay.floors.FriendPayEmptyFloor;
import java.util.List;

/* loaded from: classes14.dex */
public class FriendPayAdapter extends AbstractFloorAdapter<a> {
    public FriendPayAdapter(FragmentActivity fragmentActivity, a aVar, List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        super(fragmentActivity, aVar, list);
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.adapter.AbstractFloorAdapter
    public AbstractFloor n(View view) {
        return new FriendPayEmptyFloor(view);
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.adapter.AbstractFloorAdapter
    public com.jd.lib.cashier.sdk.d.a.b.a<a> o() {
        return new b();
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.adapter.AbstractFloorAdapter
    public com.jd.lib.cashier.sdk.d.a.b.b p() {
        return new d();
    }
}
