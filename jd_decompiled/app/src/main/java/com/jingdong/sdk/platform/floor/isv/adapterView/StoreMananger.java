package com.jingdong.sdk.platform.floor.isv.adapterView;

import android.text.TextUtils;
import com.jingdong.sdk.platform.floor.isv.store.OptionStore;
import com.jingdong.sdk.platform.floor.isv.store.ViewStore;
import java.util.HashMap;

/* loaded from: classes10.dex */
public class StoreMananger {
    static HashMap<String, ViewStore> sStoreHashMap = new HashMap<>();
    static HashMap<String, OptionStore> storeHashMap = new HashMap<>();

    public static OptionStore getOptionStore(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!storeHashMap.containsKey(str)) {
            storeHashMap.put(str, new OptionStore(str));
        }
        return storeHashMap.get(str);
    }

    public static ViewStore getViewStore(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!sStoreHashMap.containsKey(str)) {
            sStoreHashMap.put(str, new ViewStore());
        }
        return sStoreHashMap.get(str);
    }
}
