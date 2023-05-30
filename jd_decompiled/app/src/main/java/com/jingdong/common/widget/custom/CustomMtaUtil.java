package com.jingdong.common.widget.custom;

import com.jingdong.jdsdk.constant.CartConstant;

/* loaded from: classes12.dex */
public class CustomMtaUtil {
    public static String zuhe(Object... objArr) {
        StringBuilder sb = new StringBuilder();
        if (objArr != null && objArr.length > 0) {
            for (Object obj : objArr) {
                if (obj == null) {
                    obj = "";
                }
                sb.append(obj);
                sb.append(CartConstant.KEY_YB_INFO_LINK);
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String zuheWithFu(String str, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        if (objArr != null && objArr.length > 0) {
            for (Object obj : objArr) {
                if (obj == null) {
                    obj = "";
                }
                sb.append(obj);
                sb.append(str);
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
