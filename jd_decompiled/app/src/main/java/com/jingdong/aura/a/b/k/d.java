package com.jingdong.aura.a.b.k;

import java.util.Properties;

/* loaded from: classes4.dex */
class d {
    private Properties a;

    public d(Properties properties) {
        if (properties == null) {
            this.a = new Properties();
        } else {
            this.a = properties;
        }
    }

    public boolean a(String str, boolean z) {
        String str2;
        Properties properties = this.a;
        return (properties == null || (str2 = (String) properties.get(str)) == null) ? z : Boolean.valueOf(str2).booleanValue();
    }

    public String b(String str, String str2) {
        Properties properties = this.a;
        if (properties == null) {
            return null;
        }
        return (String) properties.put(str, str2);
    }

    public int a(String str, int i2) {
        String str2;
        Properties properties = this.a;
        return (properties == null || (str2 = (String) properties.get(str)) == null) ? i2 : Integer.parseInt(str2);
    }

    public String a(String str) {
        Properties properties = this.a;
        if (properties == null) {
            return null;
        }
        return (String) properties.get(str);
    }

    public String a(String str, String str2) {
        String str3;
        Properties properties = this.a;
        return (properties == null || (str3 = (String) properties.get(str)) == null) ? str2 : str3;
    }
}
