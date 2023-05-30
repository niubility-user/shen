package com.jingdong.jdexreport.e;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class d {
    private String a = "";
    private String b = "";

    public static String a(HashMap<String, String> hashMap) {
        try {
            JSONObject jSONObject = new JSONObject();
            int i2 = 0;
            String str = "";
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                try {
                    String str2 = entry.getKey() + "";
                    String str3 = entry.getValue() + "";
                    if (str3.length() <= 1048576) {
                        jSONObject.put(str2, str3);
                    } else {
                        i2 = str3.length();
                        str = str2;
                    }
                } catch (ClassCastException e2) {
                    e2.printStackTrace();
                }
            }
            if (i2 > 1048576) {
                jSONObject.put("reserved1", "out of range");
                jSONObject.put("reserved2", str + ": " + i2);
            }
            return jSONObject.toString();
        } catch (Exception e3) {
            e3.printStackTrace();
            return "";
        }
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public void b(HashMap<String, String> hashMap) {
        this.b = a(hashMap);
    }

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }
}
