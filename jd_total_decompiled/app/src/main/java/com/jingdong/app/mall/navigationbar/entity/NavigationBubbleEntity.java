package com.jingdong.app.mall.navigationbar.entity;

import android.text.TextUtils;
import com.jingdong.jdsdk.network.toolbox.FileService;

/* loaded from: classes4.dex */
public class NavigationBubbleEntity {
    public String angleSwitch;
    public String angleText;
    public int antiInterference;
    public String bubbleText;
    public String bubbleTextColor;
    public int bucketType;
    public String contentType;
    public String cornerUrl;
    public int currentMode;
    public String darkBubbleTextColor;
    public String darkCornerUrl;
    public String darkImageUrl;
    public String dataVersion;
    public long disappearTiming;
    public int dynamicType;
    public long endTime;
    public String iconUrl;
    public String iconUrlMd5;
    public String id;
    public String imgUrl;
    public String impr;
    public String inner;
    public boolean isCloseAntiInterference;
    public int jumpType;
    public String jumpUrl;
    public String lottieUrl;
    public String lottieUrlMd5;
    public String position;
    public String redPointShowSwitch;
    public String redPointSwitch;
    public int shapeType;
    public long showTiming;
    public String sourceId;
    public long startTime;
    public int triggerMode;
    public int type;
    public String updatePrice;
    public int frequency = -1;
    public int picSeparate = -1;
    public int bubbleType = -1;
    public String bubbleSwitch = "1";
    public int iconSize = -1;
    public int combinationTypes = -1;
    public int customDays = -1;
    public int customTimes = -1;

    public boolean canShowBubble() {
        if (TextUtils.isEmpty(this.imgUrl)) {
            return false;
        }
        return (this.picSeparate == 1 && TextUtils.isEmpty(this.bubbleText)) ? false : true;
    }

    public boolean canShowIcon() {
        if (this.bubbleType != 4) {
            return false;
        }
        int i2 = this.iconSize;
        return (i2 == 0 || i2 == 1) && !TextUtils.isEmpty(this.iconUrl);
    }

    public boolean iconIsLottie() {
        return !TextUtils.isEmpty(this.iconUrl) && this.iconUrl.endsWith(FileService.CACHE_EXT_NAME_JSON);
    }

    public boolean isIconAndAngleCom() {
        return this.bubbleType == 4 && this.combinationTypes == 1;
    }

    public boolean isIconAndBubbleCom() {
        int i2;
        return this.bubbleType == 4 && ((i2 = this.combinationTypes) == 2 || i2 == 3);
    }

    public String toString() {
        return "NavigationBubbleEntity{id='" + this.id + "', startTime=" + this.startTime + ", endTime=" + this.endTime + ", jumpType=" + this.jumpType + ", jumpUrl='" + this.jumpUrl + "', imgUrl='" + this.imgUrl + "', position='" + this.position + "', frequency=" + this.frequency + ", antiInterference=" + this.antiInterference + ", showTiming=" + this.showTiming + ", disappearTiming=" + this.disappearTiming + ", isCloseAntiInterference=" + this.isCloseAntiInterference + ", dataVersion='" + this.dataVersion + "', type=" + this.type + ", lottieUrl='" + this.lottieUrl + "', lottieUrlMd5='" + this.lottieUrlMd5 + "', triggerMode=" + this.triggerMode + ", picSeparate=" + this.picSeparate + ", bubbleText='" + this.bubbleText + "', bubbleType=" + this.bubbleType + ", cornerUrl='" + this.cornerUrl + "', bubbleTextColor='" + this.bubbleTextColor + "', darkBubbleTextColor='" + this.darkBubbleTextColor + "', darkImageUrl='" + this.darkImageUrl + "', darkCornerUrl='" + this.darkCornerUrl + "', bubbleSwitch='" + this.bubbleSwitch + "', iconSize=" + this.iconSize + ", iconUrl='" + this.iconUrl + "', iconUrlMd5='" + this.iconUrlMd5 + "', combinationTypes=" + this.combinationTypes + ", angleText='" + this.angleText + "', angleSwitch='" + this.angleSwitch + "', redPointSwitch='" + this.redPointSwitch + "', redPointShowSwitch='" + this.redPointShowSwitch + "', sourceId='" + this.sourceId + "', contentType='" + this.contentType + "', customDays=" + this.customDays + ", customTimes=" + this.customTimes + ", updatePrice='" + this.updatePrice + "', impr='" + this.impr + "', inner='" + this.inner + "', bucketType='" + this.bucketType + "', currentMode='" + this.currentMode + "'}";
    }
}
