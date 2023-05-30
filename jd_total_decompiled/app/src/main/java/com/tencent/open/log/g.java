package com.tencent.open.log;

import android.text.format.Time;
import android.util.Log;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.jdsdk.auraSetting.AuraConstants;

/* loaded from: classes9.dex */
public final class g {
    public static final g a = new g();

    public final String a(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 4 ? i2 != 8 ? i2 != 16 ? i2 != 32 ? "-" : "A" : "E" : "W" : "I" : AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE : "V";
    }

    public String a(int i2, Thread thread, long j2, String str, String str2, Throwable th) {
        long j3 = j2 % 1000;
        Time time = new Time();
        time.set(j2);
        StringBuilder sb = new StringBuilder();
        sb.append(a(i2));
        sb.append('/');
        sb.append(time.format("%Y-%m-%d %H:%M:%S"));
        sb.append(OrderISVUtil.MONEY_DECIMAL_CHAR);
        if (j3 < 10) {
            sb.append("00");
        } else if (j3 < 100) {
            sb.append('0');
        }
        sb.append(j3);
        sb.append(' ');
        sb.append('[');
        if (thread == null) {
            sb.append("N/A");
        } else {
            sb.append(thread.getName());
        }
        sb.append(']');
        sb.append('[');
        sb.append(str);
        sb.append(']');
        sb.append(' ');
        sb.append(str2);
        sb.append('\n');
        if (th != null) {
            sb.append("* Exception : \n");
            sb.append(Log.getStackTraceString(th));
            sb.append('\n');
        }
        return sb.toString();
    }
}
