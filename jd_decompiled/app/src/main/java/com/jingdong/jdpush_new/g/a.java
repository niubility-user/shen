package com.jingdong.jdpush_new.g;

import com.jingdong.common.messagecenter.NotificationMessageSummary;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class a {
    private String a;

    public static a b(String str) {
        try {
            a aVar = new a();
            JSONObject jSONObject = new JSONObject(str);
            aVar.e(jSONObject.has(NotificationMessageSummary.MSG_SEQ) ? jSONObject.getString(NotificationMessageSummary.MSG_SEQ) : null);
            aVar.d(jSONObject.has(NotificationMessageSummary.MSG_BODY) ? jSONObject.getString(NotificationMessageSummary.MSG_BODY) : null);
            aVar.c(jSONObject.has(NotificationMessageSummary.ECHO) ? jSONObject.getString(NotificationMessageSummary.ECHO) : null);
            return aVar;
        } catch (Exception unused) {
            return null;
        }
    }

    public String a() {
        return this.a;
    }

    public void c(String str) {
    }

    public void d(String str) {
        this.a = str;
    }

    public void e(String str) {
    }
}
