package com.jingdong.common.entity.personal;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.jingdong.common.entity.personal.SwitchConfigEntity;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.PersonalPreferenceUtil;
import com.jingdong.common.utils.PersonalSwitchManager;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class PlatformOthers {
    @SerializedName("ABComponent")
    public Map<String, String> abComponent;
    public PageAbTestMtaEntity abTestMta;
    public DarkMode darkMode;
    public List<BaseTemplateEntity> floors;
    public int forceLogin;
    public HeadStyle headStyleV90;
    public String menuTimeStamp;
    public SwitchConfigEntity.MyJdSetBusiness myjdSetBusiness;
    public Map<String, Long> timeStampStructure;

    /* loaded from: classes5.dex */
    public static class DarkMode {
        public boolean enable;
    }

    /* loaded from: classes5.dex */
    public static class HeadStyle {
        public boolean enable = false;
    }

    public void preProcess() {
        HeadStyle headStyle = this.headStyleV90;
        if (headStyle != null) {
            PersonalPreferenceUtil.putMyJdUserInfoFloorStyle(headStyle.enable);
        }
        if (this.darkMode != null) {
            DeepDarkChangeManager.getInstance().saveDeepDarkGuideSwitch(this.darkMode.enable);
        }
        Map<String, String> map = this.abComponent;
        if (map != null) {
            if (map.containsKey(PersonalSwitchManager.ASYNC_INFLATE_KEY)) {
                String str = this.abComponent.get(PersonalSwitchManager.ASYNC_INFLATE_KEY);
                if (str != null && str.equals("1")) {
                    PersonalSwitchManager.putAsyncInflateSwitch(true);
                } else {
                    PersonalSwitchManager.putAsyncInflateSwitch(false);
                }
            }
            if (this.abComponent.containsKey(PersonalSwitchManager.RX_JAVA_IO_SCHEDULER_KEY)) {
                PersonalSwitchManager.putSwitch(PersonalSwitchManager.RX_JAVA_IO_SCHEDULER_KEY, TextUtils.equals(this.abComponent.get(PersonalSwitchManager.RX_JAVA_IO_SCHEDULER_KEY), "1"));
            }
            if (this.abComponent.containsKey(PersonalSwitchManager.HOME_ASYNC_INFLATE_KEY)) {
                PersonalSwitchManager.putHomeAsyncInflateSwitch(TextUtils.equals("1", this.abComponent.get(PersonalSwitchManager.HOME_ASYNC_INFLATE_KEY)));
            } else {
                PersonalSwitchManager.putHomeAsyncInflateSwitch(false);
            }
            if (this.abComponent.containsKey(PersonalSwitchManager.FONT_SCALE_ASYNC_INFLATE_KEY)) {
                PersonalSwitchManager.putAsyncInflateFontScale(TextUtils.equals("1", this.abComponent.get(PersonalSwitchManager.FONT_SCALE_ASYNC_INFLATE_KEY)));
            } else {
                PersonalSwitchManager.putAsyncInflateFontScale(false);
            }
            if (this.abComponent.containsKey(PersonalSwitchManager.PRE_LOADER)) {
                PersonalSwitchManager.putPreLoaderSwitch(TextUtils.equals("1", this.abComponent.get(PersonalSwitchManager.PRE_LOADER)));
            }
            if (this.abComponent.containsKey(PersonalSwitchManager.CORE_API_FETCH)) {
                PersonalSwitchManager.putCoreAPIFetchSwitch(TextUtils.equals("1", this.abComponent.get(PersonalSwitchManager.CORE_API_FETCH)));
            }
            if (this.abComponent.containsKey(PersonalSwitchManager.CORE_DATA_PROCESS)) {
                PersonalSwitchManager.putCoreDataProcessSwitch(TextUtils.equals("1", this.abComponent.get(PersonalSwitchManager.CORE_DATA_PROCESS)));
            }
            if (this.abComponent.containsKey(PersonalSwitchManager.PRE_TAKE_NUMBER)) {
                PersonalSwitchManager.putPreTakeNumberSwitch(TextUtils.equals("1", this.abComponent.get(PersonalSwitchManager.PRE_TAKE_NUMBER)));
            }
        }
        PersonalSwitchManager.putForceLogin(Integer.valueOf(this.forceLogin));
    }
}
