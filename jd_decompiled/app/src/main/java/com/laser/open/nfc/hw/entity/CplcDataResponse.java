package com.laser.open.nfc.hw.entity;

/* loaded from: classes12.dex */
public class CplcDataResponse extends BaseResponse {
    private HwSEInfoEntity data;

    public CplcDataResponse(int i2, String str, HwSEInfoEntity hwSEInfoEntity) {
        super(i2, str);
        this.data = hwSEInfoEntity;
    }

    public HwSEInfoEntity getData() {
        return this.data;
    }

    public void setData(HwSEInfoEntity hwSEInfoEntity) {
        this.data = hwSEInfoEntity;
    }

    public CplcDataResponse(int i2, String str) {
        super(i2, str);
    }

    public CplcDataResponse() {
    }
}
