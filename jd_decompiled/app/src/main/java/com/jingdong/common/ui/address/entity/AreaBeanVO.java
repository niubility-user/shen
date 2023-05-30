package com.jingdong.common.ui.address.entity;

/* loaded from: classes6.dex */
public class AreaBeanVO {
    private int Id;
    private boolean IsSupCOD;
    private String Name;
    private String areaCode;
    private boolean isDiaoYuDao;
    private boolean isForeignOverSea;
    private boolean isGangAoTai;
    private String nameCode;
    private int parentId;
    private String parentName;

    public String getAreaCode() {
        return this.areaCode;
    }

    public int getId() {
        return this.Id;
    }

    public String getName() {
        return this.Name;
    }

    public String getNameCode() {
        return this.nameCode;
    }

    public int getParentId() {
        return this.parentId;
    }

    public String getParentName() {
        return this.parentName;
    }

    public boolean isIsDiaoYuDao() {
        return this.isDiaoYuDao;
    }

    public boolean isIsForeignOverSea() {
        return this.isForeignOverSea;
    }

    public boolean isIsGangAoTai() {
        return this.isGangAoTai;
    }

    public boolean isIsSupCOD() {
        return this.IsSupCOD;
    }

    public void setAreaCode(String str) {
        this.areaCode = str;
    }

    public void setId(int i2) {
        this.Id = i2;
    }

    public void setIsDiaoYuDao(boolean z) {
        this.isDiaoYuDao = z;
    }

    public void setIsForeignOverSea(boolean z) {
        this.isForeignOverSea = z;
    }

    public void setIsGangAoTai(boolean z) {
        this.isGangAoTai = z;
    }

    public void setIsSupCOD(boolean z) {
        this.IsSupCOD = z;
    }

    public void setName(String str) {
        this.Name = str;
    }

    public void setNameCode(String str) {
        this.nameCode = str;
    }

    public void setParentId(int i2) {
        this.parentId = i2;
    }

    public void setParentName(String str) {
        this.parentName = str;
    }
}
