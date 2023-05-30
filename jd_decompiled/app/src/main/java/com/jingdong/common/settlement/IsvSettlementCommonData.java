package com.jingdong.common.settlement;

import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.platform.base.ICloneableData;
import java.util.Map;

/* loaded from: classes6.dex */
public class IsvSettlementCommonData implements ICloneableData {
    public Map<String, String> lightToDarkColor;
    public Map<String, String> lightToDarkImgUrl;
    public boolean olderVersionSwitch;
    public String payWay;
    public String productType;
    public String serverTag = "0";

    @Override // com.jingdong.sdk.platform.base.ICloneableData
    public Object cloneData() {
        try {
            return clone();
        } catch (CloneNotSupportedException unused) {
            if (Log.D) {
                Log.d("IsvSettlementCommonData", "CloneNotSupportedException");
            }
            IsvSettlementCommonData isvSettlementCommonData = new IsvSettlementCommonData();
            isvSettlementCommonData.lightToDarkImgUrl = this.lightToDarkImgUrl;
            isvSettlementCommonData.lightToDarkColor = this.lightToDarkColor;
            isvSettlementCommonData.payWay = this.payWay;
            isvSettlementCommonData.productType = this.productType;
            isvSettlementCommonData.olderVersionSwitch = this.olderVersionSwitch;
            isvSettlementCommonData.serverTag = this.serverTag;
            return isvSettlementCommonData;
        }
    }
}
