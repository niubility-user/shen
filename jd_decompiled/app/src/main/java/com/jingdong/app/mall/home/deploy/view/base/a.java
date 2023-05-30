package com.jingdong.app.mall.home.deploy.view.base;

import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class a {
    public static final HashMap<String, Integer> a;

    static {
        HashMap<String, Integer> hashMap = new HashMap<>();
        a = hashMap;
        hashMap.put("r", 5);
        hashMap.put(NotifyType.LIGHTS, 3);
        hashMap.put("c", 17);
        hashMap.put("c_v", 16);
        hashMap.put("c_h", 1);
    }

    public static int a(String str) {
        Integer num = a.get(str);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public static void b(String str, LinearLayout.LayoutParams layoutParams) {
    }

    public static void c(String str, RelativeLayout.LayoutParams layoutParams) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case 76047:
                if (str.equals("L_B")) {
                    c2 = 0;
                    break;
                }
                break;
            case 76065:
                if (str.equals("L_T")) {
                    c2 = 1;
                    break;
                }
                break;
            case 81813:
                if (str.equals("R_B")) {
                    c2 = 2;
                    break;
                }
                break;
            case 81831:
                if (str.equals("R_T")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                layoutParams.addRule(9);
                layoutParams.addRule(12);
                return;
            case 1:
                layoutParams.addRule(9);
                layoutParams.addRule(10);
                return;
            case 2:
                layoutParams.addRule(11);
                layoutParams.addRule(12);
                return;
            case 3:
                layoutParams.addRule(11);
                layoutParams.addRule(10);
                return;
            default:
                return;
        }
    }
}
