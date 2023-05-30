package com.jingdong.sdk.platform;

import android.text.TextUtils;
import com.jingdong.sdk.platform.floor.FloorManager;
import com.jingdong.sdk.platform.floor.FloorManagerProxy;
import com.jingdong.sdk.platform.manager.ViewHolderManager;
import com.jingdong.sdk.platform.manager.ViewHolderManagerProxy;
import com.jingdong.sdk.platform.utils.PlatformTools;

/* loaded from: classes9.dex */
public class PlatformHelper {
    public static FloorManager getFloorManager(String str) {
        return FloorManagerProxy.getInstances(str);
    }

    public static int getFloorTypeById(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return 0;
        }
        int typeById = ViewHolderManagerProxy.getInstance().getTypeById(str2);
        if (typeById <= 0) {
            if (PlatformTools.D) {
                PlatformTools.d("PlatformHelper", " getItemViewType " + str2);
            }
            return FloorManagerProxy.getInstances(str).getTypeById(str2);
        }
        return typeById;
    }

    public static ViewHolderManager getViewHolderManager() {
        return ViewHolderManagerProxy.getInstance();
    }

    public static boolean isFloorRegister(String str, String str2) {
        return getFloorTypeById(str, str2) > 0;
    }
}
