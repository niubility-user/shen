package com.jd.manto.d;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.manto.share.ShareProxyActivity;
import com.jingdong.common.entity.ShareImageInfo;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.jdma.minterface.BaseEvent;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.jsapi.refact.JSApiShareAppMessage;
import com.jingdong.manto.sdk.api.IShareManager;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoTrack;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class g extends JSApiShareAppMessage {

    /* loaded from: classes17.dex */
    class a implements IShareManager.ShareCallback {
        final /* synthetic */ MantoResultCallBack a;
        final /* synthetic */ Bundle b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ MantoCore f6654c;
        final /* synthetic */ String d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ Activity f6655e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ String f6656f;

        a(MantoResultCallBack mantoResultCallBack, Bundle bundle, MantoCore mantoCore, String str, Activity activity, String str2) {
            this.a = mantoResultCallBack;
            this.b = bundle;
            this.f6654c = mantoCore;
            this.d = str;
            this.f6655e = activity;
            this.f6656f = str2;
        }

        @Override // com.jingdong.manto.sdk.api.IShareManager.ShareCallback
        public void onShareCancel() {
            MantoResultCallBack mantoResultCallBack = this.a;
            if (mantoResultCallBack != null) {
                mantoResultCallBack.onCancel(new Bundle());
            }
        }

        @Override // com.jingdong.manto.sdk.api.IShareManager.ShareCallback
        public void onShareClickChannel(Bundle bundle) {
            String string = bundle.getString("shareChannel", "");
            int i2 = this.b.getInt("pageId", 0);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("shareChannel", string);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            g.this.dispatchEvent(this.f6654c, "onShareChannelTap", jSONObject, i2);
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("vapp_type", this.d);
                jSONObject2.put("channel", string);
            } catch (Exception e3) {
                MantoLog.e(DYConstants.DY_TRACK, e3);
            }
            MantoTrack.sendCommonDataWithExt(this.f6655e, "\u5206\u4eab", "applets_capsule_share_channel", this.f6656f, "\u5206\u4eab\u5f39\u7a97", "", jSONObject2.toString(), "applets_share", null);
        }

        @Override // com.jingdong.manto.sdk.api.IShareManager.ShareCallback
        public void onShareFailed(Bundle bundle) {
            MantoResultCallBack mantoResultCallBack = this.a;
            if (mantoResultCallBack != null) {
                mantoResultCallBack.onFailed(bundle);
            }
        }

        @Override // com.jingdong.manto.sdk.api.IShareManager.ShareCallback
        public void onShareSuccess(Bundle bundle) {
            MantoResultCallBack mantoResultCallBack = this.a;
            if (mantoResultCallBack != null) {
                mantoResultCallBack.onSuccess(bundle);
            }
        }
    }

    @Override // com.jingdong.manto.jsapi.refact.JSApiShareAppMessage
    public void shareMantoApp(MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        String string = bundle.getString("url");
        String string2 = bundle.getString("title");
        String string3 = bundle.getString("imageUrl");
        String string4 = bundle.getString("flag");
        String string5 = bundle.getString("desc");
        String string6 = bundle.getString("path");
        String string7 = bundle.getString("channel");
        String string8 = bundle.getString("mpId");
        String string9 = bundle.getString("mpPath");
        String string10 = bundle.getString("defaultLink");
        String string11 = bundle.getString("mpLocalImagePath");
        String string12 = bundle.getString("appid");
        String string13 = bundle.getString("type");
        boolean z = false;
        MantoLog.d("shareMsg", String.format("appId:%s, type:%s", string12, string13));
        int i2 = bundle.getInt("shareType", -1);
        if (string3 != null && string4 != null) {
            String str = TextUtils.isEmpty(string5) ? "" : string5;
            String str2 = TextUtils.isEmpty(string2) ? "" : string2;
            if (TextUtils.isEmpty(string7)) {
                string7 = "Wxfriends,Wxmoments,QQfriends";
            }
            String str3 = string7;
            String str4 = !TextUtils.isEmpty(string) ? string : string10;
            if ((i2 == 0 || i2 == 1 || i2 == 2) && !TextUtils.isEmpty(string8) && !TextUtils.isEmpty(string9)) {
                z = true;
            }
            ShareInfo shareInfo = new ShareInfo(str4, str2, str, string3, string4);
            shareInfo.setChannels(str3);
            if (z) {
                shareInfo.setMpId(string8);
                shareInfo.setMpType(i2);
                shareInfo.setMpPath(string9);
                if (string3.startsWith("jdfile://") && !TextUtils.isEmpty(string11)) {
                    shareInfo.setMpLocalIconPath(string11);
                } else {
                    shareInfo.setMpIconUrl(string3);
                }
            }
            if (bundle.containsKey("onlineImageUrl") || bundle.containsKey("localImagePath")) {
                ShareImageInfo shareImageInfo = new ShareImageInfo();
                if (bundle.containsKey("onlineImageUrl")) {
                    shareImageInfo.directUrl = bundle.getString("onlineImageUrl");
                }
                if (bundle.containsKey("localImagePath")) {
                    shareImageInfo.directPath = bundle.getString("localImagePath");
                }
                shareInfo.setShareImageInfo(shareImageInfo);
            }
            if (bundle.containsKey("keyShareChannel")) {
                JSONObject jSONObject = new JSONObject();
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("category", "jump");
                    jSONObject2.put("des", "jdmp");
                    jSONObject2.put("appId", string12);
                    jSONObject2.put("vapptype", string13);
                    if (!TextUtils.isEmpty(string6)) {
                        jSONObject2.put("path", Uri.decode(string6));
                    }
                    jSONObject2.put(BaseEvent.SCENE, "keyShare");
                    jSONObject.put("keyOpenapp", "openapp.jdmobile://virtual?params=" + jSONObject2.toString());
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("android", "9.1.6");
                    jSONObject3.put("apple", "9.1.6");
                    jSONObject.put("keyVer", jSONObject3);
                    jSONObject.put("keyChannel", bundle.getString("keyShareChannel"));
                    jSONObject.put("sourceCode", "JDMP");
                    MantoLog.d("shareMsg", "setKeyShareJsonStr: " + jSONObject.toString());
                    shareInfo.setKeyShareJsonStr(jSONObject.toString());
                } catch (Exception unused) {
                }
            }
            Activity activity = mantoCore != null ? mantoCore.getActivity() : null;
            if (mantoCore != null && activity != null) {
                ShareProxyActivity.x(activity, shareInfo, new a(mantoResultCallBack, bundle, mantoCore, string13, activity, string12));
                return;
            } else if (mantoResultCallBack != null) {
                mantoResultCallBack.onFailed(null);
                return;
            } else {
                return;
            }
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("errMessage", "imageUrl is null");
        mantoResultCallBack.onFailed(bundle2);
    }
}
