package com.unicom.xiaowo.login.a;

import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.wireless.sdk.intelligent.assistant.audio.record.Constant;
import com.jingdong.common.utils.LangUtils;
import jd.wjlogin_sdk.util.ReplyCode;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes11.dex */
public class b {
    private static char[] a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static byte[] b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, ReplyCode.reply0x25, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, -1, -1, -1, -1, -1};

    public static String a(String str) {
        return str.replace(MqttTopic.SINGLE_LEVEL_WILDCARD, "%2B").replace(ContainerUtils.KEY_VALUE_DELIMITER, "%3D").replace("/", "%2F").replace(LangUtils.SINGLE_SPACE, "%20");
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x007a, code lost:
        if (r2 != (-1)) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x007d, code lost:
        r1.write(r2 | ((r5 & 3) << 6));
        r2 = r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] b(java.lang.String r8) {
        /*
            byte[] r8 = r8.getBytes()
            int r0 = r8.length
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>(r0)
            r2 = 0
        Lb:
            if (r2 >= r0) goto L87
        Ld:
            byte[] r3 = com.unicom.xiaowo.login.a.b.b
            int r4 = r2 + 1
            r2 = r8[r2]
            r2 = r3[r2]
            r3 = -1
            if (r4 >= r0) goto L1d
            if (r2 == r3) goto L1b
            goto L1d
        L1b:
            r2 = r4
            goto Ld
        L1d:
            if (r2 != r3) goto L21
            goto L87
        L21:
            byte[] r5 = com.unicom.xiaowo.login.a.b.b
            int r6 = r4 + 1
            r4 = r8[r4]
            r4 = r5[r4]
            if (r6 >= r0) goto L30
            if (r4 == r3) goto L2e
            goto L30
        L2e:
            r4 = r6
            goto L21
        L30:
            if (r4 != r3) goto L33
            goto L87
        L33:
            int r2 = r2 << 2
            r5 = r4 & 48
            int r5 = r5 >>> 4
            r2 = r2 | r5
            r1.write(r2)
        L3d:
            int r2 = r6 + 1
            r5 = r8[r6]
            r6 = 61
            if (r5 != r6) goto L4a
            byte[] r8 = r1.toByteArray()
            return r8
        L4a:
            byte[] r7 = com.unicom.xiaowo.login.a.b.b
            r5 = r7[r5]
            if (r2 >= r0) goto L55
            if (r5 == r3) goto L53
            goto L55
        L53:
            r6 = r2
            goto L3d
        L55:
            if (r5 != r3) goto L58
            goto L87
        L58:
            r4 = r4 & 15
            int r4 = r4 << 4
            r7 = r5 & 60
            int r7 = r7 >>> 2
            r4 = r4 | r7
            r1.write(r4)
        L64:
            int r4 = r2 + 1
            r2 = r8[r2]
            if (r2 != r6) goto L6f
            byte[] r8 = r1.toByteArray()
            return r8
        L6f:
            byte[] r7 = com.unicom.xiaowo.login.a.b.b
            r2 = r7[r2]
            if (r4 >= r0) goto L7a
            if (r2 == r3) goto L78
            goto L7a
        L78:
            r2 = r4
            goto L64
        L7a:
            if (r2 != r3) goto L7d
            goto L87
        L7d:
            r3 = r5 & 3
            int r3 = r3 << 6
            r2 = r2 | r3
            r1.write(r2)
            r2 = r4
            goto Lb
        L87:
            byte[] r8 = r1.toByteArray()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.xiaowo.login.a.b.b(java.lang.String):byte[]");
    }

    public static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = bArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            int i3 = i2 + 1;
            int i4 = bArr[i2] & 255;
            if (i3 == length) {
                stringBuffer.append(a[i4 >>> 2]);
                stringBuffer.append(a[(i4 & 3) << 4]);
                stringBuffer.append("==");
                break;
            }
            int i5 = i3 + 1;
            int i6 = bArr[i3] & 255;
            if (i5 == length) {
                stringBuffer.append(a[i4 >>> 2]);
                stringBuffer.append(a[((i4 & 3) << 4) | ((i6 & 240) >>> 4)]);
                stringBuffer.append(a[(i6 & 15) << 2]);
                stringBuffer.append(ContainerUtils.KEY_VALUE_DELIMITER);
                break;
            }
            int i7 = i5 + 1;
            int i8 = bArr[i5] & 255;
            stringBuffer.append(a[i4 >>> 2]);
            stringBuffer.append(a[((i4 & 3) << 4) | ((i6 & 240) >>> 4)]);
            stringBuffer.append(a[((i6 & 15) << 2) | ((i8 & 192) >>> 6)]);
            stringBuffer.append(a[i8 & 63]);
            i2 = i7;
        }
        return stringBuffer.toString();
    }
}
