package tv.danmaku.ijk.media.utils;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.sdk.platform.business.personal.R2;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import tv.danmaku.ijk.media.JDPlayerConstant;

/* loaded from: classes11.dex */
public class PlayerStringUtils {
    public static boolean canPreSetLoop(Uri uri) {
        if (uri == null || TextUtils.isEmpty(uri.toString())) {
            return true;
        }
        return !uri.toString().endsWith(JDPlayerConstant.LIVE_REPLAY_SUFFIX);
    }

    public static String formatTime(long j2) {
        DecimalFormat decimalFormat = new DecimalFormat("0.000000");
        double d = j2;
        Double.isNaN(d);
        return decimalFormat.format(d / 1000.0d);
    }

    @SuppressLint({"DefaultLocale"})
    public static String generateReportTime() {
        double currentTimeMillis = System.currentTimeMillis();
        Double.isNaN(currentTimeMillis);
        return String.format("%.6f", Double.valueOf(currentTimeMillis / 1000.0d));
    }

    public static String generateTime(int i2) {
        int i3 = i2 / 1000;
        int i4 = i3 % 60;
        int i5 = (i3 / 60) % 60;
        int i6 = i3 / R2.color.c_f2f3f3;
        return i6 > 0 ? String.format("%02d:%02d:%02d", Integer.valueOf(i6), Integer.valueOf(i5), Integer.valueOf(i4)) : String.format("%02d:%02d", Integer.valueOf(i5), Integer.valueOf(i4));
    }

    public static String getOriginUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (str.startsWith(JDPlayerConstant.IJK_CACHE_HEAD) && str.length() > 17) {
            str = str.substring(17);
        }
        return (!str.startsWith(JDPlayerConstant.HTTP_HOOK_HEAD) || str.length() <= 12) ? str : str.substring(12);
    }

    public static int getProtocolType(Uri uri) {
        if (uri != null) {
            String uri2 = uri.toString();
            if (uri2.contains(JDPlayerConstant.LIVE_QUICS_HEAD)) {
                return 1;
            }
            if (uri2.contains("127.0.0.1")) {
                return 2;
            }
        }
        return 0;
    }

    public static String getStreamContainer(String str) {
        if (TextUtils.isEmpty(str)) {
            return "unknown";
        }
        int indexOf = str.indexOf("?");
        if (indexOf != -1) {
            str = str.substring(0, indexOf);
        }
        if (str.contains(OrderISVUtil.MONEY_DECIMAL)) {
            String[] split = str.split("\\.");
            if (split.length > 0) {
                return split[split.length - 1];
            }
        }
        return "unknown";
    }

    public static boolean isLiveDomain(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.contains("jdpull.jd.com") || str.contains("jdpull-las.jd.com");
    }

    public static boolean isLocalFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return isLocalFile(Uri.parse(str.trim()));
    }

    public static boolean isMp4Url(String str) {
        return "mp4".equals(getStreamContainer(str));
    }

    public static boolean isQuicProtocol(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith(JDPlayerConstant.LIVE_QUIC_HEAD) || str.startsWith(JDPlayerConstant.LIVE_QUICS_HEAD);
    }

    public static boolean isRtmpStream(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith(JDPlayerConstant.LIVE_RTMP_HEAD) || str.startsWith(JDPlayerConstant.LIVE_RTMPS_HEAD) || str.endsWith(JDPlayerConstant.LIVE_REPLAY_SUFFIX);
    }

    public static boolean isSupportMcdn(String str) {
        Uri parse;
        if (TextUtils.isEmpty(str) || (parse = Uri.parse(str.trim())) == null || parse.getHost() == null) {
            return false;
        }
        String host = parse.getHost();
        return "jvod.300hu.com".equals(host) || "dvod.300hu.com".equals(host) || "vod.300hu.com".equals(host) || "jdvod.300hu.com".equals(host);
    }

    public static boolean isValidUrl(String str) {
        if (!str.startsWith(JDPlayerConstant.HTTP_HOOK_HEAD) && !str.startsWith(JDPlayerConstant.LIVE_HOOK_HEAD)) {
            try {
                URI uri = new URI(str);
                if (uri.getHost() == null) {
                    return false;
                }
                return uri.getScheme().equalsIgnoreCase("http") || uri.getScheme().equalsIgnoreCase("https") || uri.getScheme().equalsIgnoreCase("quic") || uri.getScheme().equalsIgnoreCase("quics");
            } catch (URISyntaxException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public static String millisToString(long j2) {
        return millisToString(j2, false);
    }

    public static String strMD5(String str) {
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

    public static String string2JsStr(String str) {
        return TextUtils.isEmpty(str) ? str : str.replace("\\", "\\\\").replace("\"", "\\\"").replace("'", "\\'").replace(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, "\\n").replace("\r", "\\r").replace("\f", "\\\f").replace("\u2028", "\\u2028").replace("\u2029", "\\u2029");
    }

    static String millisToString(long j2, boolean z) {
        boolean z2 = j2 < 0;
        long abs = Math.abs(j2) / 1000;
        int i2 = (int) (abs % 60);
        long j3 = abs / 60;
        int i3 = (int) (j3 % 60);
        long j4 = j3 / 60;
        int i4 = (int) j4;
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        decimalFormat.applyPattern("00");
        if (!z) {
            if (j4 > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(z2 ? "-" : "");
                sb.append(i4);
                sb.append(":");
                sb.append(decimalFormat.format(i3));
                sb.append(":");
                sb.append(decimalFormat.format(i2));
                return sb.toString();
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(z2 ? "-" : "");
            sb2.append(i3);
            sb2.append(":");
            sb2.append(decimalFormat.format(i2));
            return sb2.toString();
        } else if (j4 > 0) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(z2 ? "-" : "");
            sb3.append(i4);
            sb3.append(JshopConst.JSHOP_PROMOTIO_H);
            sb3.append(decimalFormat.format(i3));
            sb3.append("min");
            return sb3.toString();
        } else if (i3 > 0) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(z2 ? "-" : "");
            sb4.append(i3);
            sb4.append("min");
            return sb4.toString();
        } else {
            StringBuilder sb5 = new StringBuilder();
            sb5.append(z2 ? "-" : "");
            sb5.append(i2);
            sb5.append("s");
            return sb5.toString();
        }
    }

    public static boolean isLocalFile(Uri uri) {
        if (uri != null) {
            try {
                if (TextUtils.isEmpty(uri.getScheme())) {
                    return true;
                }
                return "file".equals(uri.getScheme());
            } catch (Exception unused) {
                return false;
            }
        }
        return false;
    }

    public static boolean isQuicProtocol(Uri uri) {
        if (uri == null) {
            return false;
        }
        return isQuicProtocol(uri.toString());
    }
}
