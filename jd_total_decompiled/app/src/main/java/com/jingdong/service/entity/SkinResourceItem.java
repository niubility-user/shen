package com.jingdong.service.entity;

/* loaded from: classes10.dex */
public class SkinResourceItem {
    private String bgColor;
    private String bgColorSelected;
    private String bgImage;
    private String bgImageMd5;
    private String bgImageSelected;
    private String bgImageSelectedMd5;
    private String code;
    private String extInfo;
    private String font;
    private String fontColor;
    private String fontColorSelected;
    private String fontSize;
    private String fontSizeSelected;
    private String localBgImage;
    private String localBgImageSelected;
    private String localResource;
    private String resource;
    private String resourceMd5;
    private String text;

    /* loaded from: classes10.dex */
    public static class Builder {
        private String bgColor;
        private String bgColorSelected;
        private String bgImage;
        private String bgImageMd5;
        private String bgImageSelected;
        private String bgImageSelectedMd5;
        private String code;
        private String extInfo;
        private String font;
        private String fontColor;
        private String fontColorSelected;
        private String fontSize;
        private String fontSizeSelected;
        private String localBgImage;
        private String localBgImageSelected;
        private String localResource;
        private String resource;
        private String resourceMd5;
        private String text;

        public Builder bgColor(String str) {
            this.bgColor = str;
            return this;
        }

        public Builder bgColorSelected(String str) {
            this.bgColorSelected = str;
            return this;
        }

        public Builder bgImage(String str) {
            this.bgImage = str;
            return this;
        }

        public Builder bgImageMd5(String str) {
            this.bgImageMd5 = str;
            return this;
        }

        public Builder bgImageSelected(String str) {
            this.bgImageSelected = str;
            return this;
        }

        public Builder bgImageSelectedMd5(String str) {
            this.bgImageSelectedMd5 = str;
            return this;
        }

        public SkinResourceItem build() {
            SkinResourceItem skinResourceItem = new SkinResourceItem();
            skinResourceItem.code = this.code;
            skinResourceItem.text = this.text;
            skinResourceItem.fontSize = this.fontSize;
            skinResourceItem.fontSizeSelected = this.fontSizeSelected;
            skinResourceItem.font = this.font;
            skinResourceItem.fontColor = this.fontColor;
            skinResourceItem.fontColorSelected = this.fontColorSelected;
            skinResourceItem.bgColor = this.bgColor;
            skinResourceItem.bgColorSelected = this.bgColorSelected;
            skinResourceItem.bgImage = this.bgImage;
            skinResourceItem.bgImageMd5 = this.bgImageMd5;
            skinResourceItem.bgImageSelected = this.bgImageSelected;
            skinResourceItem.bgImageSelectedMd5 = this.bgImageSelectedMd5;
            skinResourceItem.extInfo = this.extInfo;
            skinResourceItem.localBgImage = this.localBgImage;
            skinResourceItem.localBgImageSelected = this.localBgImageSelected;
            skinResourceItem.resource = this.resource;
            skinResourceItem.resourceMd5 = this.resourceMd5;
            skinResourceItem.localResource = this.localResource;
            return skinResourceItem;
        }

        public Builder code(String str) {
            this.code = str;
            return this;
        }

        public Builder extInfo(String str) {
            this.extInfo = str;
            return this;
        }

        public Builder font(String str) {
            this.font = str;
            return this;
        }

        public Builder fontColor(String str) {
            this.fontColor = str;
            return this;
        }

        public Builder fontColorSelected(String str) {
            this.fontColorSelected = str;
            return this;
        }

        public Builder fontSize(String str) {
            this.fontSize = str;
            return this;
        }

        public Builder fontSizeSelected(String str) {
            this.fontSizeSelected = str;
            return this;
        }

        public Builder localBgImage(String str) {
            this.localBgImage = str;
            return this;
        }

        public Builder localBgImageSelected(String str) {
            this.localBgImageSelected = str;
            return this;
        }

        public Builder localResource(String str) {
            this.localResource = str;
            return this;
        }

        public Builder resource(String str) {
            this.resource = str;
            return this;
        }

        public Builder resourceMd5(String str) {
            this.resourceMd5 = str;
            return this;
        }

        public Builder text(String str) {
            this.text = str;
            return this;
        }
    }

    public String getBgColor() {
        return this.bgColor;
    }

    public String getBgColorSelected() {
        return this.bgColorSelected;
    }

    public String getBgImage() {
        return this.bgImage;
    }

    public String getBgImageMd5() {
        return this.bgImageMd5;
    }

    public String getBgImageSelected() {
        return this.bgImageSelected;
    }

    public String getBgImageSelectedMd5() {
        return this.bgImageSelectedMd5;
    }

    public String getCode() {
        return this.code;
    }

    public String getExtInfo() {
        return this.extInfo;
    }

    public String getFont() {
        return this.font;
    }

    public String getFontColor() {
        return this.fontColor;
    }

    public String getFontColorSelected() {
        return this.fontColorSelected;
    }

    public String getFontSize() {
        return this.fontSize;
    }

    public String getFontSizeSelected() {
        return this.fontSizeSelected;
    }

    public String getLocalBgImage() {
        return this.localBgImage;
    }

    public String getLocalBgImageSelected() {
        return this.localBgImageSelected;
    }

    public String getLocalResource() {
        return this.localResource;
    }

    public String getResource() {
        return this.resource;
    }

    public String getResourceMd5() {
        return this.resourceMd5;
    }

    public String getText() {
        return this.text;
    }

    public void setBgColor(String str) {
        this.bgColor = str;
    }

    public void setBgColorSelected(String str) {
        this.bgColorSelected = str;
    }

    public void setBgImage(String str) {
        this.bgImage = str;
    }

    public void setBgImageMd5(String str) {
        this.bgImageMd5 = str;
    }

    public void setBgImageSelected(String str) {
        this.bgImageSelected = str;
    }

    public void setBgImageSelectedMd5(String str) {
        this.bgImageSelectedMd5 = str;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setExtInfo(String str) {
        this.extInfo = str;
    }

    public void setFont(String str) {
        this.font = str;
    }

    public void setFontColor(String str) {
        this.fontColor = str;
    }

    public void setFontColorSelected(String str) {
        this.fontColorSelected = str;
    }

    public void setFontSize(String str) {
        this.fontSize = str;
    }

    public void setFontSizeSelected(String str) {
        this.fontSizeSelected = str;
    }

    public void setLocalBgImage(String str) {
        this.localBgImage = str;
    }

    public void setLocalResource(String str) {
        this.localResource = str;
    }

    public void setLocalgImageSelected(String str) {
        this.localBgImageSelected = str;
    }

    public void setResource(String str) {
        this.resource = str;
    }

    public void setResourceMd5(String str) {
        this.resourceMd5 = str;
    }

    public void setText(String str) {
        this.text = str;
    }
}
