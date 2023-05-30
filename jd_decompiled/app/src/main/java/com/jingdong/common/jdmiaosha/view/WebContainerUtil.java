package com.jingdong.common.jdmiaosha.view;

import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.web.IRouterParams;
import de.greenrobot.event.EventBus;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class WebContainerUtil {
    public static final String CEK_VALUE = "value";
    public static final String EVENT_HIDEN_TABS = "hidenTabs";
    public static final String EVENT_SWITCH_TAB = "switchTab";

    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        if (r1 == 1) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003e, code lost:
        hidenTab(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void containerEvent(com.jingdong.common.web.IRouterParams r5) {
        /*
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Exception -> L45
            java.lang.String r1 = r5.getRouterParam()     // Catch: java.lang.Exception -> L45
            r0.<init>(r1)     // Catch: java.lang.Exception -> L45
            java.lang.String r1 = "type"
            java.lang.String r0 = r0.optString(r1)     // Catch: java.lang.Exception -> L45
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L45
            if (r1 != 0) goto L45
            r1 = -1
            int r2 = r0.hashCode()     // Catch: java.lang.Exception -> L45
            r3 = 866573578(0x33a6dd0a, float:7.770184E-8)
            r4 = 1
            if (r2 == r3) goto L30
            r3 = 1651364801(0x626dd3c1, float:1.0967842E21)
            if (r2 == r3) goto L26
            goto L39
        L26:
            java.lang.String r2 = "switchTab"
            boolean r0 = r0.equals(r2)     // Catch: java.lang.Exception -> L45
            if (r0 == 0) goto L39
            r1 = 0
            goto L39
        L30:
            java.lang.String r2 = "hidenTabs"
            boolean r0 = r0.equals(r2)     // Catch: java.lang.Exception -> L45
            if (r0 == 0) goto L39
            r1 = 1
        L39:
            if (r1 == 0) goto L42
            if (r1 == r4) goto L3e
            goto L45
        L3e:
            hidenTab(r5)     // Catch: java.lang.Exception -> L45
            goto L45
        L42:
            switchTab(r5)     // Catch: java.lang.Exception -> L45
        L45:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdmiaosha.view.WebContainerUtil.containerEvent(com.jingdong.common.web.IRouterParams):void");
    }

    private static void hidenTab(IRouterParams iRouterParams) {
        try {
            int optInt = new JSONObject(iRouterParams.getRouterParam()).optInt("value");
            EventBus.getDefault().post(new BaseEvent(EVENT_HIDEN_TABS, optInt + ""));
        } catch (Exception unused) {
        }
    }

    private static void switchTab(IRouterParams iRouterParams) {
        try {
            int optInt = new JSONObject(iRouterParams.getRouterParam()).optInt("value");
            EventBus.getDefault().post(new BaseEvent(EVENT_SWITCH_TAB, optInt + ""));
        } catch (Exception unused) {
        }
    }
}
