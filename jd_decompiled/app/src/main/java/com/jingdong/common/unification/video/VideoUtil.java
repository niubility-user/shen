package com.jingdong.common.unification.video;

import android.os.Environment;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.UnLog;
import com.jingdong.common.unification.filter.FilterTools;
import com.jingdong.common.unification.video.player.VideoPlayView;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.File;

/* loaded from: classes6.dex */
public class VideoUtil {
    private static final String TAG = "VideoUtil";

    public static ViewGroup.LayoutParams changeVideoViewParams(VideoPlayView videoPlayView) {
        float f2;
        float f3;
        if (videoPlayView == null) {
            return null;
        }
        float videoWidth = videoPlayView.getVideoWidth();
        float videoHeight = videoPlayView.getVideoHeight();
        if (videoHeight > videoWidth) {
            f2 = DpiUtil.getHeight(JdSdk.getInstance().getApplicationContext());
            f3 = (videoWidth * f2) / videoHeight;
        } else {
            float width = DpiUtil.getWidth(JdSdk.getInstance().getApplicationContext());
            f2 = (videoHeight * width) / videoWidth;
            f3 = width;
        }
        ViewGroup.LayoutParams layoutParams = videoPlayView.getLayoutParams();
        layoutParams.width = (int) f3;
        layoutParams.height = (int) f2;
        return layoutParams;
    }

    private static float createCenterPictureRate(int i2, int i3) {
        return Math.min(i2 / DpiUtil.getWidth(JdSdk.getInstance().getApplicationContext()), i3 / DpiUtil.getHeight(JdSdk.getInstance().getApplicationContext()));
    }

    public static String createFilterVideoName(String str, FilterTools.FilterType filterType, boolean z) {
        String name = new File(str).getName();
        if (!TextUtils.isEmpty(name)) {
            String[] split = name.split("\\.");
            if (split.length >= 2) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(System.currentTimeMillis());
                stringBuffer.append(CartConstant.KEY_YB_INFO_LINK);
                if (z) {
                    stringBuffer.append("cut");
                    stringBuffer.append(CartConstant.KEY_YB_INFO_LINK);
                }
                stringBuffer.append("filter-");
                stringBuffer.append(FilterTools.getFilterName(filterType));
                stringBuffer.append(OrderISVUtil.MONEY_DECIMAL);
                stringBuffer.append(split[1]);
                String stringBuffer2 = stringBuffer.toString();
                if (UnLog.D) {
                    UnLog.e(TAG, "outName:" + stringBuffer2);
                    return stringBuffer2;
                }
                return stringBuffer2;
            }
        }
        return "";
    }

    public static String createOutVideoPath(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/jd/video/";
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (UnLog.D) {
            UnLog.d(TAG, "videoOutPath:" + str2);
        }
        return str2;
    }

    public static int getVideoBitRate(int i2, int i3) {
        return (i2 >= 1920 || i3 >= 1920) ? 4194304 : 2097152;
    }
}
