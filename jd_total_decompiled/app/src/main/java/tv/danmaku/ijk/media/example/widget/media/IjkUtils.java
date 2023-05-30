package tv.danmaku.ijk.media.example.widget.media;

import android.net.Uri;
import android.text.TextUtils;
import java.net.URLDecoder;
import tv.danmaku.ijk.media.utils.DebugLog;

/* loaded from: classes11.dex */
public class IjkUtils {
    public static Uri parseVideoPath(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            DebugLog.d("ijkvideoview url:", str);
            String decode = URLDecoder.decode(str, "utf-8");
            Uri parse = Uri.parse(decode);
            int indexOf = decode.indexOf("?");
            if (indexOf < 0) {
                return Uri.parse(str);
            }
            String substring = decode.substring(0, indexOf);
            String queryParameter = parse.getQueryParameter("source");
            String queryParameter2 = parse.getQueryParameter("h265");
            if (("1".equals(queryParameter) || "2".equals(queryParameter)) && !TextUtils.isEmpty(queryParameter2)) {
                String str2 = substring.substring(0, substring.lastIndexOf("/") + 1) + queryParameter2;
                int indexOf2 = str.indexOf("?");
                if (indexOf2 != -1) {
                    str2 = str2 + str.substring(indexOf2);
                }
                DebugLog.d("ijkvideoview h265 url:", str2);
                return Uri.parse(str2);
            }
            return Uri.parse(str);
        } catch (Exception unused) {
            return Uri.parse(str);
        }
    }

    public static String replace(String str, String str2, String str3, int i2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str3 == null || i2 == 0) {
            return str;
        }
        int i3 = 0;
        int indexOf = str.indexOf(str2, 0);
        if (indexOf == -1) {
            return str;
        }
        int length = str2.length();
        int length2 = str3.length() - length;
        if (length2 < 0) {
            length2 = 0;
        }
        int i4 = 64;
        if (i2 < 0) {
            i4 = 16;
        } else if (i2 <= 64) {
            i4 = i2;
        }
        StringBuffer stringBuffer = new StringBuffer(str.length() + (length2 * i4));
        while (indexOf != -1) {
            stringBuffer.append(str.substring(i3, indexOf));
            stringBuffer.append(str3);
            i3 = indexOf + length;
            i2--;
            if (i2 == 0) {
                break;
            }
            indexOf = str.indexOf(str2, i3);
        }
        stringBuffer.append(str.substring(i3));
        return stringBuffer.toString();
    }

    public static String replaceOnce(String str, String str2, String str3) {
        return replace(str, str2, str3, 1);
    }
}
