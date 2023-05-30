package com.jingdong.common.utils;

import android.text.TextUtils;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class SettlementUtil {
    private static final ArrayList<String> noitfyClosedFunctionId = new ArrayList<>();

    public static void addNoitfyClosedFunctionId(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        noitfyClosedFunctionId.add(str);
    }

    public static void clearAllFunctionId() {
        noitfyClosedFunctionId.clear();
    }

    public static boolean hasAddFunctionId(String str) {
        return noitfyClosedFunctionId.contains(str);
    }

    public static void remvoeFunctionId(String str) {
        ArrayList<String> arrayList = noitfyClosedFunctionId;
        if (arrayList == null || arrayList.isEmpty() || !arrayList.contains(str)) {
            return;
        }
        arrayList.remove(str);
    }
}
