package com.jingdong.app.mall.bundle.styleinfoview.temp;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.platform.floor.constant.BaseFloorConstant;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class FloorTemplate extends BaseTemplateEntity {
    public static final int JUMP_TYPE_HTTP = 1;
    public static final int JUMP_TYPE_LAYERVIEW = 3;
    public static final int JUMP_TYPE_OPENAPP = 2;
    private JDJSONObject cfJson;
    public WareBusinessData mSkuBaseData;
    public String styleId;
    public String styleVersion;
    public Map<String, String> track;
    public boolean showSimple = false;
    public boolean isExpo = false;
    public boolean isExpo2 = false;
    public boolean expoFlag = false;
    private String trackStr = DYConstants.DY_TRACK;

    public FloorTemplate(JDJSONObject jDJSONObject) {
        this.styleId = "";
        this.styleVersion = "";
        if (jDJSONObject != null) {
            String optString = jDJSONObject.optString("mId");
            this.mId = optString;
            if (BaseFloorConstant.PLATFORM_FLOOR_PUPPET.equals(optString)) {
                this.styleId = jDJSONObject.optString(XView2Constants.STYLEID);
                this.styleVersion = jDJSONObject.optString("styleVersion");
                if (!TextUtils.isEmpty(this.styleId) && !TextUtils.isEmpty(this.styleVersion)) {
                    this.tId = this.styleId + CartConstant.KEY_YB_INFO_LINK + this.styleVersion;
                }
            }
            this.sortId = jDJSONObject.optInt(CartConstant.KEY_YB_SORTID);
            this.bId = jDJSONObject.optString("bId");
            this.refId = jDJSONObject.optString("refId");
            if (TextUtils.isEmpty(this.bId)) {
                this.bId = this.mId;
            }
            JDJSONObject optJSONObject = jDJSONObject.optJSONObject("cf");
            this.cfJson = optJSONObject;
            if (optJSONObject != null) {
                this.divideLine = optJSONObject.optString("spl");
                this.backgroundColor = this.cfJson.optString("bgc");
                parseConfig(this.cfJson);
            }
            JDJSONObject optJSONObject2 = jDJSONObject.optJSONObject("data");
            if (optJSONObject2.containsKey(this.trackStr)) {
                this.track = json2Map(optJSONObject2.optString(DYConstants.DY_TRACK));
            }
        }
    }

    @Override // com.jingdong.sdk.platform.base.BaseEntity
    public String getMtaParam() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mId);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append((TextUtils.isEmpty(this.bId) || TextUtils.equals(this.bId, this.mId)) ? "-100" : this.bId);
        return sb.toString();
    }

    public int getTextColorValue(String str) {
        return PDUtils.parseColor(str, "#232326");
    }

    public Map<String, String> json2Map(String str) {
        try {
            HashMap hashMap = new HashMap(16);
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            JDJSONObject parseObject = JDJSON.parseObject(str);
            for (String str2 : parseObject.keySet()) {
                hashMap.put(str2.toString(), parseObject.get(str2).toString());
            }
            return hashMap;
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return null;
        }
    }

    public boolean parseCommonData(JDJSONObject jDJSONObject) {
        return true;
    }

    public void parseConfig(JDJSONObject jDJSONObject) {
    }
}
