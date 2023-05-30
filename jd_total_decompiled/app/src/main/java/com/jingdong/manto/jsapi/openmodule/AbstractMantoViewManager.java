package com.jingdong.manto.jsapi.openmodule;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Keep;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.base.MantoCallback;
import com.jingdong.manto.m.d1.i;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public abstract class AbstractMantoViewManager implements IMantoBaseModule {
    public static final String VIEW_ID_KEY = "viewID";
    private HashMap<String, i> mHashMap = new HashMap<>();

    public AbstractMantoViewManager() {
        ArrayList arrayList = new ArrayList();
        injectJsApiMethod(arrayList);
        if (arrayList.size() > 0) {
            Iterator<JsApiMethod> it = arrayList.iterator();
            while (it.hasNext()) {
                registerAPIMethod(it.next().methodName, 5);
            }
        }
    }

    private void registerAPIMethod(String str, int i2) {
        if (this.mHashMap.containsKey(str)) {
            throw new RuntimeException("method name should be different");
        }
        this.mHashMap.put(str, new i(str, i2, this));
    }

    public MantoLifecycleLisener addLifecycleLisener(Bundle bundle) {
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final MantoLifecycleLisener addLifecycleLisener(String str, Bundle bundle) {
        if (str.equals("insert" + getViewName())) {
            return addLifecycleLisener(bundle);
        }
        return null;
    }

    public void addPicInPicPage(MantoCore mantoCore, int i2, boolean z, boolean z2) {
        if (mantoCore != null) {
            mantoCore.addPicInPicPage(i2, z, z2);
        }
    }

    public final boolean dispatchEvent(MantoCore mantoCore, String str, JSONObject jSONObject, int i2) {
        if (mantoCore != null) {
            mantoCore.dispatchEevent(str, jSONObject, i2);
            return true;
        }
        return false;
    }

    public final boolean dispatchEventToPage(MantoCore mantoCore, String str, JSONObject jSONObject, int[] iArr) {
        if (mantoCore != null) {
            mantoCore.dispatchEeventToPage(str, jSONObject, iArr);
            return true;
        }
        return false;
    }

    public final Bitmap getBitmap(MantoCore mantoCore, String str) {
        if (mantoCore != null) {
            return mantoCore.getBitmap(str);
        }
        return null;
    }

    public abstract View getCustomView(MantoCore mantoCore);

    public View getCustomView(MantoCore mantoCore, Bundle bundle) {
        return null;
    }

    public int getInsertIndex() {
        return 0;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final HashMap<String, i> getNativeMethod() {
        return this.mHashMap;
    }

    public int getRemoveIndex() {
        return 0;
    }

    public int getUpdateIndex() {
        return 0;
    }

    public final View getView(MantoCore mantoCore) {
        return getCustomView(mantoCore);
    }

    public final View getView(MantoCore mantoCore, Bundle bundle) {
        return getCustomView(mantoCore, bundle);
    }

    public abstract String getViewName();

    public abstract Bundle handleInsertData(MantoCore mantoCore, JSONObject jSONObject);

    public abstract void handleMethod(String str, View view, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack);

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final Bundle handleMethodSync(String str, MantoCore mantoCore, Bundle bundle) {
        return null;
    }

    public abstract Bundle handleRemoveData(MantoCore mantoCore, JSONObject jSONObject);

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final Bundle handleResult(String str, MantoCore mantoCore, Intent intent, int i2, int i3) {
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleResultWithCallback(String str, MantoCore mantoCore, Intent intent, int i2, int i3, MantoResultCallBack mantoResultCallBack) {
    }

    public abstract Bundle handleUpdateData(MantoCore mantoCore, JSONObject jSONObject);

    @Keep
    protected abstract void injectJsApiMethod(List<JsApiMethod> list);

    public abstract boolean onViewInsert(Bundle bundle, View view, MantoCore mantoCore);

    public abstract boolean onViewRemove(Bundle bundle, View view, MantoCore mantoCore);

    public abstract boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle);

    public abstract boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle, MantoCallback mantoCallback);

    public final InputStream readFile(MantoCore mantoCore, String str) {
        if (mantoCore != null) {
            return mantoCore.getFile(str);
        }
        return null;
    }

    public void removePicInPicPage(MantoCore mantoCore, int i2) {
        if (mantoCore != null) {
            mantoCore.removePicInPicPage(i2);
        }
    }
}
