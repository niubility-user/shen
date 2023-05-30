package tv.danmaku.ijk.media.utils;

import android.text.TextUtils;
import java.security.MessageDigest;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import tv.danmaku.ijk.media.JDPlayerConstant;

/* loaded from: classes11.dex */
public class PlayerToolsUtil {
    public static String MD5(String str) {
        try {
            byte[] digest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(str.getBytes("utf-8"));
            char[] charArray = "0123456789ABCDEF".toCharArray();
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (int i2 = 0; i2 < digest.length; i2++) {
                sb.append(charArray[(digest[i2] >> 4) & 15]);
                sb.append(charArray[digest[i2] & 15]);
            }
            return sb.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        }
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x004a: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:29:0x004a */
    /* JADX WARN: Removed duplicated region for block: B:40:0x004d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getFileMD5(java.io.File r5) {
        /*
            r0 = 0
            java.lang.String r1 = "MD5"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r1)     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            r5 = 8192(0x2000, float:1.14794E-41)
            byte[] r5 = new byte[r5]     // Catch: java.lang.Exception -> L35 java.lang.Throwable -> L49
        L10:
            int r3 = r2.read(r5)     // Catch: java.lang.Exception -> L35 java.lang.Throwable -> L49
            r4 = -1
            if (r3 == r4) goto L1c
            r4 = 0
            r1.update(r5, r4, r3)     // Catch: java.lang.Exception -> L35 java.lang.Throwable -> L49
            goto L10
        L1c:
            java.math.BigInteger r5 = new java.math.BigInteger     // Catch: java.lang.Exception -> L35 java.lang.Throwable -> L49
            r3 = 1
            byte[] r1 = r1.digest()     // Catch: java.lang.Exception -> L35 java.lang.Throwable -> L49
            r5.<init>(r3, r1)     // Catch: java.lang.Exception -> L35 java.lang.Throwable -> L49
            r1 = 16
            java.lang.String r5 = r5.toString(r1)     // Catch: java.lang.Exception -> L35 java.lang.Throwable -> L49
            r2.close()     // Catch: java.io.IOException -> L30
            goto L34
        L30:
            r0 = move-exception
            r0.printStackTrace()
        L34:
            return r5
        L35:
            r5 = move-exception
            goto L3b
        L37:
            r5 = move-exception
            goto L4b
        L39:
            r5 = move-exception
            r2 = r0
        L3b:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L49
            if (r2 == 0) goto L48
            r2.close()     // Catch: java.io.IOException -> L44
            goto L48
        L44:
            r5 = move-exception
            r5.printStackTrace()
        L48:
            return r0
        L49:
            r5 = move-exception
            r0 = r2
        L4b:
            if (r0 == 0) goto L55
            r0.close()     // Catch: java.io.IOException -> L51
            goto L55
        L51:
            r0 = move-exception
            r0.printStackTrace()
        L55:
            goto L57
        L56:
            throw r5
        L57:
            goto L56
        */
        throw new UnsupportedOperationException("Method not decompiled: tv.danmaku.ijk.media.utils.PlayerToolsUtil.getFileMD5(java.io.File):java.lang.String");
    }

    public static String getOriginUrl(String str) {
        while (true) {
            if (!TextUtils.isEmpty(str)) {
                if (str.startsWith(JDPlayerConstant.IJK_CACHE_HEAD) && str.length() > 17) {
                    str = str.substring(17);
                } else if (str.startsWith(JDPlayerConstant.HTTP_HOOK_HEAD) && str.length() > 12) {
                    str = str.substring(12);
                } else if (str.startsWith(JDPlayerConstant.LIVE_HOOK_HEAD) && str.length() > 12) {
                    str = str.substring(12);
                } else if (str.startsWith(JDPlayerConstant.IJK_CACHE_HEAD) && str.length() > 17) {
                    str = str.substring(17);
                } else if (str.startsWith(JDPlayerConstant.LIVE_QUICS_HEAD)) {
                    str = str.replaceFirst("quics", "https");
                } else if (!str.startsWith(JDPlayerConstant.LIVE_QUIC_HEAD)) {
                    return str;
                } else {
                    str = str.replaceFirst("quic", "http");
                }
            }
        }
    }
}
