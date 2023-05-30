package com.jdpay.lib.name;

import androidx.annotation.NonNull;
import com.jdpay.lib.util.JPDigest;

/* loaded from: classes18.dex */
public class MD5NameGenerator implements NameGenerator {
    private final String source;

    public MD5NameGenerator(@NonNull String str) {
        this.source = str;
    }

    @Override // com.jdpay.lib.name.NameGenerator
    public String generate() {
        return JPDigest.md5(this.source);
    }
}
