package com.jd.lib.productdetail.mainimage.bean;

/* loaded from: classes15.dex */
public class PdMImageEventEntity {
    public Object mObject;
    public PdImageEventCode pdImageEventCodeCode;

    public PdMImageEventEntity(PdImageEventCode pdImageEventCode) {
        this.pdImageEventCodeCode = pdImageEventCode;
    }

    public PdMImageEventEntity(PdImageEventCode pdImageEventCode, Object obj) {
        this.pdImageEventCodeCode = pdImageEventCode;
        this.mObject = obj;
    }
}
