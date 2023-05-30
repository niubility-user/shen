package com.jingdong.common.web;

import com.jd.libs.jdmbridge.base.JDBridgeManager;
import com.jingdong.common.ColorQueryBridge;
import com.jingdong.common.SwitchQueryBridge;

/* loaded from: classes6.dex */
public class XBridgeLibManager {
    public static void registerPlugin() {
        JDBridgeManager.registerBridgePlugin("SwitchQueryPlugin", SwitchQueryBridge.class);
        JDBridgeManager.registerBridgePlugin("ColorQueryPlugin", ColorQueryBridge.class);
        JDBridgeManager.registerBridgePlugin("XCachePlugin", XCacheTest.class);
        JDBridgeManager.registerBridgePlugin("DogDoorPlugin", com.jd.libs.xdog.c.class);
        JDBridgeManager.registerBridgePlugin("WebViewToolPlugin", WebViewToolPlugin.class);
    }
}
