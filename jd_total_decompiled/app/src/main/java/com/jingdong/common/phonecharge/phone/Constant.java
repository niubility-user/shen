package com.jingdong.common.phonecharge.phone;

import android.content.Intent;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.phonecharge.game.GameChargeConstant;

/* loaded from: classes5.dex */
public class Constant {
    public static final int cardPwdFlag_0 = 0;
    public static final int cardPwdFlag_1 = 1;
    public static final int dayj = 25;
    public static final int dayk = 22;
    public static final int hh1 = 9;
    public static final int hh2 = 16;

    public static String getErrorString(String str, String str2, String str3) {
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put("1017", "\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u624b\u673a\u53f7\u7801");
        arrayMap.put("1025", "");
        arrayMap.put("1026", "");
        arrayMap.put("1034", "");
        arrayMap.put("1035", "");
        arrayMap.put("1037", "");
        arrayMap.put("1038", "");
        arrayMap.put("1039", "");
        arrayMap.put("1040", "");
        arrayMap.put("1111", "\u6b64\u9762\u989d\u7f3a\u8d27");
        arrayMap.put("1112", "\u5148\u5f00\u901a\u652f\u4ed8\u5bc6\u7801\u54e6");
        arrayMap.put("1113", "\u652f\u4ed8\u5bc6\u7801\u9519\u8bef\u6b21\u6570\u592a\u591a\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5");
        arrayMap.put("1114", "\u652f\u4ed8\u5bc6\u7801\u4e0d\u6b63\u786e\uff0c\u518d\u8bd5\u4e00\u4e0b");
        arrayMap.put("5000", GameChargeConstant.ERROR);
        arrayMap.put("8000", "\u6bcf\u5355\u9650\u75281\u5f20\u4f18\u60e0\u5238\u54e6");
        arrayMap.put("JDLL_00001", "\u53c2\u6570\u4e0d\u80fd\u4e3a\u7a7a");
        arrayMap.put("JDLL_00002", "\u53c2\u6570\u4e0d\u6b63\u786e");
        arrayMap.put("JDLL_00003", "\u53f7\u6bb5\u4fe1\u606f\u4e0d\u5b58\u5728");
        arrayMap.put("JDLL_00004", "\u5546\u54c1\u4fe1\u606f\u4e0d\u5b58\u5728");
        arrayMap.put("JDLL_00005", "\u8ba2\u5355\u4fe1\u606f\u4e0d\u5b58\u5728");
        arrayMap.put("JDLL_00006", "\u7528\u6237\u865a\u62df\u8d44\u4ea7\u4e0d\u8db3");
        arrayMap.put("JDLL_00007", "\u53cc\u65b9\u552e\u4ef7\u4e0d\u7b26");
        arrayMap.put("JDLL_00008", "\u4ea7\u54c1\u4e0e\u624b\u673a\u53f7\u4e0d\u5339\u914d");
        arrayMap.put("JDLL_00009", "\u4e0b\u5355\u5931\u8d25");
        arrayMap.put("JDLL_000010", "\u7cfb\u7edf\u5f02\u5e38");
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        if (str3 == null) {
            str3 = "";
        }
        if ("-1".equals(str)) {
            return "\u7cfb\u7edf\u5f02\u5e38";
        }
        if ("0".equals(str)) {
            if ("".equals(str2)) {
                return "";
            }
            if (!"".equals(str3)) {
                return str3;
            }
            if (arrayMap.get(str2) != 0) {
                return (String) arrayMap.get(str2);
            }
        } else if ("1".equals(str)) {
            return "\u53c2\u6570\u9519\u8bef";
        } else {
            if ("2".equals(str)) {
                return "\u65b9\u6cd5\u4e0d\u5b58\u5728";
            }
            if ("3".equals(str)) {
                return "\u672a\u767b\u5f55";
            }
        }
        return GameChargeConstant.ERROR;
    }

    public static String handlePhoneNum(String str) {
        if (TextUtils.isEmpty(str) || str.length() != 11) {
            return str;
        }
        return str.substring(0, 3) + "****" + str.substring(7, 11);
    }

    public static void startActivity(IMyActivity iMyActivity, Intent intent) {
        iMyActivity.startActivityInFrameWithNoNavigation(intent);
    }
}
