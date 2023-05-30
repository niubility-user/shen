package com.jd.lib.productdetail.core.entitys.warebusiness;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class WareBusinessXpTrailFloorEntity {
    public XpTrialInfo xpTrialInfo;

    /* loaded from: classes15.dex */
    public static class PayMap {
        public String isFromCart;
        public String orderId;
        public String orderPrice;
        public String orderType;
        public String orderTypeCode;
        public String paySourceId;
    }

    /* loaded from: classes15.dex */
    public static class TryButton {
        public String jumpUrl;
        public String mainText;
        public String subText;
        public int type;
    }

    /* loaded from: classes15.dex */
    public static class TryPop {
        public String buttonText;
        public String statusText;
        public String title;
        public String toastText;
        public String viewText;
    }

    /* loaded from: classes15.dex */
    public static class TryWinningInfo {
        public String applyTitle;
        public String floorTitle;
        public String mainTitle;
        public String subTitle;
        public String winningTitle;
        public List<WinningUserList> winningUserList;
    }

    /* loaded from: classes15.dex */
    public static class WinningUserList {
        public String nickname;
        public int userLevel;
    }

    /* loaded from: classes15.dex */
    public static class XpTrailBean {
        public ArrayList<AssistAvatars> assistAvatars;
        public String completionFlag;
        public String largeImage;
        public String orderPrice;
        public PayMap payMap;
        public String shareSourceCode;
        public boolean shareType;
        public String shareUrl;
        public String supplyCountDesc;
        public String trialName;
        public TryButton tryButton;
        public String tryContent;
        public TryPop tryPop;
        public TryWinningInfo tryWinningInfo;

        /* loaded from: classes15.dex */
        public static class AssistAvatars {
            public String avatar;
            public String nickname;
        }
    }

    /* loaded from: classes15.dex */
    public static class XpTrialInfo {
        public String activityId;
        public String activityTitle;
        public long leftTime;
        public String leftTimeContent;
        public XpTrailBean xpActivity;
        public xpRuleInfo xpRuleInfo;
    }

    /* loaded from: classes15.dex */
    public static class xpRuleInfo {
        public List<String> ruleContent;
        public String ruleJumpIcon;
        public String ruleJumpTitle;
        public String ruleTitle;
    }
}
