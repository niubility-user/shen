package com.jingdong.app.mall.bundle.styleinfoview.entitys.sizehelper;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;

/* loaded from: classes3.dex */
public class PDSizeHelperEntity {
    public static final int CODE_NOT_LOGIN = -1;
    public static final int CODE_NOT_MAPPING_SIZE = -2;
    public static final int CODE_NOT_SETTING = -3;
    public static final int CODE_NOT_SUPPORT = -4;
    public static final int CODE_SUCCESS = 0;
    public int code;
    public PDSizeHelperInfoEntity data;
    public boolean isOpenApp;
    public String msg;
    public String openAppStr;
    public StateMap stateMap;
    public String url;

    /* loaded from: classes3.dex */
    public class StateMap {
        public String stateHasResult;
        public String stateNoResult;
        public String stateUnfilled;

        public StateMap() {
        }
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
}
