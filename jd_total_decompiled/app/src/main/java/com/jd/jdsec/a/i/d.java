package com.jd.jdsec.a.i;

import android.os.Build;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes13.dex */
public class d extends a {
    @Override // com.jd.jdsec.a.i.a
    protected String b() {
        List<SubscriptionInfo> activeSubscriptionInfoList;
        String str = "";
        if (com.jd.jdsec.c.g.k() || Build.VERSION.SDK_INT < 22 || (activeSubscriptionInfoList = ((SubscriptionManager) com.jd.jdsec.c.g.a.getSystemService("telephony_subscription_service")).getActiveSubscriptionInfoList()) == null) {
            return "";
        }
        Iterator<SubscriptionInfo> it = activeSubscriptionInfoList.iterator();
        while (it.hasNext()) {
            str = str + it.next().getMnc() + DYConstants.DY_REGEX_COMMA;
        }
        return com.jd.jdsec.a.l.e.j(str);
    }

    @Override // com.jd.jdsec.a.i.a
    protected String e() {
        if (com.jd.jdsec.a.a.a(com.jd.jdsec.c.g.a)) {
            TextUtils.isEmpty(com.jingdong.jdsdk.a.a.a);
            return "";
        }
        return "";
    }

    @Override // com.jd.jdsec.a.i.a
    protected boolean j() {
        return com.jd.jdsec.c.c.c().d();
    }
}
