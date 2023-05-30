package com.jingdong.jdma.minterface;

import androidx.room.FtsOptions;

/* loaded from: classes12.dex */
public enum AppMode {
    SIMPLE(FtsOptions.TOKENIZER_SIMPLE),
    NORMAL("");
    
    private String name;

    AppMode(String str) {
        this.name = str;
    }

    public final String getName() {
        return this.name;
    }
}
