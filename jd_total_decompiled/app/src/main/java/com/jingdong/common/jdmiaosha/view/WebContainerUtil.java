package com.jingdong.common.jdmiaosha.view;

import android.text.TextUtils;
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
    */
    public static void containerEvent(IRouterParams iRouterParams) {
        try {
            String optString = new JSONObject(iRouterParams.getRouterParam()).optString("type");
            if (TextUtils.isEmpty(optString)) {
                return;
            }
            char c2 = '\uffff';
            int hashCode = optString.hashCode();
            if (hashCode != 866573578) {
                if (hashCode == 1651364801 && optString.equals(EVENT_SWITCH_TAB)) {
                    c2 = 0;
                }
            } else if (optString.equals(EVENT_HIDEN_TABS)) {
                c2 = 1;
            }
            switchTab(iRouterParams);
        } catch (Exception unused) {
        }
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
