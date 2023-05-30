package com.jd.lib.productdetail.tradein.g;

import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.JsonObject;
import com.jd.dynamic.DYConstants;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceFragment;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;

/* loaded from: classes16.dex */
public class c implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem f5358g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ TradeInSelectDeviceFragment.g f5359h;

    public c(TradeInSelectDeviceFragment.g gVar, TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem cateItem) {
        this.f5359h = gVar;
        this.f5358g = cateItem;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        RecyclerView recyclerView = TradeInSelectDeviceFragment.this.r;
        if (recyclerView == null || recyclerView.getVisibility() == 0) {
            return;
        }
        if (TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDProductdetail", "tradeinselectdeviceupdate", "enable", DYConstants.DY_FALSE), DYConstants.DY_TRUE)) {
            TradeInSelectDeviceFragment tradeInSelectDeviceFragment = TradeInSelectDeviceFragment.this;
            tradeInSelectDeviceFragment.e(tradeInSelectDeviceFragment.t(), null);
        } else {
            TradeInSelectDeviceFragment.j(TradeInSelectDeviceFragment.this, false, true, false);
        }
        if (TradeInSelectDeviceFragment.this.f5503g != null) {
            JsonObject jsonObject = new JsonObject();
            if (this.f5358g != null) {
                jsonObject.addProperty("cid3", this.f5358g.categoryId + "");
            }
            TradeInSelectDeviceFragment.this.f5503g.e("Productdetail_yjhxChooseoldCid", jsonObject);
        }
    }
}
