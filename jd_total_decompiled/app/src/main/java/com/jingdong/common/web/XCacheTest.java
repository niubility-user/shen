package com.jingdong.common.web;

import android.os.Handler;
import android.os.Looper;
import com.jd.hybrid.downloader.j;
import com.jd.xbridge.base.IBridgeCallback;
import com.jd.xbridge.base.IBridgePlugin;
import com.jd.xbridge.base.IBridgeWebView;
import com.jingdong.common.utils.ToastUtil;
import com.jingdong.corelib.utils.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class XCacheTest implements IBridgePlugin {
    @Override // com.jd.xbridge.base.IBridgePlugin
    public boolean execute(IBridgeWebView iBridgeWebView, String str, String str2, IBridgeCallback iBridgeCallback) {
        str.hashCode();
        if (!str.equals("getXCacheFile")) {
            if (str.equals("observeXCache")) {
                try {
                    JSONObject jSONObject = new JSONObject(str2);
                    j.l().n(jSONObject.optString("nameSpace"), jSONObject.optString("fileId"), new com.jd.hybrid.downloader.i() { // from class: com.jingdong.common.web.XCacheTest.1
                        @Override // com.jd.hybrid.downloader.i
                        public void update(final com.jd.hybrid.downloader.m.b bVar) {
                            Log.d("XCache", "notify update:" + bVar.getId());
                            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.common.web.XCacheTest.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    ToastUtil.showToast("\u6587\u4ef6\uff08id:" + bVar.getId() + "\uff09\u4e0b\u8f7d\u72b6\u6001\uff1a" + bVar.getStatus() + "\uff0c\u6587\u4ef6\u8def\u5f84\uff1a" + bVar.getFilePath());
                                }
                            });
                        }
                    });
                    iBridgeCallback.onSuccess("success");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    iBridgeCallback.onError(e2.getMessage());
                }
                return true;
            }
            return false;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject(str2);
            com.jd.hybrid.downloader.m.b k2 = j.l().k(jSONObject2.optString("nameSpace"), jSONObject2.optString("fileId"));
            if (k2 != null) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("id", k2.getId());
                jSONObject3.put("status", k2.getStatus());
                jSONObject3.put("path", k2.getFilePath());
                jSONArray.put(jSONObject3);
            }
            iBridgeCallback.onSuccess(jSONArray);
        } catch (JSONException e3) {
            e3.printStackTrace();
            iBridgeCallback.onError(e3.getMessage());
        }
        return true;
    }
}
