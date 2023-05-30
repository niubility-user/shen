package com.jd.lib.cashier.sdk.h.a.a.l;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public abstract class d extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.h.f.d, CashierPayEntity> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(IHttpSetting iHttpSetting, com.jd.lib.cashier.sdk.h.f.d dVar) {
        if (iHttpSetting == null || dVar == null) {
            return;
        }
        iHttpSetting.setEffect(0);
        if (!TextUtils.isEmpty(dVar.groupOrders)) {
            iHttpSetting.setFunctionId("platGroupPayChannel");
        } else {
            iHttpSetting.setFunctionId("platPayChannel");
        }
        iHttpSetting.putJsonParam("hasUPPay", dVar.f3546j);
        iHttpSetting.putJsonParam("hasOCPay", dVar.f3547k);
        iHttpSetting.putJsonParam("hasCyberMoneyPay", dVar.f3548l);
        iHttpSetting.putJsonParam("supportNFC", dVar.f3549m);
        iHttpSetting.putJsonParam("hasHuaweiPay", dVar.f3550n);
        iHttpSetting.putJsonParam("hasAndroidPay", dVar.o);
        if (!TextUtils.isEmpty(dVar.z)) {
            iHttpSetting.putJsonParam("payUrl", dVar.z);
        }
        if (!TextUtils.isEmpty(dVar.s)) {
            iHttpSetting.putJsonParam("unJieSuan", dVar.s);
        }
        if (!TextUtils.isEmpty(dVar.t)) {
            iHttpSetting.putJsonParam("baiTiaoNum", dVar.t);
        }
        if (!TextUtils.isEmpty(dVar.w)) {
            iHttpSetting.putJsonParam("isGoodsDetailBaiTiaoFlag", dVar.w);
        }
        if (!TextUtils.isEmpty(dVar.x)) {
            iHttpSetting.putJsonParam(DeeplinkProductDetailHelper.LAYER_STYLE, dVar.x);
        }
        if (!TextUtils.isEmpty(dVar.u)) {
            iHttpSetting.putJsonParam("businessTag", dVar.u);
        }
        if (!TextUtils.isEmpty(dVar.v)) {
            try {
                dVar.v = URLDecoder.decode(dVar.v, "UTF-8");
                iHttpSetting.putJsonParam("submitOrderExtFlag", new JSONObject(dVar.v));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (!TextUtils.isEmpty(dVar.a)) {
            iHttpSetting.putJsonParam("payablePrice", dVar.a);
        }
        if (!TextUtils.isEmpty(dVar.b)) {
            iHttpSetting.putJsonParam("paySourceId", dVar.b);
        }
        if (!TextUtils.isEmpty(dVar.f3540c)) {
            iHttpSetting.putJsonParam("back_url", dVar.f3540c);
        }
        if (!TextUtils.isEmpty(dVar.y)) {
            iHttpSetting.putJsonParam("dfPinIgnoreFlag", dVar.y);
        }
        if (!TextUtils.isEmpty(dVar.p)) {
            iHttpSetting.putJsonParam("graduallyPayFlag", dVar.p);
        }
        if (!TextUtils.isEmpty(dVar.q)) {
            iHttpSetting.putJsonParam("graduallyPayAmount", dVar.q);
        }
        if (!TextUtils.isEmpty(dVar.r)) {
            iHttpSetting.putJsonParam("sdkToken", dVar.r);
        }
        if (!TextUtils.isEmpty(dVar.f3545i)) {
            iHttpSetting.putJsonParam("fk_aid", dVar.f3545i);
        }
        if (!TextUtils.isEmpty(dVar.d)) {
            iHttpSetting.putJsonParam("fk_appId", dVar.d);
        }
        if (!TextUtils.isEmpty(dVar.f3543g)) {
            iHttpSetting.putJsonParam("fk_longtitude", dVar.f3543g);
        }
        if (!TextUtils.isEmpty(dVar.f3544h)) {
            iHttpSetting.putJsonParam("fk_latitude", dVar.f3544h);
        }
        if (!TextUtils.isEmpty(dVar.f3541e)) {
            iHttpSetting.putJsonParam("fk_traceIp", dVar.f3541e);
        }
        if (!TextUtils.isEmpty(dVar.f3542f)) {
            iHttpSetting.putJsonParam("fk_terminalType", dVar.f3542f);
        }
        if (!TextUtils.isEmpty(dVar.A)) {
            iHttpSetting.putJsonParam("from", dVar.A);
        }
        Map<String, List<String>> map = dVar.B;
        if (map == null || map.isEmpty()) {
            return;
        }
        iHttpSetting.putJsonParam("webViewUrlsDic", dVar.B);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public CashierPayEntity d(String str) {
        CashierPayEntity cashierPayEntity = !TextUtils.isEmpty(str) ? (CashierPayEntity) o.a(str, CashierPayEntity.class) : null;
        return cashierPayEntity != null ? cashierPayEntity : new CashierPayEntity();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public CashierPayEntity i(String str) {
        return (CashierPayEntity) o.a(str, CashierPayEntity.class);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(IHttpConfig iHttpConfig) {
    }
}
