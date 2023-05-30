package com.jingdong.manto.jsapi.openmodule;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.Keep;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.card.MantoCardView;
import com.jingdong.manto.m.d1.i;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public abstract class AbstractMantoModule implements IMantoBaseModule {
    private HashMap<String, i> mHashMap = new HashMap<>();

    public AbstractMantoModule() {
        ArrayList arrayList = new ArrayList();
        injectJsApiMethod(arrayList);
        if (arrayList.size() > 0) {
            for (JsApiMethod jsApiMethod : arrayList) {
                registerAPIMethod(jsApiMethod.methodName, jsApiMethod.type);
            }
        }
    }

    public static final ViewGroup getPageInnerContentView(MantoCore mantoCore) {
        if (mantoCore instanceof MantoCore) {
            return mantoCore.getPageInnerContentView();
        }
        return null;
    }

    public static final InputStream readFile(MantoCore mantoCore, String str) {
        if (mantoCore instanceof MantoCore) {
            return mantoCore.getFile(str);
        }
        return null;
    }

    private void registerAPIMethod(String str, int i2) {
        if (this.mHashMap.containsKey(str)) {
            throw new RuntimeException("method name should be different");
        }
        this.mHashMap.put(str, new i(str, i2, this));
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public MantoLifecycleLisener addLifecycleLisener(String str, Bundle bundle) {
        return null;
    }

    public final boolean convertNativeBuffer(MantoCore mantoCore, JSONObject jSONObject, Map map, boolean z) {
        if (mantoCore instanceof MantoCore) {
            return mantoCore.convertNativeBuffer(jSONObject, map, z);
        }
        return false;
    }

    public final boolean dispatchEvent(MantoCore mantoCore, String str, JSONObject jSONObject, int i2) {
        if (mantoCore instanceof MantoCore) {
            mantoCore.dispatchEevent(str, jSONObject, i2);
            return true;
        }
        return false;
    }

    public final boolean dispatchEventToPage(MantoCore mantoCore, String str, JSONObject jSONObject, int[] iArr) {
        if (mantoCore instanceof MantoCore) {
            mantoCore.dispatchEeventToPage(str, jSONObject, iArr);
            return true;
        }
        return false;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public HashMap<String, i> getNativeMethod() {
        return this.mHashMap;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle handleMethodSync(String str, MantoCore mantoCore, Bundle bundle) {
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle handleResult(String str, MantoCore mantoCore, Intent intent, int i2, int i3) {
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleResultWithCallback(String str, MantoCore mantoCore, Intent intent, int i2, int i3, MantoResultCallBack mantoResultCallBack) {
    }

    @Keep
    protected abstract void injectJsApiMethod(List<JsApiMethod> list);

    public boolean isCard(MantoCore mantoCore) {
        if (mantoCore != null) {
            return mantoCore instanceof MantoCardView;
        }
        return false;
    }

    public void setAnchorViewVisible(MantoCore mantoCore, boolean z, Bundle bundle) {
        if (mantoCore instanceof MantoCore) {
            mantoCore.setAnchorViewVisible(z, bundle);
        }
    }
}
