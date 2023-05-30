package com.laser.open.nfc.hw.entity;

/* loaded from: classes12.dex */
public class CardDataResponse extends BaseResponse {
    private HwTrafficCardDataEntity data;

    public CardDataResponse() {
    }

    public HwTrafficCardDataEntity getData() {
        return this.data;
    }

    public void setData(HwTrafficCardDataEntity hwTrafficCardDataEntity) {
        this.data = hwTrafficCardDataEntity;
    }

    public CardDataResponse(int i2, String str) {
        super(i2, str);
    }

    public CardDataResponse(int i2, String str, HwTrafficCardDataEntity hwTrafficCardDataEntity) {
        super(i2, str);
        this.data = hwTrafficCardDataEntity;
    }
}
