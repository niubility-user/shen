package com.jd.lib.productdetail.tradein.g;

import android.os.Bundle;
import android.view.View;
import com.google.gson.JsonObject;
import com.jd.lib.productdetail.tradein.TradeInStep;
import com.jd.lib.productdetail.tradein.TradeInViewModel;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceDeviceAdapter;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceFragment;

/* loaded from: classes16.dex */
public class b implements View.OnClickListener {

    /* renamed from: g */
    public final /* synthetic */ TradeInSelectDeviceData.Data.devicesInfo.OwnedDevice f5356g;

    /* renamed from: h */
    public final /* synthetic */ TradeInSelectDeviceDeviceAdapter.d f5357h;

    public b(TradeInSelectDeviceDeviceAdapter.d dVar, TradeInSelectDeviceData.Data.devicesInfo.OwnedDevice ownedDevice) {
        this.f5357h = dVar;
        this.f5356g = ownedDevice;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        TradeInSelectDeviceDeviceAdapter.a aVar = TradeInSelectDeviceDeviceAdapter.this.a;
        if (aVar != null) {
            TradeInSelectDeviceData.Data.devicesInfo.OwnedDevice ownedDevice = this.f5356g;
            f fVar = (f) aVar;
            TradeInViewModel tradeInViewModel = fVar.a.f5503g;
            if (tradeInViewModel == null || ownedDevice == null) {
                return;
            }
            tradeInViewModel.u.setValue(null);
            Bundle bundle = new Bundle();
            TradeInSelectDeviceFragment tradeInSelectDeviceFragment = fVar.a;
            tradeInSelectDeviceFragment.f5503g.f5262n = ownedDevice.deviceId;
            tradeInSelectDeviceFragment.f5504h.moveToStep(TradeInStep.TRADEIN, bundle);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("es_id", fVar.a.f5503g.f5262n);
            fVar.a.f5503g.e("Productdetail_yjhxChooseToastHaveOld", jsonObject);
        }
    }
}
