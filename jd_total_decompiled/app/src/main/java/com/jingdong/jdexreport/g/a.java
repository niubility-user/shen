package com.jingdong.jdexreport.g;

import com.jingdong.jdexreport.record.JDExReportDbImpl;
import java.util.HashMap;

/* loaded from: classes.dex */
public class a implements Runnable {
    private JDExReportDbImpl a;
    private HashMap<String, String> b;

    public a(JDExReportDbImpl jDExReportDbImpl, HashMap<String, String> hashMap) {
        this.a = jDExReportDbImpl;
        this.b = hashMap;
    }

    @Override // java.lang.Runnable
    public void run() {
        HashMap<String, String> hashMap;
        JDExReportDbImpl jDExReportDbImpl = this.a;
        if (jDExReportDbImpl == null || (hashMap = this.b) == null) {
            return;
        }
        jDExReportDbImpl.reqRecord(hashMap);
    }
}
