package com.jd.jdsec.a.i;

import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.jingdong.common.database.table.SignUpTable;

/* loaded from: classes13.dex */
public class g extends a {
    private String k() {
        try {
            if (com.jd.jdsec.a.a.a(com.jd.jdsec.c.g.a)) {
                String simCountryIso = ((TelephonyManager) com.jd.jdsec.c.g.a.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getSimCountryIso();
                return TextUtils.isEmpty(simCountryIso) ? "" : simCountryIso;
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    @Override // com.jd.jdsec.a.i.a
    protected String b() {
        return c("getNetworkCountryIso");
    }

    @Override // com.jd.jdsec.a.i.a
    protected String e() {
        return k();
    }

    @Override // com.jd.jdsec.a.i.a
    protected boolean j() {
        return com.jd.jdsec.c.c.c().d();
    }
}
