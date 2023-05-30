package com.jingdong.common.unification.customtheme.entity;

import com.jd.framework.json.anotation.JSONField;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;

/* loaded from: classes.dex */
public class NavigationInfo {
    public String angleLabel;
    public int angleSwitch;
    public String bigIconIsOpen;
    public int bigLottieFlag;
    public String clickEventId;
    public String cutLabelName;
    public int displayType;
    public String effectEndLottiePath;
    public String effectEndLottieUrl;
    public String effectEndLottieUrlMd5;
    public String effectStartLottiePath;
    public String effectStartLottieUrl;
    public String effectStartLottieUrlMd5;
    public String functionId;
    public boolean hasAngle;
    public boolean hasMarketPic;
    public boolean hasRedPoint;
    public int id;
    public boolean isDark;
    public String labelColor;
    @JSONField(name = CustomThemeConstance.NAV_UNSELECT)
    public String labelImage;
    @JSONField(name = "lableImageMd5")
    public String labelImageMd5;
    public String labelImagePath;
    @JSONField(name = "lableName")
    public String labelName;
    public String lottieMd5;
    public String lottiePath;
    public String lottieUrl;
    public int marketingAuto;
    public String marketingId;
    public String marketingLottiePath;
    public String marketingLottieUrl;
    public String marketingLottieUrlMd5;
    public int marketingPlayTimes;
    public String middleFirstImg;
    public String middleFirstImgMd5;
    public String middleFirstImgPath;
    public String middleFirstLottiePath;
    public String middleFirstLottieUrl;
    public String middleFirstLottieUrlMd5;
    public String middleSecondImg;
    public String middleSecondImgMd5;
    public String middleSecondImgPath;
    public String middleSecondLottiePath;
    public String middleSecondLottieUrl;
    public String middleSecondLottieUrlMd5;
    public String middleThirdLottiePath;
    public String middleThirdLottieUrl;
    public String middleThirdLottieUrlMd5;
    @JSONField(name = "modelAndroid")
    public String model;
    public int navigationId;
    @JSONField(name = "optlabelColor")
    public String optLabelColor;
    @JSONField(name = CustomThemeConstance.NAV_SELECT_PATH)
    public String optLabelImage;
    @JSONField(name = "optlableImageMd5")
    public String optLabelImageMd5;
    public String optLabelImagePath;
    public String tabNameSelected;
    public String url;
    public int useType;

    public String toString() {
        return "NavigationInfo{id=" + this.id + ", displayType=" + this.displayType + ", functionId='" + this.functionId + "', navigationId=" + this.navigationId + ", labelImage='" + this.labelImage + "', tabNameSelected='" + this.tabNameSelected + "', labelImagePath='" + this.labelImagePath + "', labelColor='" + this.labelColor + "', labelName='" + this.labelName + "', cutLabelName='" + this.cutLabelName + "', optLabelImage='" + this.optLabelImage + "', optLabelImagePath='" + this.optLabelImagePath + "', optLabelColor='" + this.optLabelColor + "', useType=" + this.useType + ", url='" + this.url + "', model='" + this.model + "', lottieUrl='" + this.lottieUrl + "', lottiePath='" + this.lottiePath + "', clickEventId='" + this.clickEventId + "', lottieMd5='" + this.lottieMd5 + "', labelImageMd5='" + this.labelImageMd5 + "', optLabelImageMd5='" + this.optLabelImageMd5 + "', isDark=" + this.isDark + ", middleFirstLottieUrlMd5='" + this.middleFirstLottieUrlMd5 + "', middleFirstImgMd5='" + this.middleFirstImgMd5 + "', middleSecondLottieUrlMd5='" + this.middleSecondLottieUrlMd5 + "', middleSecondImgMd5='" + this.middleSecondImgMd5 + "', middleFirstLottieUrl='" + this.middleFirstLottieUrl + "', middleFirstLottiePath='" + this.middleFirstLottiePath + "', middleFirstImg='" + this.middleFirstImg + "', middleFirstImgPath='" + this.middleFirstImgPath + "', middleSecondLottieUrl='" + this.middleSecondLottieUrl + "', middleSecondLottiePath='" + this.middleSecondLottiePath + "', middleSecondImg='" + this.middleSecondImg + "', middleSecondImgPath='" + this.middleSecondImgPath + "', middleThirdLottieUrl='" + this.middleThirdLottieUrl + "', middleThirdLottiePath='" + this.middleThirdLottiePath + "', middleThirdLottieUrlMd5='" + this.middleThirdLottieUrlMd5 + "', effectStartLottieUrl='" + this.effectStartLottieUrl + "', effectStartLottiePath='" + this.effectStartLottiePath + "', effectStartLottieUrlMd5='" + this.effectStartLottieUrlMd5 + "', effectEndLottieUrl='" + this.effectEndLottieUrl + "', effectEndLottiePath='" + this.effectEndLottiePath + "', effectEndLottieUrlMd5='" + this.effectEndLottieUrlMd5 + "', marketingLottieUrl='" + this.marketingLottieUrl + "', marketingLottiePath='" + this.marketingLottiePath + "', marketingLottieUrlMd5='" + this.marketingLottieUrlMd5 + "', marketingAuto=" + this.marketingAuto + ", marketingPlayTimes=" + this.marketingPlayTimes + ", marketingId=" + this.marketingId + ", bigIconIsOpen=" + this.bigIconIsOpen + ", angleSwitch=" + this.angleSwitch + '}';
    }
}
