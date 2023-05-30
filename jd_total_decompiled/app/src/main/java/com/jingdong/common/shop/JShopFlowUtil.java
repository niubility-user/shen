package com.jingdong.common.shop;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public final class JShopFlowUtil {
    private static final Map<String, Integer> FLOW_STATE_MAP = new HashMap(128);
    public static final int JSHOP_FLOW_STATE_CANCEL = 0;
    public static final int JSHOP_FLOW_STATE_DEFAULT = -1;
    public static final int JSHOP_FLOW_STATE_FOLLOWED = 1;

    public static int getFlowState(String str) {
        Map<String, Integer> map = FLOW_STATE_MAP;
        if (map.get(str) == null) {
            return -1;
        }
        return map.get(str).intValue();
    }

    public static void setFlowState(String str, boolean z) {
        if (!z) {
            FLOW_STATE_MAP.put(str, 0);
        } else {
            FLOW_STATE_MAP.put(str, 1);
        }
    }
}
