package com.jd.jdsec.a.i;

import android.annotation.SuppressLint;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.jingdong.common.database.table.SignUpTable;

@SuppressLint({"MissingPermission", "HardwareIds"})
/* loaded from: classes13.dex */
public class f extends a {
    private String k() {
        try {
            if (com.jd.jdsec.a.a.a(com.jd.jdsec.c.g.a)) {
                String line1Number = ((TelephonyManager) com.jd.jdsec.c.g.a.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getLine1Number();
                return TextUtils.isEmpty(line1Number) ? "" : line1Number;
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    @Override // com.jd.jdsec.a.i.a
    protected String b() {
        try {
            return c("getLine1Number");
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
