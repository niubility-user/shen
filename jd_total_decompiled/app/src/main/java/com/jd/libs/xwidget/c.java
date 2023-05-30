package com.jd.libs.xwidget;

import com.jd.xbridge.base.IBridgeCallback;
import com.jd.xbridge.base.IBridgePlugin;
import com.jd.xbridge.base.IBridgeWebView;

/* loaded from: classes16.dex */
public class c implements IBridgePlugin {
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0033, code lost:
        if (r5 == 1) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0036, code lost:
        com.jd.libs.xwidget.a.a("hybrid-image").newInstance().callNative(r7, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0045, code lost:
        return true;
     */
    @Override // com.jd.xbridge.base.IBridgePlugin
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean execute(IBridgeWebView iBridgeWebView, String str, String str2, IBridgeCallback iBridgeCallback) {
        String.format("action:%s, params:%s", str, str2);
        char c2 = '\uffff';
        try {
            int hashCode = str.hashCode();
            if (hashCode != 1499608255) {
                if (hashCode == 1511497695 && str.equals("hybridVideo")) {
                    c2 = 0;
                }
            } else if (str.equals("hybridImage")) {
                c2 = 1;
            }
            a.a("hybrid-video").newInstance().callNative(str2, iBridgeCallback);
            return true;
        } catch (Exception e2) {
            e2.toString();
        }
        return false;
    }
}
