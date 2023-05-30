package com.jingdong.common.recommend;

import android.graphics.Color;
import android.text.TextUtils;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class RecommendConfig {
    private String businessElderValue;
    private int darkBgColor;
    private boolean enableLiveProductsCover;
    private boolean isDarkTheme;
    private boolean isEnableDeepDark;
    private boolean isFollowService;
    private String netExtentParam;
    private boolean recommendServiceElderSwitch;
    private boolean serviceDarkSwitch;
    private String displayStyle = "A";
    public boolean isPdFeedDark = false;

    public int getDarkBgColor() {
        return this.darkBgColor;
    }

    public String getDisplayStyle() {
        return this.displayStyle;
    }

    public String getNetExtentParam() {
        return this.netExtentParam;
    }

    public boolean isBigEnable() {
        return ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode());
    }

    public boolean isDarkEnable() {
        if (this.isEnableDeepDark) {
            return this.isFollowService ? this.serviceDarkSwitch && DeepDarkChangeManager.getInstance().getUIMode() == 1 : DeepDarkChangeManager.getInstance().getUIMode() == 1;
        }
        return false;
    }

    public boolean isDarkTheme() {
        return this.isDarkTheme;
    }

    public boolean isElderEnable() {
        return !StringUtil.isEmpty(this.businessElderValue) ? "1".equals(this.businessElderValue) && !this.recommendServiceElderSwitch && JDElderModeUtils.isElderMode() : !this.recommendServiceElderSwitch && JDElderModeUtils.isElderMode();
    }

    public boolean isEnableLiveProductCover() {
        return this.enableLiveProductsCover;
    }

    public void serviceDarkSwitch(boolean z) {
        this.serviceDarkSwitch = z;
    }

    public void setBusinessElderValue(String str) {
        this.businessElderValue = str;
    }

    public void setDarkBgColor(String str) {
        try {
            this.darkBgColor = Color.parseColor(str);
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    public void setDarkTheme(boolean z) {
        this.isDarkTheme = z;
    }

    public void setDisplayStyle(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.displayStyle = str;
    }

    public void setEnableDeepDark(boolean z) {
        this.isEnableDeepDark = z;
    }

    public void setEnableLiveProductsCover(boolean z) {
        this.enableLiveProductsCover = z;
    }

    public void setFollowService(boolean z) {
        this.isFollowService = z;
    }

    public void setNetExtentParam(String str) {
        this.netExtentParam = str;
    }

    public void setPDFeedDarkStyle(boolean z) {
        this.isPdFeedDark = z;
    }

    public void setRecommendServiceElderSwitch(boolean z) {
        this.recommendServiceElderSwitch = z;
    }
}
