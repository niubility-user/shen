package com.jd.lib.flexcube.iwidget.entity.material;

/* loaded from: classes14.dex */
public class FlexVideoModel {
    private int bgColor;
    private boolean isMute;
    private boolean isNewWindowPlay;
    private boolean isShowErrorTip;
    private boolean isShowLoading;
    private boolean isShowNonWifiTip;
    private boolean isWifiPoolPlay;
    private String playerType;
    private String videoFitType;
    private String videoUrl;

    public int getBgColor() {
        return this.bgColor;
    }

    public String getPlayerType() {
        return this.playerType;
    }

    public String getVideoFitType() {
        return this.videoFitType;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public boolean isMute() {
        return this.isMute;
    }

    public boolean isNewWindowPlay() {
        return this.isNewWindowPlay;
    }

    public boolean isShowErrorTip() {
        return this.isShowErrorTip;
    }

    public boolean isShowLoading() {
        return this.isShowLoading;
    }

    public boolean isShowNonWifiTip() {
        return this.isShowNonWifiTip;
    }

    public boolean isWifiPoolPlay() {
        return this.isWifiPoolPlay;
    }

    public void setBgColor(int i2) {
        this.bgColor = i2;
    }

    public void setMute(boolean z) {
        this.isMute = z;
    }

    public void setNewWindowPlay(boolean z) {
        this.isNewWindowPlay = z;
    }

    public void setPlayerType(String str) {
        this.playerType = str;
    }

    public void setShowErrorTip(boolean z) {
        this.isShowErrorTip = z;
    }

    public void setShowLoading(boolean z) {
        this.isShowLoading = z;
    }

    public void setShowNonWifiTip(boolean z) {
        this.isShowNonWifiTip = z;
    }

    public void setVideoFitType(String str) {
        this.videoFitType = str;
    }

    public void setVideoUrl(String str) {
        this.videoUrl = str;
    }

    public void setWifiPoolPlay(boolean z) {
        this.isWifiPoolPlay = z;
    }
}
