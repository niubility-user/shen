package com.jd.dynamic.entity;

/* loaded from: classes13.dex */
public class ResultEntity {
    public boolean isAssets;
    public boolean isBackup = false;
    public String jsString;
    public ViewNode viewNode;
    public String zipDir;
    public String zipVersion;

    public ResultEntity() {
    }

    public ResultEntity(ViewNode viewNode, String str) {
        this.viewNode = viewNode;
        this.zipDir = str;
    }

    public ResultEntity(ViewNode viewNode, String str, boolean z) {
        this.viewNode = viewNode;
        this.zipDir = str;
        this.isAssets = z;
    }

    /* renamed from: clone  reason: merged with bridge method [inline-methods] */
    public ResultEntity m17clone() {
        ResultEntity resultEntity = new ResultEntity();
        ViewNode viewNode = this.viewNode;
        if (viewNode != null) {
            resultEntity.viewNode = viewNode.m18clone();
        }
        resultEntity.zipDir = this.zipDir;
        resultEntity.isAssets = this.isAssets;
        resultEntity.jsString = this.jsString;
        resultEntity.isBackup = this.isBackup;
        return resultEntity;
    }
}
