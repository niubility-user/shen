package com.jd.libs.jdmbridge.base.proxy.share;

import android.webkit.JavascriptInterface;
import com.jd.libs.jdmbridge.base.JDBridgeManager;
import com.jd.libs.jdmbridge.base.base.BaseBridgeAdapter;
import com.jd.libs.jdmbridge.base.callback.ShareCallback;
import com.jd.libs.jdmbridge.base.proxy.nav.INavAdapter;
import com.jd.libs.jdmbridge.base.proxy.nav.NavBridgeProxy;
import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.jd.libs.jdmbridge.base.utils.Util;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class ShareBridgeAdapter extends BaseBridgeAdapter<ShareBridgeProxy> implements IShareBridge {
    private ShareCallback mCallback;
    private IShareAdapter.ShareInfo mShareInfo;

    public ShareBridgeAdapter(JDBridgeManager jDBridgeManager) {
        super(jDBridgeManager);
        this.mShareInfo = new IShareAdapter.ShareInfo();
        this.mCallback = new ShareCallback() { // from class: com.jd.libs.jdmbridge.base.proxy.share.ShareBridgeAdapter.1
            {
                ShareBridgeAdapter.this = this;
            }

            @Override // com.jd.libs.jdmbridge.base.callback.ShareCallback
            public void onCancel() {
                if (((BaseBridgeAdapter) ShareBridgeAdapter.this).mBridgeManager != null) {
                    ((BaseBridgeAdapter) ShareBridgeAdapter.this).mBridgeManager.callback2H5("jdappShareRes", "{'shareChannel': '', 'shareResult':'2'}");
                }
            }

            @Override // com.jd.libs.jdmbridge.base.callback.ShareCallback
            public void onClick(String str) {
                if (((BaseBridgeAdapter) ShareBridgeAdapter.this).mBridgeManager != null) {
                    ((BaseBridgeAdapter) ShareBridgeAdapter.this).mBridgeManager.callback2H5("jdappShareRes", "{\"shareChannel\": \"" + str + "\"}");
                }
            }

            @Override // com.jd.libs.jdmbridge.base.callback.ShareCallback
            public void onComplete(String str) {
                if (((BaseBridgeAdapter) ShareBridgeAdapter.this).mBridgeManager != null) {
                    ((BaseBridgeAdapter) ShareBridgeAdapter.this).mBridgeManager.callback2H5("jdappShareRes", "{\"shareChannel\": \"" + str + "\", \"shareResult\":\"0\"}");
                }
            }

            @Override // com.jd.libs.jdmbridge.base.callback.ShareCallback
            public void onError(String str) {
                if (((BaseBridgeAdapter) ShareBridgeAdapter.this).mBridgeManager != null) {
                    ((BaseBridgeAdapter) ShareBridgeAdapter.this).mBridgeManager.callback2H5("jdappShareRes", "{\"shareChannel\": \"\", \"shareResult\":\"1\"}");
                }
            }
        };
    }

    @Override // com.jd.libs.jdmbridge.base.proxy.share.IShareBridge
    @JavascriptInterface
    public void initShare(String str) {
        try {
            if (str.startsWith("\"")) {
                str = str.substring(1);
            }
            if (str.endsWith("\"")) {
                str = str.substring(0, str.length() - 1);
            }
            JSONObject jSONObject = null;
            try {
                jSONObject = new JSONObject(str);
            } catch (Exception unused) {
            }
            if (jSONObject == null) {
                return;
            }
            Util.concatString("share jsonObject: ", jSONObject);
            IShareAdapter.ShareInfo parseShareInfoFromJson = getProxy().parseShareInfoFromJson(jSONObject);
            if (parseShareInfoFromJson == null) {
                return;
            }
            String str2 = parseShareInfoFromJson.shareActionType;
            if (IShareAdapter.SHARE_ACTION_OPEN.equals(str2)) {
                getProxy().sendShare(parseShareInfoFromJson, str, this.mCallback);
            } else if (IShareAdapter.SHARE_ACTION_PANE.equals(str2)) {
                getProxy().showShareDialog(parseShareInfoFromJson, str, this.mCallback);
            } else if ("S".equals(str2)) {
                this.mShareInfo = parseShareInfoFromJson;
                if (this.mBridgeManager.getProxy("MobileNavi") == null || !(this.mBridgeManager.getProxy("MobileNavi") instanceof NavBridgeProxy)) {
                    return;
                }
                ((NavBridgeProxy) this.mBridgeManager.getProxy("MobileNavi")).showRightButton(INavAdapter.BTN_TYPE_SHARE, 0);
            }
        } catch (Throwable unused2) {
        }
    }

    @Override // com.jd.libs.jdmbridge.base.base.BaseBridgeAdapter
    public ShareBridgeProxy getProxy() {
        return (ShareBridgeProxy) this.mBridgeManager.getProxy("shareHelper");
    }
}
