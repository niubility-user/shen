package com.facebook.react;

import com.facebook.react.bridge.ReadableMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class JDReactData {
    private static List<ReadableMap> data = Collections.synchronizedList(new ArrayList());
    private static JDReactData mJDReactData;

    public static void addData(ReadableMap readableMap) {
        if (data == null) {
            data = new ArrayList();
        }
        data.add(readableMap);
    }

    public static void cleanAll() {
        List<ReadableMap> list = data;
        if (list == null) {
            return;
        }
        list.clear();
        data = null;
    }

    public static List<ReadableMap> getData() {
        return data;
    }

    public static JDReactData newInstance() {
        if (mJDReactData == null) {
            mJDReactData = new JDReactData();
        }
        return mJDReactData;
    }

    public static void removeData(int i2) {
        List<ReadableMap> list = data;
        if (list == null) {
            return;
        }
        list.remove(i2);
    }

    public static void setData(List<ReadableMap> list) {
        data = list;
    }
}
