package com.jd.libs.jdmbridge.base.proxy.nav;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.facebook.react.uimanager.ViewProps;
import com.jd.libs.jdmbridge.base.JDBridgeManager;
import com.jd.libs.jdmbridge.base.base.BaseBridgeAdapter;
import com.jingdong.common.widget.NavigatorHolder;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class NavBridgeAdapter extends BaseBridgeAdapter<NavBridgeProxy> implements INavBridge {
    public NavBridgeAdapter(JDBridgeManager jDBridgeManager) {
        super(jDBridgeManager);
    }

    /* renamed from: c */
    public /* synthetic */ void d() {
        getProxy().setCloseBtnVisibility(0);
    }

    /* renamed from: setRightButton */
    public void b(JSONObject jSONObject) {
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            JSONObject optJSONObject = jSONObject.optJSONObject(keys.next());
            if (optJSONObject != null) {
                String optString = optJSONObject.optString("type");
                String optString2 = optJSONObject.optString(ViewProps.DISPLAY);
                if (TextUtils.isEmpty(optString)) {
                    continue;
                } else {
                    String str = null;
                    optString.hashCode();
                    if (optString.equals("share")) {
                        str = INavAdapter.BTN_TYPE_SHARE;
                    } else if (optString.equals(NavigatorHolder.NaviEntity.TYPE_CLEAR)) {
                        getProxy().resetRightButtons();
                        return;
                    }
                    if (str != null) {
                        if ("show".equalsIgnoreCase(optString2)) {
                            getProxy().showRightButton(str, 0);
                        } else {
                            getProxy().hideRightButton(str);
                        }
                    }
                }
            }
        }
    }

    @Override // com.jd.libs.jdmbridge.base.proxy.nav.INavBridge
    @JavascriptInterface
    public void configBtnSince610(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            final JSONObject jSONObject = null;
            try {
                jSONObject = new JSONObject(str);
            } catch (Exception unused) {
            }
            if (jSONObject == null) {
                return;
            }
            this.mBridgeManager.post(new Runnable() { // from class: com.jd.libs.jdmbridge.base.proxy.nav.a
                {
                    NavBridgeAdapter.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    NavBridgeAdapter.this.b(jSONObject);
                }
            });
        } catch (Throwable unused2) {
        }
    }

    @Override // com.jd.libs.jdmbridge.base.proxy.nav.INavBridge
    @JavascriptInterface
    public void showCloseBtn() {
        try {
            this.mBridgeManager.post(new Runnable() { // from class: com.jd.libs.jdmbridge.base.proxy.nav.b
                {
                    NavBridgeAdapter.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    NavBridgeAdapter.this.d();
                }
            });
        } catch (Throwable unused) {
        }
    }

    @Override // com.jd.libs.jdmbridge.base.base.BaseBridgeAdapter
    public NavBridgeProxy getProxy() {
        return (NavBridgeProxy) this.mBridgeManager.getProxy("MobileNavi");
    }
}
