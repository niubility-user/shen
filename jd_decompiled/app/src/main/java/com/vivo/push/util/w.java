package com.vivo.push.util;

import android.content.Context;
import com.jd.dynamic.DYConstants;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes11.dex */
public final class w extends b {
    private static w b;

    public static synchronized w b() {
        w wVar;
        synchronized (w.class) {
            if (b == null) {
                b = new w();
            }
            wVar = b;
        }
        return wVar;
    }

    public final synchronized void a(Context context) {
        if (this.a == null) {
            this.a = context;
            a(context, "com.vivo.push_preferences");
        }
    }

    public final byte[] c() {
        byte[] c2 = c(b("com.vivo.push.secure_cache_iv", ""));
        return (c2 == null || c2.length <= 0) ? new byte[]{ReplyCode.reply0x22, 32, ReplyCode.reply0x21, ReplyCode.reply0x25, ReplyCode.reply0x21, ReplyCode.reply0x22, 32, ReplyCode.reply0x21, ReplyCode.reply0x21, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x29, ReplyCode.reply0x23, 32, 32, 32} : c2;
    }

    public final byte[] d() {
        byte[] c2 = c(b("com.vivo.push.secure_cache_key", ""));
        return (c2 == null || c2.length <= 0) ? new byte[]{ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, ReplyCode.reply0x25, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 32, ReplyCode.reply0x26, ReplyCode.reply0x25, ReplyCode.reply0x24, ReplyCode.reply0x23, ReplyCode.reply0x22, ReplyCode.reply0x21} : c2;
    }

    private static byte[] c(String str) {
        int i2;
        byte[] bArr = null;
        try {
            String[] split = str.split(DYConstants.DY_REGEX_COMMA);
            if (split.length > 0) {
                bArr = new byte[split.length];
                i2 = split.length;
            } else {
                i2 = 0;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                bArr[i3] = Byte.parseByte(split[i3].trim());
            }
        } catch (Exception e2) {
            p.a("SharePreferenceManager", "getCodeBytes error:" + e2.getMessage());
        }
        return bArr;
    }
}
