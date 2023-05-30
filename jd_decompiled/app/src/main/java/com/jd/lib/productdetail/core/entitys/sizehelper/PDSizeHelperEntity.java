package com.jd.lib.productdetail.core.entitys.sizehelper;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.utils.PDUtils;

/* loaded from: classes15.dex */
public class PDSizeHelperEntity {
    public static final int CODE_NOT_LOGIN = -1;
    public static final int CODE_NOT_MAPPING_SIZE = -2;
    public static final int CODE_NOT_SETTING = -3;
    public static final int CODE_NOT_SUPPORT = -4;
    public static final int CODE_SUCCESS = 0;
    public int code;
    public PDSizeHelperInfoEntity data;
    public String headIcon;
    public String headIconBlack;
    public String headIconGray;
    public boolean isOpenApp;
    public String msg;
    public String openAppStr;
    public boolean sizeAssistPro;
    public StateMap stateMap;
    public String url;

    /* loaded from: classes15.dex */
    public static class StateMap {
        public String stateHasResult;
        public String stateNoResult;
        public String stateUnfilled;
    }

    public void dealData(boolean z, boolean z2) {
        PDSizeHelperInfoEntity pDSizeHelperInfoEntity = this.data;
        if (pDSizeHelperInfoEntity == null || !z) {
            return;
        }
        String str = pDSizeHelperInfoEntity.size;
        if (!TextUtils.isEmpty(str)) {
            this.data.size = PDUtils.decryptThreeDESECB(str, z2);
        }
        String str2 = this.data.title;
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        this.data.title = PDUtils.decryptThreeDESECB(str2, z2);
    }

    public PDSizeHelperEntity getSizeText(String str) {
        JDJSONObject parseObject;
        PDSizeHelperEntity pDSizeHelperEntity;
        try {
            if (TextUtils.isEmpty(str) || (parseObject = JDJSON.parseObject(str)) == null || parseObject.isNull("NativeMap") || (pDSizeHelperEntity = (PDSizeHelperEntity) JDJSON.parseObject(parseObject.optJSONObject("NativeMap").toJSONString(), PDSizeHelperEntity.class)) == null) {
                return null;
            }
            this.code = pDSizeHelperEntity.code;
            this.msg = pDSizeHelperEntity.msg;
            PDSizeHelperInfoEntity pDSizeHelperInfoEntity = pDSizeHelperEntity.data;
            if (pDSizeHelperInfoEntity != null) {
                this.data = pDSizeHelperInfoEntity;
            }
            return this;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
