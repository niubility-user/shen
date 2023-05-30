package com.jd.lib.cashier.sdk.freindpay.floors;

import android.view.View;
import android.view.ViewGroup;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamic;
import com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamicListener;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.f.f.c;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class FriendPayDynamicTopFloor extends AbstractFloor<com.jd.lib.cashier.sdk.f.c.a, c> {

    /* renamed from: i */
    private ViewGroup f3374i;

    /* renamed from: j */
    private ViewGroup f3375j;

    /* loaded from: classes14.dex */
    public class a implements IDynamicListener {
        a(FriendPayDynamicTopFloor friendPayDynamicTopFloor) {
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

    public FriendPayDynamicTopFloor(View view) {
        super(view);
    }

    private boolean c(IDynamic iDynamic) {
        return iDynamic != null && (iDynamic.haveCache("pay", "FriendPayTopFloor") || iDynamic.haveBackUp("pay", "FriendPayTopFloor"));
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: d */
    public void b(com.jd.lib.cashier.sdk.f.c.a aVar, c cVar) {
        if (cVar != null) {
            try {
                if (cVar.a != null && this.f3374i != null) {
                    IDynamic dynamic = DependInitializer.getDynamic();
                    JSONObject jSONObject = new JSONObject(DependInitializer.getJsonParser().toJsonString(cVar.a));
                    if (this.f3375j == null && dynamic != null) {
                        this.f3375j = dynamic.createDynamicContainer(this.f3374i.getContext(), "pay", "FriendPayTopFloor", new a(this));
                        this.f3374i.removeAllViews();
                        this.f3374i.addView(this.f3375j);
                        dynamic.asyncLoad(this.f3375j, jSONObject);
                    } else if (c(dynamic)) {
                        dynamic.bindData(this.f3375j, jSONObject);
                    } else if (dynamic != null) {
                        dynamic.asyncLoad(this.f3375j, jSONObject);
                    }
                }
            } catch (Exception e2) {
                r.e("FriendPayDynamicTopFloor", e2.getMessage(), e2);
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f3374i == null) {
            this.f3374i = (ViewGroup) getView(R.id.lib_cashier_friend_pay_dynamic_top_image_floor_root);
        }
    }
}
