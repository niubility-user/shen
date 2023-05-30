package com.jingdong.sdk.platform.floor;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes10.dex */
public class FloorManagerProxy {
    private static Map<String, FloorManager> managerMap = new HashMap(3);

    public static synchronized FloorManager getInstances(String str) {
        FloorManager floorManager;
        synchronized (FloorManagerProxy.class) {
            floorManager = managerMap.isEmpty() ? null : managerMap.get(str);
            if (floorManager == null) {
                floorManager = new FloorManagerImpl(str);
                managerMap.put(str, floorManager);
            }
        }
        return floorManager;
    }
}
