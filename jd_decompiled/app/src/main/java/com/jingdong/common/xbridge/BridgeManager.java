package com.jingdong.common.xbridge;

import com.jd.lib.babel.task.common.TTTTaskPlugin;
import com.jd.libs.jdmbridge.base.JDBridgeManager;
import com.jd.xbridge.XBridgeManager;
import com.jingdong.common.web.XBridgeLibManager;
import com.jingdong.common.web.javainterface.WebPlugin;
import com.jingdong.common.widget.custom.liveplayer.plugin.LiveSmallWindowPlugin;

/* loaded from: classes12.dex */
public class BridgeManager {
    public static void registerPlugin() {
        XBridgeManager.INSTANCE.setWebDebug(false);
        XBridgeLibManager.registerPlugin();
        JDBridgeManager.registerBridgePlugin("TTTTaskPlugin", TTTTaskPlugin.class);
        JDBridgeManager.registerBridgePlugin("PerformancePlugin", MtaBridgePlugin.class);
        JDBridgeManager.registerBridgePlugin("AppUnitePlugin", AppUnitePlugin.class);
        JDBridgeManager.registerBridgePlugin("JDBStoragePlugin", JDBStoragePlugin.class);
        JDBridgeManager.registerBridgePlugin("TTTWebviewVisiblePlugin", TTTWebviewVisiblePlugin.class);
        JDBridgeManager.registerBridgePlugin("JDFollowPlugin", JDFollowPlugin.class);
        JDBridgeManager.registerBridgePlugin("ContainerPlugin", WebPlugin.class);
        JDBridgeManager.registerBridgePlugin("TTTWebviewTransParamPlugin", TTTTransParamPlugin.class);
        JDBridgeManager.registerBridgePlugin("XWidgetPlugin", com.jd.libs.xwidget.c.class);
        JDBridgeManager.registerBridgePlugin("WebViewDownloadPlugin", WebViewDownloadPlugin.class);
        JDBridgeManager.registerBridgePlugin("CashierCloseWebViewPlugin", CashierCloseWebViewPlugin.class);
        JDBridgeManager.registerBridgePlugin("JDLiveSmallWindow", LiveSmallWindowPlugin.class);
    }
}
