package com.vivo.push.b;

import android.text.TextUtils;
import com.jingdong.common.database.table.PushMessageTable;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.HashMap;

/* loaded from: classes11.dex */
public final class x extends com.vivo.push.o {
    private HashMap<String, String> a;
    private long b;

    public x() {
        super(R2.attr.textAppearanceListItem);
    }

    public final void a(HashMap<String, String> hashMap) {
        this.a = hashMap;
    }

    @Override // com.vivo.push.o
    public final void c(com.vivo.push.a aVar) {
        aVar.a("ReporterCommand.EXTRA_PARAMS", this.a);
        aVar.a("ReporterCommand.EXTRA_REPORTER_TYPE", this.b);
    }

    @Override // com.vivo.push.o
    public final void d(com.vivo.push.a aVar) {
        this.a = (HashMap) aVar.d("ReporterCommand.EXTRA_PARAMS");
        this.b = aVar.b("ReporterCommand.EXTRA_REPORTER_TYPE", this.b);
    }

    @Override // com.vivo.push.o
    public final String toString() {
        return "ReporterCommand\uff08" + this.b + ")";
    }

    public x(long j2) {
        this();
        this.b = j2;
    }

    public final void d() {
        if (this.a == null) {
            com.vivo.push.util.p.d("ReporterCommand", "reportParams is empty");
            return;
        }
        StringBuilder sb = new StringBuilder("report message reportType:");
        sb.append(this.b);
        sb.append(",msgId:");
        String str = this.a.get("messageID");
        if (TextUtils.isEmpty(str)) {
            str = this.a.get(PushMessageTable.TB_CLOUMN_MESSAGE_ID);
        }
        sb.append(str);
        com.vivo.push.util.p.d("ReporterCommand", sb.toString());
    }
}
