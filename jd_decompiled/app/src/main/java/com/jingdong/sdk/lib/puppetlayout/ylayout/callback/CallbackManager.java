package com.jingdong.sdk.lib.puppetlayout.ylayout.callback;

import android.app.Dialog;
import android.text.TextUtils;
import com.jingdong.sdk.lib.puppetlayout.ylayout.ExtraParam;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Action;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class CallbackManager {
    public static final String TYPE_ACTION = "action";
    public static final String TYPE_EXPOSURE = "exposure";
    private HashMap<String, ArrayList<Callback>> callbackHashMap = new HashMap<>();
    private ExtraParam cartFloorExtraParam;
    private Dialog dialog;

    public boolean handle(String str, Object obj) {
        ArrayList<Callback> arrayList = this.callbackHashMap.get(str);
        if (arrayList == null) {
            return false;
        }
        boolean z = false;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            Callback callback = arrayList.get(i2);
            if (callback != null && str.equals("action") && (obj instanceof Action)) {
                Action action = (Action) obj;
                if (action.isClientAndroid() && (callback instanceof ActionCallback)) {
                    ((ActionCallback) callback).execute(action);
                    z = true;
                }
            }
        }
        return z;
    }

    public void register(String str, Callback callback) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArrayList<Callback> arrayList = this.callbackHashMap.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.callbackHashMap.put(str, arrayList);
        }
        if (callback == null || arrayList.contains(callback)) {
            return;
        }
        arrayList.add(callback);
    }

    public void unregister(String str) {
        this.callbackHashMap.remove(str);
    }

    public void unregister(Callback callback) {
        if (callback != null) {
            ArrayList<Callback> arrayList = this.callbackHashMap.get("action");
            if (arrayList != null && arrayList.size() > 0) {
                arrayList.remove(callback);
            }
            ArrayList<Callback> arrayList2 = this.callbackHashMap.get("exposure");
            if (arrayList2 == null || arrayList2.size() <= 0) {
                return;
            }
            arrayList2.remove(callback);
        }
    }
}
