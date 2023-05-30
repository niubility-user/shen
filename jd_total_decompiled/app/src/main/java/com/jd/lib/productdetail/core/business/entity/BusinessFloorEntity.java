package com.jd.lib.productdetail.core.business.entity;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.temp.FloorTemplate;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.lib.puppetlayout.ylayout.PuppetManager;
import com.jingdong.sdk.platform.floor.constant.BaseFloorConstant;

/* loaded from: classes15.dex */
public class BusinessFloorEntity extends FloorTemplate {
    public JDJSONObject floorJson;
    public Object mExternalData;
    public String styleId;
    public String styleVersion;

    public BusinessFloorEntity(JDJSONObject jDJSONObject) {
        super(jDJSONObject);
        this.styleId = "";
        this.styleVersion = "";
        if (BaseFloorConstant.PLATFORM_FLOOR_PUPPET.equals(this.mId)) {
            this.styleId = jDJSONObject.optString(XView2Constants.STYLEID);
            this.styleVersion = jDJSONObject.optString("styleVersion");
            if (!TextUtils.isEmpty(this.styleId) && !TextUtils.isEmpty(this.styleVersion)) {
                this.tId = this.styleId + CartConstant.KEY_YB_INFO_LINK + this.styleVersion;
            }
        }
        this.floorJson = jDJSONObject;
        if (jDJSONObject != null) {
            this.mData = jDJSONObject.optJSONObject("data");
            if (TextUtils.equals(this.mId, this.bId)) {
                this.bId += CartConstant.KEY_YB_INFO_LINK + PDUtils.getPDManagerKey();
            }
            addToFloor(check());
        }
    }

    public boolean check() {
        return BaseFloorConstant.PLATFORM_FLOOR_PUPPET.equals(this.mId) ? PuppetManager.getInstance().hasTemplate(JdSdk.getInstance().getApplicationContext(), this.styleId, this.styleVersion) && this.mData != null && this.sortId > 0 : this.mData != null && this.sortId > 0;
    }
}
