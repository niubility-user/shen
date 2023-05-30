package com.jd.skin.lib.bean;

/* loaded from: classes18.dex */
public class ResourceItems {
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
    private String localResource;
    private String localgImageSelected;
    private String resource;
    private String resourceMd5;
    private String text;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ResourceItems resourceItems = (ResourceItems) obj;
        return this.code.equals(resourceItems.code) && this.text.equals(resourceItems.text) && this.fontSize.equals(resourceItems.fontSize) && this.fontSizeSelected.equals(resourceItems.fontSizeSelected) && this.font.equals(resourceItems.font) && this.fontColor.equals(resourceItems.fontColor) && this.fontColorSelected.equals(resourceItems.fontColorSelected) && this.bgColor.equals(resourceItems.bgColor) && this.bgColorSelected.equals(resourceItems.bgColorSelected) && this.bgImage.equals(resourceItems.bgImage) && this.bgImageSelected.equals(resourceItems.bgImageSelected) && this.extInfo.equals(resourceItems.extInfo) && this.localBgImage.equals(resourceItems.localBgImage) && this.localgImageSelected.equals(resourceItems.localgImageSelected) && this.bgImageMd5.equals(resourceItems.bgImageMd5) && this.bgImageSelected.equals(resourceItems.bgImageSelectedMd5) && this.resource.equals(resourceItems.resource) && this.resourceMd5.equals(resourceItems.resourceMd5) && this.localResource.equals(resourceItems.localResource);
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

    public String getLocalResource() {
        return this.localResource;
    }

    public String getLocalgImageSelected() {
        return this.localgImageSelected;
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
        this.localgImageSelected = str;
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

    public String toString() {
        return "ResourceItems{code='" + this.code + "', text='" + this.text + "', fontSize='" + this.fontSize + "', fontSizeSelected='" + this.fontSizeSelected + "', font='" + this.font + "', fontColor='" + this.fontColor + "', fontColorSelected='" + this.fontColorSelected + "', bgColor='" + this.bgColor + "', bgColorSelected='" + this.bgColorSelected + "', bgImage='" + this.bgImage + "', bgImageMd5='" + this.bgImageMd5 + "', bgImageSelected='" + this.bgImageSelected + "', bgImageSelectedMd5='" + this.bgImageSelectedMd5 + "', extInfo='" + this.extInfo + "', localBgImage='" + this.localBgImage + "', localgImageSelected='" + this.localgImageSelected + "', resource='" + this.resource + "', resourceMd5='" + this.resourceMd5 + "', localResource='" + this.localResource + "'}";
    }
}
