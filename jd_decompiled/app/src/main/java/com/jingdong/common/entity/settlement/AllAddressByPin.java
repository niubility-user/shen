package com.jingdong.common.entity.settlement;

import com.jingdong.common.entity.SelfUserAddress;
import com.jingdong.common.entity.UserAddress;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class AllAddressByPin extends NotifyMessage implements Serializable {
    public static final String STATE_OPEN = "1";
    public AddressIntelligentReminder addressIntelligentReminder;
    public boolean addressLayerFlag;
    public String addressLayerPointData;
    public int addressLimit;
    public String addressLimitCreateMsg;
    private ArrayList<UserAddress> addressList;
    public boolean addressListLayerFlag;
    public String addressListLayerPointData;
    public HashMap<String, String> addressTagColorMap;
    public ArrayList<AddressTagInfo> addressTagList;
    public String clickGrayAddressMsg;
    public String defaultAddressMsg;
    public boolean defaultAddressSwitch;
    public String defaultAddressTitle;
    public String encryptType;
    public String fontSizeBigSwitch;
    public boolean hySDKSwitch;
    public boolean isAddressListNewStyle;
    public boolean isCloseClipboard;
    @Deprecated
    public boolean isOpenLocate;
    public boolean isSupportVoice;
    public List<Integer> noAddresShieldProvinceIds;
    public String noAddressSwitch;
    public boolean olderVersionSwitch;
    public String parseSupportNewParamEncode;
    public List<SelfUserAddress> pickAddressList;
    public List<RecommendMobileInfo> recommendMobileList;
    public boolean supportAllEncode;
    public boolean supportNewParamEncode;
    public HashMap<String, String> switchControl;
    public boolean useRecommendAddressInfo;

    public ArrayList<UserAddress> getAddressList() {
        return this.addressList;
    }

    public HashMap<String, String> getExtMap() {
        if (this.switchControl == null) {
            this.switchControl = new HashMap<>();
        }
        return this.switchControl;
    }

    public boolean getExtMapSwitchControl(String str) {
        return "1".equals(getExtMap().get(str));
    }

    public void setAddressList(ArrayList<UserAddress> arrayList) {
        this.addressList = arrayList;
    }
}
