package com.jingdong.common.recommend.entity;

import java.util.List;

/* loaded from: classes6.dex */
public class RecommendOtherData {
    public String bottomHeaderIcon;
    public CategoryGuideAssistant categoryGuideAssistant;
    public String darkHeaderTitleUrl;
    private String displayMode;
    private int exposeNum;
    public int filteredPages;
    public String innerBackgroudColor;
    public String innerBackgroudImg;
    public String innerDescriptionFont;
    public String innerMainTitleFont;
    public String innerSubTitleFont;
    public boolean isFromCache;
    public String mainBackgroundImg;
    public String mainBackgroundThumbImg;
    public int nextPage;
    private String publicTest;
    private String publicTestBubble;
    private String publicTestBubbleTimestamp;
    private RecommendGuide recommendGuide;
    public RecommendProduct sceneTopProduct;
    private String titleUrl;
    private String uiStrategy;
    private List<WareInfoReason> wareInfoReasons;
    private String new900UIStrategy = "A";
    public boolean isReachEnd = false;
    public int tipsIndex = -1;
    public int recommendGrayNumber = 0;

    /* loaded from: classes6.dex */
    public static class CategoryGuideAssistant {
        public String expandText;
        public String jumpUrl;
        public String sourceValue;
        public int startIndex = 11;
        public int endIndex = 21;
    }

    public String get924UIStrategy() {
        return this.new900UIStrategy;
    }

    public String getDarkHeaderTitleUrl() {
        return this.darkHeaderTitleUrl;
    }

    public String getDisplayMode() {
        return this.displayMode;
    }

    public int getExposeNum() {
        return this.exposeNum;
    }

    public String getPublicTest() {
        return this.publicTest;
    }

    public String getPublicTestBubble() {
        return this.publicTestBubble;
    }

    public String getPublicTestBubbleTimestamp() {
        return this.publicTestBubbleTimestamp;
    }

    public RecommendGuide getRecommendGuide() {
        return this.recommendGuide;
    }

    public String getTitleUrl() {
        return this.titleUrl;
    }

    public String getUIStrategy() {
        return this.uiStrategy;
    }

    public List<WareInfoReason> getWareInfoReasons() {
        return this.wareInfoReasons;
    }

    public void set924UIStrategy(String str) {
        this.new900UIStrategy = str;
    }

    public void setDarkHeaderTitleUrl(String str) {
        this.darkHeaderTitleUrl = str;
    }

    public void setDisplayMode(String str) {
        this.displayMode = str;
    }

    public void setExposeNum(int i2) {
        this.exposeNum = i2;
    }

    public void setPublicTest(String str) {
        this.publicTest = str;
    }

    public void setPublicTestBubble(String str) {
        this.publicTestBubble = str;
    }

    public void setPublicTestBubbleTimestamp(String str) {
        this.publicTestBubbleTimestamp = str;
    }

    public void setRecommendGuide(RecommendGuide recommendGuide) {
        this.recommendGuide = recommendGuide;
    }

    public void setTitleUrl(String str) {
        this.titleUrl = str;
    }

    public void setUIStrategy(String str) {
        this.uiStrategy = str;
    }

    public void setWareInfoReasons(List<WareInfoReason> list) {
        this.wareInfoReasons = list;
    }
}
