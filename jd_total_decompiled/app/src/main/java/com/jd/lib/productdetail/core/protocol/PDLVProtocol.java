package com.jd.lib.productdetail.core.protocol;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.PDLVEntity;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import java.util.HashMap;

/* loaded from: classes15.dex */
public class PDLVProtocol extends PdLVBaseProtocol {
    public static String ADD_CAR_RECOMMEND_LV = "addCartRecommend";
    private static final String FUNCTION_ID = "lv";
    public static final String TYPE_CANTAUN = "cantuan";
    public static final String TYPE_CARBUNDING = "carBunding";
    public static final String TYPE_DONG_GUIDE_AUDIO = "advancedAudioGuide";
    public static final String TYPE_DONG_GUIDE_VIDEO = "advancedVideoGuide";
    public static final String TYPE_DONG_VIRTUALPHONENUMBER = "virtualPhoneNumber";
    public static final String TYPE_EQUITY_PACKS = "equityPacks";
    public static final String TYPE_FRESH_MENU = "freshMenu";
    public static final String TYPE_HEYUE_PHONE = "treaty";
    public static final String TYPE_JOINBUY = "joinBuy";
    public static final String TYPE_JOINBUY_ALONE = "aloneBuy";
    public static final String TYPE_JPS = "signJPS";
    public static final String TYPE_LOC_2_SHOP_SERVICE = "locToTheShopService";
    public static final String TYPE_MERGEDBUY = "mergedBuy";
    public static final String TYPE_PINTAUN = "kaituan";
    public static final String TYPE_PLUS_MT_GZDP = "followShop";
    public static final String TYPE_PRESCRIPT_SERVICE = "addPrescriptService";
    public static final String TYPE_PRE_OTC = "appointRx";
    public static final String TYPE_PROPAGEWHITEBAR = "proPageWhiteBar";
    public static final String TYPE_REGULARBUY = "regularBuy";
    public static final String TYPE_RNCERTIFY = "isko";
    public static final String TYPE_WHITEBAR = "whiteBar";
    public static final String TYPE_WHITEBAR_NEWUSER = "whiteBarNewUser";
    public static final String TYPE_ZEROEPSON = "zeroEpson";
    public static boolean isRefreshLv = true;
    private String mComeFrom;

    public PDLVProtocol(String str) {
        super(str);
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdLVBaseProtocol
    protected String getFunctionId() {
        return FUNCTION_ID;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdLVBaseProtocol
    protected Object getRequestParam(Object obj) {
        if (obj instanceof HashMap) {
            HashMap hashMap = (HashMap) obj;
            if (hashMap.containsKey("comefrom") && (hashMap.get("comefrom") instanceof String)) {
                this.mComeFrom = (String) hashMap.get("comefrom");
            }
        }
        return obj;
    }

    public String getmComeFrom() {
        return this.mComeFrom;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdLVBaseProtocol
    protected boolean isNotifyUser() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.productdetail.core.protocol.PdLVBaseProtocol
    public void parseError(HttpError httpError) {
        super.parseError(httpError);
        PDLVEntity pDLVEntity = new PDLVEntity();
        if (TextUtils.isEmpty(pDLVEntity.comefrom)) {
            pDLVEntity.comefrom = this.mComeFrom;
        }
        getEventBus().post(new PDApiEvent(PDApiEvent.DETAIL_LV_FAIL_KEY, pDLVEntity, this.mEventKey));
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdLVBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PDLVEntity pDLVEntity = (PDLVEntity) JDJSON.parseObject(str, PDLVEntity.class);
        if (pDLVEntity != null && TextUtils.isEmpty(pDLVEntity.comefrom)) {
            pDLVEntity.comefrom = this.mComeFrom;
        }
        if (pDLVEntity != null) {
            pDLVEntity.inputParam = this.mInputParams;
        }
        getEventBus().post(new PDApiEvent("detail_lv_key", pDLVEntity, this.mEventKey));
        return pDLVEntity;
    }
}
