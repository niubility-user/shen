package com.jingdong.common.web.util;

import android.os.Bundle;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.openlinktime.OpenLinkTimeManager;

/* loaded from: classes12.dex */
public class BundleUtils {
    public static boolean isFromDeepLink(Bundle bundle) {
        if (bundle != null) {
            try {
                return OpenLinkTimeManager.getInstance().getOpenJsonParam(bundle.getInt(OpenAppJumpController.KEY_OPEN_LINK_PARAMS, 0)) != null;
            } catch (Throwable unused) {
                return false;
            }
        }
        return false;
    }
}
