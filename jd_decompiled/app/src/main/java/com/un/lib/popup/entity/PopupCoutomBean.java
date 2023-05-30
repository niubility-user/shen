package com.un.lib.popup.entity;

import java.util.List;

/* loaded from: classes11.dex */
public class PopupCoutomBean {
    private List<CustomDataBean> customData;
    private String customSwitch;
    private String titleImageUrl;
    private String titleImageUrlDark;
    private String titleText;

    /* loaded from: classes11.dex */
    public static class CustomDataBean {
        private String content;
        private String imageUrl;
        private String imageUrlDark;
        private String info;
        private String type;

        public String getContent() {
            return this.content;
        }

        public String getImageUrl() {
            return this.imageUrl;
        }

        public String getImageUrlDark() {
            return this.imageUrlDark;
        }

        public String getInfo() {
            return this.info;
        }

        public String getType() {
            return this.type;
        }

        public void setContent(String str) {
            this.content = str;
        }

        public void setImageUrl(String str) {
            this.imageUrl = str;
        }

        public void setImageUrlDark(String str) {
            this.imageUrlDark = str;
        }

        public void setInfo(String str) {
            this.info = str;
        }

        public void setType(String str) {
            this.type = str;
        }
    }

    public List<CustomDataBean> getCustomData() {
        return this.customData;
    }

    public String getCustomSwitch() {
        return this.customSwitch;
    }

    public String getTitleImageUrl() {
        return this.titleImageUrl;
    }

    public String getTitleImageUrlDark() {
        return this.titleImageUrlDark;
    }

    public String getTitleText() {
        return this.titleText;
    }

    public void setCustomData(List<CustomDataBean> list) {
        this.customData = list;
    }

    public void setCustomSwitch(String str) {
        this.customSwitch = str;
    }

    public void setTitleImageUrl(String str) {
        this.titleImageUrl = str;
    }

    public void setTitleImageUrlDark(String str) {
        this.titleImageUrlDark = str;
    }

    public void setTitleText(String str) {
        this.titleText = str;
    }
}
