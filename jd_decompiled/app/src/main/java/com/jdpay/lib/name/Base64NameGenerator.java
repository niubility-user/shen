package com.jdpay.lib.name;

import android.util.Base64;
import androidx.annotation.NonNull;

/* loaded from: classes18.dex */
public class Base64NameGenerator implements NameGenerator {
    private final String source;

    public Base64NameGenerator(@NonNull String str) {
        this.source = str;
    }

    @Override // com.jdpay.lib.name.NameGenerator
    public String generate() {
        return Base64.encodeToString(this.source.getBytes(), 0);
    }
}
