package com.jd.lib.cashier.sdk.freindpay.floors;

import android.view.View;
import android.view.ViewGroup;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamic;
import com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamicListener;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.f.f.b;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class FriendPayDynamicPDInfoFloor extends AbstractFloor<com.jd.lib.cashier.sdk.f.c.a, b> {

    /* renamed from: i */
    private ViewGroup f3372i;

    /* renamed from: j */
    private ViewGroup f3373j;

    /* loaded from: classes14.dex */
    public class a implements IDynamicListener {
        a(FriendPayDynamicPDInfoFloor friendPayDynamicPDInfoFloor) {
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamicListener
        public void onCreate() {
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamicListener
        public void onLoad() {
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamicListener
        public void onRefresh() {
        }
    }

    public FriendPayDynamicPDInfoFloor(View view) {
        super(view);
    }

    private boolean c(IDynamic iDynamic) {
        return iDynamic != null && (iDynamic.haveCache("pay", "FriendPayPDInfoFloor") || iDynamic.haveBackUp("pay", "FriendPayPDInfoFloor"));
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: d */
    public void b(com.jd.lib.cashier.sdk.f.c.a aVar, b bVar) {
        if (bVar != null) {
            try {
                if (bVar.a != null && this.f3372i != null) {
                    IDynamic dynamic = DependInitializer.getDynamic();
                    JSONObject jSONObject = new JSONObject(DependInitializer.getJsonParser().toJsonString(bVar.a));
                    if (this.f3373j == null && dynamic != null) {
                        this.f3373j = dynamic.createDynamicContainer(this.f3372i.getContext(), "pay", "FriendPayPDInfoFloor", new a(this));
                        this.f3372i.removeAllViews();
                        this.f3372i.addView(this.f3373j);
                        dynamic.asyncLoad(this.f3373j, jSONObject);
                    } else if (c(dynamic)) {
                        dynamic.bindData(this.f3373j, jSONObject);
                    } else if (dynamic != null) {
                        dynamic.asyncLoad(this.f3373j, jSONObject);
                    }
                }
            } catch (Exception e2) {
                r.e("FriendPayDynamicOrderInfoFloor", e2.getMessage(), e2);
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f3372i == null) {
            this.f3372i = (ViewGroup) getView(R.id.lib_cashier_friend_pay_dynamic_order_info_floor_root);
        }
    }
}
