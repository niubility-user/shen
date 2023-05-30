package com.jingdong.union.a;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes12.dex */
public class b {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* loaded from: classes12.dex */
    class a implements Comparator<Map.Entry<String, String>> {
        a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public int compare(Map.Entry<String, String> entry, Map.Entry<String, String> entry2) {
            return entry.getKey().toString().compareTo(entry2.getKey());
        }
    }

    public static String a(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            try {
                messageDigest.update(str.getBytes("utf-8"));
                return new String(c(messageDigest.digest()));
            } catch (UnsupportedEncodingException unused) {
                throw new IllegalStateException("System doesn't support your  EncodingException.");
            }
        } catch (NoSuchAlgorithmException unused2) {
            throw new IllegalStateException("System doesn't support MD5 algorithm.");
        }
    }

    public static String b(Map<String, String> map, String str) {
        try {
            ArrayList arrayList = new ArrayList(map.entrySet());
            Collections.sort(arrayList, new a());
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                Map.Entry entry = (Map.Entry) arrayList.get(i2);
                if (!TextUtils.isEmpty((CharSequence) entry.getKey())) {
                    String str2 = (String) entry.getKey();
                    String str3 = (String) entry.getValue();
                    if (!TextUtils.isEmpty(str3) && !"seData".equals(str2) && !"sourceExt".equals(str2)) {
                        if (i2 < arrayList.size() - 1) {
                            sb.append(str3 + ContainerUtils.FIELD_DELIMITER);
                        } else {
                            sb.append(str3);
                        }
                    }
                }
            }
            sb.append(str);
            String sb2 = sb.toString();
            try {
                f.a("before md5 " + sb2);
                return a(sb2).toUpperCase();
            } catch (Exception unused) {
                return sb2;
            }
        } catch (Exception unused2) {
            return "";
        }
    }

    public static char[] c(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length << 1];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = i2 + 1;
            char[] cArr2 = a;
            cArr[i2] = cArr2[(bArr[i3] & 240) >>> 4];
            i2 = i4 + 1;
            cArr[i4] = cArr2[bArr[i3] & 15];
        }
        return cArr;
    }
}
