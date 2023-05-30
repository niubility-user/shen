package com.unicom.xiaowo.login.a;

import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.wireless.sdk.intelligent.assistant.audio.record.Constant;
import com.jingdong.common.utils.LangUtils;
import java.io.ByteArrayOutputStream;
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
    */
    public static byte[] b(String str) {
        int i2;
        byte b2;
        int i3;
        byte b3;
        int i4;
        byte b4;
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
        int i5 = 0;
        while (i5 < length) {
            while (true) {
                i2 = i5 + 1;
                b2 = b[bytes[i5]];
                if (i2 >= length || b2 != -1) {
                    break;
                }
                i5 = i2;
            }
            if (b2 == -1) {
                break;
            }
            while (true) {
                i3 = i2 + 1;
                b3 = b[bytes[i2]];
                if (i3 >= length || b3 != -1) {
                    break;
                }
                i2 = i3;
            }
            if (b3 == -1) {
                break;
            }
            byteArrayOutputStream.write((b2 << 2) | ((b3 & 48) >>> 4));
            while (true) {
                i4 = i3 + 1;
                byte b5 = bytes[i3];
                if (b5 == 61) {
                    return byteArrayOutputStream.toByteArray();
                }
                b4 = b[b5];
                if (i4 >= length || b4 != -1) {
                    break;
                }
                i3 = i4;
            }
            if (b4 == -1) {
                break;
            }
            byteArrayOutputStream.write(((b3 & 15) << 4) | ((b4 & Constant.MAX_DURATION_DEFAULT) >>> 2));
            while (true) {
                int i6 = i4 + 1;
                byte b6 = bytes[i4];
                if (b6 == 61) {
                    return byteArrayOutputStream.toByteArray();
                }
                byte b7 = b[b6];
                if (i6 >= length || b7 != -1) {
                    break;
                }
                i4 = i6;
            }
        }
        return byteArrayOutputStream.toByteArray();
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
