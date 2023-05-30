package com.jd.jdsec.a.i;

import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.jingdong.common.database.table.SignUpTable;

/* loaded from: classes13.dex */
public class h extends a {
    private String k() {
        try {
            if (com.jd.jdsec.a.a.a(com.jd.jdsec.c.g.a)) {
                String simOperatorName = ((TelephonyManager) com.jd.jdsec.c.g.a.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getSimOperatorName();
                return TextUtils.isEmpty(simOperatorName) ? "" : simOperatorName;
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    @Override // com.jd.jdsec.a.i.a
    protected String b() {
        try {
            return c("getSimOperatorName");
        } catch (Exception unused) {
            return "";
        }
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
