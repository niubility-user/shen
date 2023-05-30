package com.jingdong.common.xbridge;

import com.jd.libs.hybrid.datasnapshot.DataSnapshotSDK;
import com.jd.libs.hybrid.datasnapshot.ISnapshotListener;
import com.jd.xbridge.base.IBridgeCallback;
import com.jd.xbridge.base.IBridgePlugin;
import com.jd.xbridge.base.IBridgeWebView;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class JDBStoragePlugin implements IBridgePlugin {
    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(IBridgeCallback iBridgeCallback, Map map) {
        if (map != null) {
            iBridgeCallback.onSuccess(convertMap2Json(map));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void d(IBridgeCallback iBridgeCallback, Map map) {
        iBridgeCallback.onSuccess(convertMap2Json(map));
    }

    private JSONObject convertMap2Json(Map map) {
        if (map != null && map.size() > 0) {
            return new JSONObject(map);
        }
        return new JSONObject();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void f(IBridgeCallback iBridgeCallback, Map map) {
        iBridgeCallback.onSuccess(convertMap2Json(map));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void h(IBridgeCallback iBridgeCallback, Map map) {
        iBridgeCallback.onSuccess(convertMap2Json(map));
    }

    private JSONObject getWrappedFailResult() {
        HashMap hashMap = new HashMap(4);
        hashMap.put("result", JDReactConstant.FAILED);
        hashMap.put("data", "undefined");
        return new JSONObject(hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void j(IBridgeCallback iBridgeCallback, Map map) {
        iBridgeCallback.onSuccess(convertMap2Json(map));
    }

    @Override // com.jd.xbridge.base.IBridgePlugin
    public boolean execute(IBridgeWebView iBridgeWebView, String str, String str2, final IBridgeCallback iBridgeCallback) {
        if (iBridgeCallback == null) {
            return true;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            String optString = jSONObject.optString("key");
            String optString2 = jSONObject.optString("value");
            if ("getItem".equals(str)) {
                DataSnapshotSDK.INSTANCE.getInstance().getItem(optString, new ISnapshotListener() { // from class: com.jingdong.common.xbridge.c
                    @Override // com.jd.libs.hybrid.datasnapshot.ISnapshotListener
                    public final void onReceived(Map map) {
                        JDBStoragePlugin.this.b(iBridgeCallback, map);
                    }
                });
            } else if ("setItem".equals(str)) {
                DataSnapshotSDK.INSTANCE.getInstance().setItem(optString, optString2, new ISnapshotListener() { // from class: com.jingdong.common.xbridge.b
                    @Override // com.jd.libs.hybrid.datasnapshot.ISnapshotListener
                    public final void onReceived(Map map) {
                        JDBStoragePlugin.this.d(iBridgeCallback, map);
                    }
                });
            } else if ("removeItem".equals(str)) {
                DataSnapshotSDK.INSTANCE.getInstance().removeItem(optString, new ISnapshotListener() { // from class: com.jingdong.common.xbridge.e
                    @Override // com.jd.libs.hybrid.datasnapshot.ISnapshotListener
                    public final void onReceived(Map map) {
                        JDBStoragePlugin.this.f(iBridgeCallback, map);
                    }
                });
            } else if (CartConstant.KEY_LENGTH.equals(str)) {
                DataSnapshotSDK.INSTANCE.getInstance().length(new ISnapshotListener() { // from class: com.jingdong.common.xbridge.a
                    @Override // com.jd.libs.hybrid.datasnapshot.ISnapshotListener
                    public final void onReceived(Map map) {
                        JDBStoragePlugin.this.h(iBridgeCallback, map);
                    }
                });
            } else if ("getAllKeys".equals(str)) {
                DataSnapshotSDK.INSTANCE.getInstance().getAllKeys(new ISnapshotListener() { // from class: com.jingdong.common.xbridge.d
                    @Override // com.jd.libs.hybrid.datasnapshot.ISnapshotListener
                    public final void onReceived(Map map) {
                        JDBStoragePlugin.this.j(iBridgeCallback, map);
                    }
                });
            } else {
                iBridgeCallback.onSuccess(getWrappedFailResult());
            }
        } catch (Exception unused) {
            iBridgeCallback.onSuccess(getWrappedFailResult());
        }
        return true;
    }
}
