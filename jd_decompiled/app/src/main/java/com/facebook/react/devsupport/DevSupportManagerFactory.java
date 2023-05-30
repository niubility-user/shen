package com.facebook.react.devsupport;

import android.content.Context;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.packagerconnection.RequestHandler;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class DevSupportManagerFactory {
    private static final String DEVSUPPORT_IMPL_CLASS = "DevSupportManagerImpl";
    private static final String DEVSUPPORT_IMPL_PACKAGE = "com.facebook.react.devsupport";

    public static DevSupportManager create(Context context, ReactInstanceManagerDevHelper reactInstanceManagerDevHelper, @Nullable String str, boolean z, int i2) {
        return create(context, reactInstanceManagerDevHelper, str, z, null, null, i2, null);
    }

    public static DevSupportManager create(Context context, ReactInstanceManagerDevHelper reactInstanceManagerDevHelper, @Nullable String str, boolean z, @Nullable RedBoxHandler redBoxHandler, @Nullable DevBundleDownloadListener devBundleDownloadListener, int i2, @Nullable Map<String, RequestHandler> map) {
        if (!z) {
            return new DisabledDevSupportManager();
        }
        try {
            return (DevSupportManager) Class.forName(DEVSUPPORT_IMPL_PACKAGE + OrderISVUtil.MONEY_DECIMAL + DEVSUPPORT_IMPL_CLASS).getConstructor(Context.class, ReactInstanceManagerDevHelper.class, String.class, Boolean.TYPE, RedBoxHandler.class, DevBundleDownloadListener.class, Integer.TYPE, Map.class).newInstance(context, reactInstanceManagerDevHelper, str, Boolean.TRUE, redBoxHandler, devBundleDownloadListener, Integer.valueOf(i2), map);
        } catch (Exception e2) {
            throw new RuntimeException("Requested enabled DevSupportManager, but DevSupportManagerImpl class was not found or could not be created", e2);
        }
    }
}
