package com.jingdong.app.mall.bundle.updownload.download;

import java.io.IOException;

/* loaded from: classes3.dex */
public class DataProcessException extends IOException {
    private int mCode;

    public DataProcessException(int i2, String str) {
        super(str);
        this.mCode = i2;
    }

    public int getCode() {
        return this.mCode;
    }
}
