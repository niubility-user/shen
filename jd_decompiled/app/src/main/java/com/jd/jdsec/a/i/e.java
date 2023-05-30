package com.jd.jdsec.a.i;

import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.jingdong.common.database.table.SignUpTable;

/* loaded from: classes13.dex */
public class e extends a {
    private String k() {
        try {
            String networkCountryIso = ((TelephonyManager) com.jd.jdsec.c.g.a.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getNetworkCountryIso();
            return TextUtils.isEmpty(networkCountryIso) ? "" : networkCountryIso;
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
        return true;
    }
}
