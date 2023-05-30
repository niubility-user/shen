package com.jingdong.common.apkcenter;

import android.annotation.TargetApi;
import android.util.Base64;
import java.util.Arrays;
import java.util.jar.Attributes;

/* loaded from: classes5.dex */
public class ManifestDigest {
    private static final String[] DIGEST_TYPES = {"SHA1-Digest", "SHA-Digest", "MD5-Digest"};
    private static final String TO_STRING_PREFIX = "ManifestDigest {mDigest=";
    private final byte[] mDigest;

    ManifestDigest(byte[] bArr) {
        this.mDigest = bArr;
    }

    @TargetApi(8)
    static ManifestDigest fromAttributes(Attributes attributes) {
        String str;
        if (attributes == null) {
            return null;
        }
        int i2 = 0;
        while (true) {
            String[] strArr = DIGEST_TYPES;
            if (i2 >= strArr.length) {
                str = null;
                break;
            }
            str = attributes.getValue(strArr[i2]);
            if (str != null) {
                break;
            }
            i2++;
        }
        if (str == null) {
            return null;
        }
        return new ManifestDigest(Base64.decode(str, 0));
    }

    public boolean equals(Object obj) {
        if (obj instanceof ManifestDigest) {
            ManifestDigest manifestDigest = (ManifestDigest) obj;
            return this == manifestDigest || Arrays.equals(this.mDigest, manifestDigest.mDigest);
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(this.mDigest);
    }
}
