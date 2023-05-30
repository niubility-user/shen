package com.heytap.openid.sdk;

/* loaded from: classes12.dex */
public class m_e {
    public String m_a;
    public long m_b;

    public m_e(String str, long j2) {
        this.m_a = str;
        this.m_b = j2;
    }

    public boolean m_a(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = this.m_b;
        return currentTimeMillis < j2 - 600000 && Math.abs(j2 - currentTimeMillis) <= m_a.m_d(str) + 600000;
    }
}
