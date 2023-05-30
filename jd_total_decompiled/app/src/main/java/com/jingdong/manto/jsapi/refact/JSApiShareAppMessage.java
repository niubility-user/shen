package com.jingdong.manto.jsapi.refact;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.b;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.t.c;
import com.jingdong.manto.t.d;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoThreadUtils;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class JSApiShareAppMessage extends AbstractMantoModule {
    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "ShareAppMessage";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        String str2;
        Bundle bundle2;
        d g2;
        d g3;
        try {
            if (bundle.containsKey("localImageUrl") && (g3 = c.g(bundle.getString(IMantoBaseModule.APP_UNIQUEID_ID_KEY), bundle.getString("localImageUrl", ""))) != null) {
                String str3 = g3.b;
                if (new File(str3).exists()) {
                    bundle.putString("localImagePath", str3);
                }
            }
            if (bundle.containsKey("imageUrl")) {
                String string = bundle.getString("imageUrl", "");
                if (string.startsWith("jdfile://") && (g2 = c.g(bundle.getString(IMantoBaseModule.APP_UNIQUEID_ID_KEY), string)) != null) {
                    String str4 = g2.b;
                    if (new File(str4).exists()) {
                        bundle.putString("mpLocalImagePath", str4);
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        mantoCore.getActivity();
        if (str.equals("shareAppMessageDirectly")) {
            String string2 = bundle.getString("type", "");
            if (!TextUtils.isEmpty(string2)) {
                str2 = string2;
                shareToWxFriends(mantoCore, bundle.getString("appid"), str2, bundle, mantoResultCallBack);
                return;
            }
            bundle2 = new Bundle();
            bundle2.putString("message", "info do not exist");
        } else if (!str.equals("shareAppMessage")) {
            return;
        } else {
            boolean z = bundle.getBoolean(IMantoBaseModule.SHARE_SUPPORT_KEY, true);
            String string3 = bundle.getString("type", "");
            if (!z) {
                bundle2 = new Bundle();
                bundle2.putString("message", "share app message fail, not allow to share");
            } else if (!TextUtils.isEmpty(string3)) {
                str2 = string3;
                shareToWxFriends(mantoCore, bundle.getString("appid"), str2, bundle, mantoResultCallBack);
                return;
            } else {
                bundle2 = new Bundle();
                bundle2.putString("message", "info do not exist");
            }
        }
        mantoResultCallBack.onFailed(bundle2);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle handleMethodSync(String str, MantoCore mantoCore, Bundle bundle) {
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object opt = jSONObject.opt(next);
            if ("type".equals(next)) {
                if (opt instanceof Integer) {
                    bundle.putInt("shareType", ((Integer) opt).intValue());
                } else if (opt instanceof String) {
                    try {
                        bundle.putInt("shareType", Integer.valueOf((String) opt).intValue());
                    } catch (Throwable unused) {
                    }
                }
            }
            if (opt instanceof Integer) {
                bundle.putInt(next, ((Integer) opt).intValue());
            } else if (opt instanceof Boolean) {
                bundle.putBoolean(next, ((Boolean) opt).booleanValue());
            } else if (opt instanceof Double) {
                bundle.putDouble(next, ((Double) opt).doubleValue());
            } else if (opt instanceof Float) {
                bundle.putFloat(next, ((Float) opt).floatValue());
            } else if (opt instanceof String) {
                bundle.putString(next, (String) opt);
            } else if (opt instanceof Byte) {
                bundle.putByte(next, ((Byte) opt).byteValue());
            }
        }
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("shareAppMessageDirectly", 1));
        list.add(new JsApiMethod("shareAppMessage", 1));
    }

    public void shareMantoApp(MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
    }

    public void shareToWxFriends(final MantoCore mantoCore, final String str, final String str2, final Bundle bundle, final MantoResultCallBack mantoResultCallBack) {
        String string = bundle.getString("imageUrl");
        if (MantoStringUtils.isEmpty(str) || MantoStringUtils.isEmpty(str2) || MantoStringUtils.isEmpty(string) || MantoStringUtils.isEmpty("vapp")) {
            return;
        }
        bundle.putString("flag", "vapp");
        b.d().diskIO().execute(new Runnable() { // from class: com.jingdong.manto.jsapi.refact.JSApiShareAppMessage.1
            @Override // java.lang.Runnable
            public void run() {
                String str3;
                try {
                    str3 = b.k().c(str, str2).getShareUrl();
                } catch (Exception unused) {
                    str3 = "";
                }
                if (MantoStringUtils.isEmpty(str3)) {
                    str3 = b.g().b("share_h5");
                }
                String str4 = (str3 + "?appId=" + str) + "&type=" + str2;
                String string2 = bundle.getString("path");
                if (!MantoStringUtils.isEmpty(string2)) {
                    str4 = str4 + "&path=" + string2;
                }
                bundle.putString("defaultLink", str4);
                MantoThreadUtils.runOnUIThread(new Runnable() { // from class: com.jingdong.manto.jsapi.refact.JSApiShareAppMessage.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MantoCore mantoCore2 = mantoCore;
                        if (mantoCore2 == null || mantoCore2.isFinishing()) {
                            return;
                        }
                        AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                        JSApiShareAppMessage.this.shareMantoApp(mantoCore, bundle, mantoResultCallBack);
                    }
                });
            }
        });
    }
}
