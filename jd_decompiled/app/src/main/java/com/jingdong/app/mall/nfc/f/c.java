package com.jingdong.app.mall.nfc.f;

import android.nfc.NdefRecord;
import com.google.common.base.Preconditions;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.apache.commons.codec.CharEncoding;

/* loaded from: classes4.dex */
public class c implements a {
    private final String a;

    public c(String str, String str2) {
        String str3 = (String) Preconditions.checkNotNull(str);
        this.a = (String) Preconditions.checkNotNull(str2);
    }

    public static boolean c(NdefRecord ndefRecord) {
        try {
            d(ndefRecord);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static c d(NdefRecord ndefRecord) {
        Preconditions.checkArgument(ndefRecord.getTnf() == 1);
        Preconditions.checkArgument(Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT));
        try {
            byte[] payload = ndefRecord.getPayload();
            if (payload.length <= 0) {
                ndefRecord.getType();
                return null;
            }
            String str = (payload[0] & 128) == 0 ? "UTF-8" : CharEncoding.UTF_16;
            int i2 = payload[0] & 63;
            return new c(new String(payload, 1, i2, CharEncoding.US_ASCII), new String(payload, i2 + 1, (payload.length - i2) - 1, str));
        } catch (UnsupportedEncodingException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    @Override // com.jingdong.app.mall.nfc.f.a
    public String a() {
        return this.a;
    }

    public String b() {
        return this.a;
    }
}
