package com.jingdong.app.mall.bundle.styleinfoview.yuyue;

import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewYuYueListener;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PDOperAppointEntity;
import com.jingdong.app.mall.bundle.styleinfoview.protocol.PDBaseProtocol;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;
import com.jingdong.common.entity.UserAddress;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class PDAppointProtocol extends PDBaseProtocol {
    private static final String FUNCTION_ID = "appoint";
    private LibPdStyleInfoViewYuYueListener mCallback;
    public boolean needVerify;

    public PDAppointProtocol(String str, LibPdStyleInfoViewYuYueListener libPdStyleInfoViewYuYueListener) {
        super(str);
        this.mCallback = libPdStyleInfoViewYuYueListener;
    }

    private String encrypt3DESECB(String str) {
        return PDUtils.encryptThreeDESECB(str, SharedPreferencesUtil.getBoolean("isDesCbc", false), "np!u5chin@adm!n1aaaaaaa2");
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return FUNCTION_ID;
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        try {
            jSONObject.put("skuId", objArr[0]);
            jSONObject.put("bsid", objArr[1]);
            jSONObject.put("ctext", objArr[2]);
            jSONObject.put("type", objArr[3]);
            jSONObject.put("check", objArr[4]);
            if (objArr[5] instanceof Boolean) {
                this.needVerify = ((Boolean) objArr[5]).booleanValue();
            }
            jSONObject.put("isShowCode", objArr[6]);
            jSONObject.put("autoAddCart", objArr[7]);
            jSONObject.put("mad", objArr[8]);
            jSONObject.put("type", objArr[9]);
            if (objArr.length > 10) {
                jSONObject.put("appointMoreTimeFlag", objArr[10]);
            }
            if (objArr.length > 11) {
                jSONObject.put("shopId", objArr[11]);
            }
            if (objArr.length > 12 && (objArr[12] instanceof UserAddress)) {
                UserAddress userAddress = (UserAddress) objArr[12];
                jSONObject.put("from", "addressPage");
                jSONObject.put("name", encrypt3DESECB(userAddress.name));
                jSONObject.put("mobile", encrypt3DESECB(userAddress.mobileReal));
                jSONObject.put("where", encrypt3DESECB(userAddress.where));
                jSONObject.put(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID, encrypt3DESECB(userAddress.addressDetail));
                jSONObject.put("province", userAddress.idProvince);
                jSONObject.put("city", userAddress.idCity);
                jSONObject.put("county", userAddress.idArea);
                jSONObject.put("town", userAddress.idTown);
                jSONObject.put("drawMoreTimeFlag", objArr[13]);
                jSONObject.put("eid", objArr[14]);
                jSONObject.put("provinceName", encrypt3DESECB(userAddress.ProvinceName));
                jSONObject.put("cityName", encrypt3DESECB(userAddress.CityName));
                jSONObject.put("countryName", encrypt3DESECB(userAddress.CountryName));
                jSONObject.put("townName", encrypt3DESECB(userAddress.TownName));
            }
        } catch (JSONException e2) {
            if (Log.D) {
                Log.d("PDAppointProtocol", "JSONException -->> ", e2);
            }
        }
        return jSONObject;
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PDOperAppointEntity pDOperAppointEntity = (PDOperAppointEntity) JDJSON.parseObject(str, PDOperAppointEntity.class);
        if (pDOperAppointEntity != null) {
            pDOperAppointEntity.needVerify = this.needVerify;
            LibPdStyleInfoViewYuYueListener libPdStyleInfoViewYuYueListener = this.mCallback;
            if (libPdStyleInfoViewYuYueListener != null) {
                libPdStyleInfoViewYuYueListener.onAppointted(pDOperAppointEntity, this.mEventKey);
            }
        }
        return pDOperAppointEntity;
    }
}
