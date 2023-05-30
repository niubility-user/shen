package com.jd.aips.verify.face.biz;

import android.util.SparseArray;

/* loaded from: classes12.dex */
public class TipTextHelper {
    private static final SparseArray<String> ACTION_TYPE_TIPS = new SparseArray<>();
    private static final SparseArray<String> CALLBACK_TYPE_TIPS = new SparseArray<>();

    static {
        initActionTypeTips();
        initCallbackTypeTips();
    }

    private void TipTextHelper() {
    }

    public static String getSilentTipTextByCallbackType(int i2) {
        String str = CALLBACK_TYPE_TIPS.get(i2);
        return str == null ? "" : str;
    }

    public static String getTipTextByActionType(int i2) {
        String str = ACTION_TYPE_TIPS.get(i2);
        return str == null ? "" : str;
    }

    private static void initActionTypeTips() {
        SparseArray<String> sparseArray = ACTION_TYPE_TIPS;
        sparseArray.put(1002, "\u5f20\u5f20\u5634");
        sparseArray.put(1003, "\u7728\u7728\u773c");
        sparseArray.put(1004, "\u8bf7\u7f13\u6162\u6447\u5934");
        sparseArray.put(1005, "\u8bf7\u7f13\u6162\u70b9\u5934");
    }

    private static void initCallbackTypeTips() {
        SparseArray<String> sparseArray = CALLBACK_TYPE_TIPS;
        sparseArray.put(1002, "\u6ca1\u6709\u68c0\u6d4b\u5230\u4eba\u8138");
        sparseArray.put(1005, "\u8bf7\u6b63\u5bf9\u5c4f\u5e55");
        sparseArray.put(1006, "\u8c03\u6574\u59ff\u6001\uff0c\u6b63\u5bf9\u5c4f\u5e55");
        sparseArray.put(1008, "\u8c03\u6574\u59ff\u6001\uff0c\u8bf7\u6b63\u5bf9\u624b\u673a");
        sparseArray.put(1009, "\u9760\u8fd1\u4e00\u70b9");
        sparseArray.put(1010, "\u79bb\u8fdc\u4e00\u70b9");
        sparseArray.put(1012, "\u8c03\u6574\u59ff\u6001\uff0c\u907f\u514d\u5634\u5df4\u906e\u6321");
        sparseArray.put(1013, "\u8c03\u6574\u59ff\u6001\uff0c\u907f\u514d\u773c\u775b\u906e\u6321");
        sparseArray.put(1014, "\u5149\u7ebf\u8f83\u6697");
        sparseArray.put(1015, "\u52a8\u4f5c\u4e0d\u8fde\u7eed");
    }
}
