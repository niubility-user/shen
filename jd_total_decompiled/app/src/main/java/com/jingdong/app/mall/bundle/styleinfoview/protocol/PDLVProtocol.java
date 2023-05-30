package com.jingdong.app.mall.bundle.styleinfoview.protocol;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewYuYueListener;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PDLVEntity;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class PDLVProtocol extends PdLVBaseProtocol {
    private static final String FUNCTION_ID = "lv";
    public static final String TYPE_CANTAUN = "cantuan";
    public static final String TYPE_CARBUNDING = "carBunding";
    public static final String TYPE_DONG_GUIDE_AUDIO = "advancedAudioGuide";
    public static final String TYPE_DONG_GUIDE_VIDEO = "advancedVideoGuide";
    public static final String TYPE_FRESH_MENU = "freshMenu";
    public static final String TYPE_HEYUE_PHONE = "treaty";
    public static final String TYPE_JOINBUY = "joinBuy";
    public static final String TYPE_JOINBUY_ALONE = "aloneBuy";
    public static final String TYPE_JPS = "signJPS";
    public static final String TYPE_MERGEDBUY = "mergedBuy";
    public static final String TYPE_PINTAUN = "kaituan";
    public static final String TYPE_PLUS_MT_GZDP = "followShop";
    public static final String TYPE_PRE_OTC = "appointRx";
    public static final String TYPE_PROPAGEWHITEBAR = "proPageWhiteBar";
    public static final String TYPE_REGULARBUY = "regularBuy";
    public static final String TYPE_RNCERTIFY = "isko";
    public static final String TYPE_WHITEBAR = "whiteBar";
    public static final String TYPE_ZEROEPSON = "zeroEpson";
    public static boolean isRefreshLv = true;
    private String mComeFrom;
    private LibPdStyleInfoViewYuYueListener viewYuYueListener;

    public PDLVProtocol(String str) {
        super(str);
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PdLVBaseProtocol
    protected String getFunctionId() {
        return FUNCTION_ID;
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PdLVBaseProtocol
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

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PdLVBaseProtocol
    protected boolean isNotifyUser() {
        return true;
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PdLVBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PDLVEntity pDLVEntity = (PDLVEntity) JDJSON.parseObject(str, PDLVEntity.class);
        if (pDLVEntity != null && TextUtils.isEmpty(pDLVEntity.comefrom)) {
            pDLVEntity.comefrom = this.mComeFrom;
        }
        LibPdStyleInfoViewYuYueListener libPdStyleInfoViewYuYueListener = this.viewYuYueListener;
        if (libPdStyleInfoViewYuYueListener != null) {
            libPdStyleInfoViewYuYueListener.dealLvData(pDLVEntity);
        }
        return pDLVEntity;
    }

    public void setViewYuYueListener(LibPdStyleInfoViewYuYueListener libPdStyleInfoViewYuYueListener) {
        this.viewYuYueListener = libPdStyleInfoViewYuYueListener;
    }
}
