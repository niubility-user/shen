package com.jingdong.common.videoplayer;

import android.view.View;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes6.dex */
public class VideoPlayerUtils {
    public static void setActivityFullScreen(View view) {
        view.setSystemUiVisibility(R2.color.tradein_result_btn_add_your_device_textcolor);
    }

    public static void setActivityNotFullScreen(View view) {
        view.setSystemUiVisibility(0);
    }
}
