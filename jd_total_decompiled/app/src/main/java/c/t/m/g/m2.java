package c.t.m.g;

import android.location.Location;
import android.os.Bundle;

/* loaded from: classes.dex */
public class m2 {
    public static final Location a;

    static {
        new Bundle();
        a = new Location("EMPTY");
    }

    public static String a(int i2, int i3, int i4) {
        StringBuilder sb;
        String str;
        StringBuilder sb2 = new StringBuilder();
        boolean i5 = y2.f().i("https");
        StringBuilder sb3 = new StringBuilder("http");
        sb3.append(i5 ? "s" : "");
        String sb4 = sb3.toString();
        if (s3.d0 && s3.e0) {
            sb = new StringBuilder();
            sb.append(sb4);
            str = "://lbs.map.iot.wechatpay.cn/loc";
        } else {
            sb = new StringBuilder();
            sb.append(sb4);
            str = "://lbs.map.qq.com/loc";
        }
        sb.append(str);
        sb2.append(sb.toString());
        sb2.append("?");
        sb2.append("c=");
        sb2.append(i2);
        sb2.append("&mars=");
        sb2.append(i3);
        sb2.append("&obs=");
        sb2.append(i4);
        return sb2.toString();
    }
}
