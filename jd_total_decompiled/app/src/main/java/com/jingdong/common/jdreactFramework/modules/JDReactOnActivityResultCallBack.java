package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

/* loaded from: classes5.dex */
public interface JDReactOnActivityResultCallBack {
    WritableMap getState(String str);

    WritableArray getStateArray(String str);

    WritableMap removeState(String str);

    WritableArray removeStateArray(String str);

    void saveState(String str, WritableMap writableMap);

    void saveStateArray(String str, WritableArray writableArray);
}
