package tv.danmaku.ijk.media.alpha.util;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.jdsdk.network.dependency.IStatInfoConfig;
import tv.danmaku.ijk.media.domain.entity.AlphaAnimBean;

/* loaded from: classes11.dex */
public class UrlUtil {
    private static final String TAG = "UrlUtil";

    public static AlphaAnimBean parseUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        AlphaAnimBean alphaAnimBean = new AlphaAnimBean();
        if (str.indexOf(63) != -1) {
            alphaAnimBean.setDownloadUrl(str.substring(0, str.indexOf(63)));
            for (String str2 : str.substring(str.indexOf(63) + 1).split(ContainerUtils.FIELD_DELIMITER)) {
                if (!TextUtils.isEmpty(str2) && str2.contains(IStatInfoConfig.REPORT_PARAM_SIGN)) {
                    String substring = str2.substring(5);
                    String str3 = "md5 = " + substring;
                    alphaAnimBean.setMd5(substring);
                }
                if (!TextUtils.isEmpty(str2) && str2.contains("expireTime=")) {
                    String substring2 = str2.substring(11);
                    String str4 = "expireTime = " + substring2;
                    alphaAnimBean.setExpireTime(substring2);
                }
            }
        } else {
            alphaAnimBean.setDownloadUrl(str);
        }
        return alphaAnimBean;
    }
}
