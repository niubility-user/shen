package com.xiaomi.push;

import android.content.Context;
import com.jd.dynamic.DYConstants;
import com.tencent.connect.common.Constants;

/* loaded from: classes11.dex */
public class y2 extends x2 {
    public y2(Context context, int i2) {
        super(context, i2);
    }

    @Override // com.xiaomi.push.i.a
    public String b() {
        return Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR;
    }

    @Override // com.xiaomi.push.x2
    public e7 c() {
        return e7.Storage;
    }

    @Override // com.xiaomi.push.x2
    public String g() {
        return "ram:" + z6.e() + DYConstants.DY_REGEX_COMMA + "rom:" + z6.n() + "|ramOriginal:" + z6.r() + DYConstants.DY_REGEX_COMMA + "romOriginal:" + z6.u();
    }
}
