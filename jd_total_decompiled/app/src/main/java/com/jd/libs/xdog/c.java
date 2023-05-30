package com.jd.libs.xdog;

import com.jd.xbridge.base.IBridgeCallback;
import com.jd.xbridge.base.IBridgePlugin;
import com.jd.xbridge.base.IBridgeWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class c implements IBridgePlugin {

    /* loaded from: classes16.dex */
    class a implements d {
        final /* synthetic */ IBridgeCallback a;

        a(c cVar, IBridgeCallback iBridgeCallback) {
            this.a = iBridgeCallback;
        }

        @Override // com.jd.libs.xdog.d
        public void onError(String str) {
            this.a.onError("action throw exception");
        }

        @Override // com.jd.libs.xdog.d
        public void onSuccess(Object obj) {
            JSONArray jSONArray;
            try {
                jSONArray = new JSONArray(obj.toString());
            } catch (JSONException e2) {
                e2.printStackTrace();
                jSONArray = null;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("packList", jSONArray);
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
            f.b().k(jSONObject);
            this.a.onSuccess(jSONObject);
        }
    }

    @Override // com.jd.xbridge.base.IBridgePlugin
    public boolean execute(IBridgeWebView iBridgeWebView, String str, String str2, IBridgeCallback iBridgeCallback) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1426957612:
                if (str.equals("getDogDoor")) {
                    c2 = 0;
                    break;
                }
                break;
            case -148556777:
                if (str.equals("useBeta")) {
                    c2 = 1;
                    break;
                }
                break;
            case -27498641:
                if (str.equals("hiddenView")) {
                    c2 = 2;
                    break;
                }
                break;
            case 530344166:
                if (str.equals("getHybridPackageById")) {
                    c2 = 3;
                    break;
                }
                break;
            case 530628018:
                if (str.equals("getHybridPackageList")) {
                    c2 = 4;
                    break;
                }
                break;
            case 1831149322:
                if (str.equals("dogDoor")) {
                    c2 = 5;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                iBridgeCallback.onSuccess(f.b().a());
                return true;
            case 1:
                f.w = true;
                return true;
            case 2:
                f.b().e();
                return true;
            case 3:
                if (!f.u) {
                    iBridgeCallback.onError("switch is off");
                }
                try {
                    JSONObject jSONObject = new JSONObject(str2);
                    Object d = f.b().d(jSONObject.has("appid") ? jSONObject.getString("appid") : null);
                    if (d != null) {
                        iBridgeCallback.onSuccess(d);
                    } else {
                        iBridgeCallback.onError("data is null");
                    }
                } catch (Exception unused) {
                    iBridgeCallback.onError("action throw exception");
                }
                return true;
            case 4:
                if (!f.u) {
                    iBridgeCallback.onError("switch is off");
                } else {
                    try {
                        f.s.newInstance().notify(new a(this, iBridgeCallback));
                    } catch (IllegalAccessException e2) {
                        e2.printStackTrace();
                        iBridgeCallback.onError("action throw exception");
                    } catch (InstantiationException e3) {
                        e3.printStackTrace();
                        iBridgeCallback.onError("action throw exception");
                    }
                }
                return true;
            case 5:
                try {
                    JSONObject jSONObject2 = new JSONObject(str2);
                    if (jSONObject2.has("hybridDoor")) {
                        f.u = jSONObject2.getBoolean("hybridDoor");
                    }
                    if (jSONObject2.has("performanceDoor")) {
                        f.v = jSONObject2.getBoolean("performanceDoor");
                    }
                } catch (Exception unused2) {
                    iBridgeCallback.onError("action throw exception");
                }
                return true;
            default:
                return false;
        }
    }
}
