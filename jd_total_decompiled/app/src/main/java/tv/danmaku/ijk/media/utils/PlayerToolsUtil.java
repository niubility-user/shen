package tv.danmaku.ijk.media.utils;

import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
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
    */
    public static String getFileMD5(File file) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        MessageDigest messageDigest;
        FileInputStream fileInputStream3 = null;
        try {
            try {
                messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                fileInputStream = new FileInputStream(file);
            } catch (Exception e2) {
                e = e2;
                fileInputStream = null;
            } catch (Throwable th) {
                th = th;
                if (fileInputStream3 != null) {
                }
                throw th;
            }
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    messageDigest.update(bArr, 0, read);
                }
                String bigInteger = new BigInteger(1, messageDigest.digest()).toString(16);
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return bigInteger;
            } catch (Exception e4) {
                e = e4;
                e.printStackTrace();
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream3 = fileInputStream2;
            if (fileInputStream3 != null) {
                try {
                    fileInputStream3.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
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
