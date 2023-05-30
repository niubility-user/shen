package com.jd.stat.common.a;

import android.os.Build;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.stat.common.l;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes18.dex */
public class d extends a {
    @Override // com.jd.stat.common.a.a
    protected String a() {
        if (l.a(com.jd.stat.security.c.a)) {
            TextUtils.isEmpty(com.jingdong.jdsdk.a.a.a);
            return "";
        }
        return "";
    }

    @Override // com.jd.stat.common.a.a
    protected String b() {
        List<SubscriptionInfo> activeSubscriptionInfoList;
        String str = "";
        if (com.jd.stat.security.c.k() && Build.VERSION.SDK_INT >= 22 && (activeSubscriptionInfoList = ((SubscriptionManager) com.jd.stat.security.c.a.getSystemService("telephony_subscription_service")).getActiveSubscriptionInfoList()) != null) {
            Iterator<SubscriptionInfo> it = activeSubscriptionInfoList.iterator();
            while (it.hasNext()) {
                str = str + it.next().getMnc() + DYConstants.DY_REGEX_COMMA;
            }
            return com.jd.stat.common.b.g.h(str);
        }
        return "";
    }

    @Override // com.jd.stat.common.a.a
    protected boolean c() {
        return com.jd.stat.security.d.a().m();
    }
}
