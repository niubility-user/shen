package com.jd.libs.jdmbridge.base.proxy.share;

import com.jd.libs.jdmbridge.base.base.IProxy;
import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public abstract class ShareBridgeProxy implements IProxy, IShareAdapter {
    public static final String PROXY_NAME = "shareHelper";

    @Override // com.jd.libs.jdmbridge.base.base.IProxy
    public String getName() {
        return "shareHelper";
    }

    @Override // com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter
    public IShareAdapter.ShareInfo parseShareInfoFromJson(JSONObject jSONObject) {
        if (jSONObject != null) {
            IShareAdapter.ShareInfo shareInfo = new IShareAdapter.ShareInfo();
            shareInfo.title = jSONObject.optString("title", "");
            shareInfo.content = jSONObject.optString("content", "");
            shareInfo.shareActionType = jSONObject.optString("shareActionType", "");
            shareInfo.url = jSONObject.optString("url", "");
            shareInfo.img = jSONObject.optString("img", "");
            shareInfo.timeline_title = jSONObject.optString("timeline_title", "");
            shareInfo.channel = jSONObject.optString("channel", "");
            shareInfo.event_id = jSONObject.optString("eventId", "");
            return shareInfo;
        }
        return null;
    }
}
