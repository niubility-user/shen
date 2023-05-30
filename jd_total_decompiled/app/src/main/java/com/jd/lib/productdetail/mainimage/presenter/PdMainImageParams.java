package com.jd.lib.productdetail.mainimage.presenter;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.BaseActivity;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.Serializable;
import java.util.HashMap;

/* loaded from: classes15.dex */
public class PdMainImageParams implements Serializable {
    public boolean boomer;
    public String brandId;
    public String dJTemplateType;
    public String floorPriceMta;
    public boolean is4GCanPlayVideo;
    public boolean isDark;
    public boolean isElder;
    public String layerPriceMta;
    public String mManagerKey;
    public String mSkuTag;
    public String moduleName;
    public String mtaPageId;
    public String mtaPageName;
    public String searchParam;
    public String skuId;
    public Integer topViewIdFor3D;
    public String eventParams = "";
    public HashMap<String, JDJSONObject> mtaJsonMap = new HashMap<>();

    public String getSearchParam(String str) {
        if (TextUtils.isEmpty(this.searchParam)) {
            return str;
        }
        return str + CartConstant.KEY_YB_INFO_LINK + this.searchParam;
    }

    public void onCleared(BaseActivity baseActivity) {
        this.eventParams = "";
        this.mSkuTag = "";
        this.mtaPageId = "";
        this.searchParam = "";
        this.dJTemplateType = "";
    }
}
