package com.jingdong.common.jdreactFramework.listener;

import androidx.collection.ArrayMap;
import com.jingdong.common.utils.StatisticsReportUtil;
import java.util.Map;

/* loaded from: classes5.dex */
public class JDReactNativeDeviceInfoListener implements NativeDeviceInfoListener {
    private static final String DEVICEUUID_KEY = "DEVUUID";

    @Override // com.jingdong.common.jdreactFramework.listener.NativeDeviceInfoListener
    public Map<String, Object> getConstants() {
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put(DEVICEUUID_KEY, StatisticsReportUtil.readDeviceUUID());
        return arrayMap;
    }
}
