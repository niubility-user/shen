package com.huawei.hms.hatool;

import android.os.Build;

/* loaded from: classes12.dex */
public class m0 {
    private boolean a = false;
    private int b = 4;

    private static String a() {
        return "FormalHASDK_2.2.0.313" + p.a();
    }

    public void a(int i2) {
        String str;
        StringBuilder sb = new StringBuilder();
        if (Build.VERSION.SDK_INT >= 19) {
            sb.append(System.lineSeparator());
            sb.append("======================================= ");
            sb.append(System.lineSeparator());
            sb.append(a());
            sb.append("");
            sb.append(System.lineSeparator());
            str = "=======================================";
        } else {
            sb.append("====================");
            sb.append(a());
            str = "===================";
        }
        sb.append(str);
        sb.toString();
        this.b = i2;
        this.a = true;
    }

    public void a(int i2, String str, String str2) {
        if (i2 == 3 || i2 != 5) {
        }
    }

    public void b(int i2, String str, String str2) {
        a(i2, "FormalHASDK", str + "=> " + str2);
    }

    public boolean b(int i2) {
        return this.a && i2 >= this.b;
    }
}
