package com.jingdong.common.unification.customtheme.entity;

/* loaded from: classes6.dex */
public class ImageInfoEntity {
    public String colorType;
    public int displayType;
    public boolean isEffected;
    public String lableName;
    public String md5;
    public String sort;
    public String imageId = "";
    public String url = "";
    public String localPath = "";

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ImageInfoEntity) {
            ImageInfoEntity imageInfoEntity = (ImageInfoEntity) obj;
            if (this.isEffected == imageInfoEntity.isEffected && this.displayType == imageInfoEntity.displayType && this.imageId.equals(imageInfoEntity.imageId)) {
                String str = this.url;
                if (str == null ? imageInfoEntity.url == null : str.equals(imageInfoEntity.url)) {
                    String str2 = this.colorType;
                    if (str2 == null ? imageInfoEntity.colorType == null : str2.equals(imageInfoEntity.colorType)) {
                        String str3 = this.sort;
                        if (str3 == null ? imageInfoEntity.sort == null : str3.equals(imageInfoEntity.sort)) {
                            String str4 = this.lableName;
                            String str5 = imageInfoEntity.lableName;
                            return str4 != null ? str4.equals(str5) : str5 == null;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.imageId.hashCode() * 31;
        String str = this.url;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.colorType;
        int hashCode3 = (((((hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + (this.isEffected ? 1 : 0)) * 31) + this.displayType) * 31;
        String str3 = this.sort;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.lableName;
        return hashCode4 + (str4 != null ? str4.hashCode() : 0);
    }
}
