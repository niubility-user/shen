package com.heytap.openid.sdk;

import android.content.Context;
import com.heytap.openid.sdk.m_c;
import com.heytap.openid.sdk.m_f;
import com.jingdong.common.utils.JDReminderNewUtils;
import java.util.HashMap;

/* loaded from: classes12.dex */
public class m_b {
    public static m_b m_c;
    public HashMap<String, m_e> m_a;
    public boolean m_b = false;

    public m_b() {
        this.m_a = null;
        this.m_a = new HashMap<>();
    }

    public static m_b m_a() {
        if (m_c == null) {
            synchronized (m_b.class) {
                if (m_c == null) {
                    m_c = new m_b();
                }
            }
        }
        return m_c;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x01c3 A[Catch: all -> 0x0283, TryCatch #2 {, blocks: (B:3:0x0001, B:5:0x000b, B:59:0x011a, B:61:0x0120, B:63:0x012c, B:65:0x0131, B:69:0x013d, B:71:0x0141, B:73:0x014e, B:77:0x0163, B:79:0x016b, B:82:0x0184, B:83:0x018e, B:118:0x0264, B:120:0x0268, B:121:0x026e, B:122:0x0270, B:128:0x027e, B:129:0x027f, B:130:0x0280, B:111:0x01dc, B:112:0x01f2, B:113:0x01f6, B:114:0x020d, B:115:0x0229, B:116:0x023b, B:117:0x024d, B:85:0x0192, B:88:0x019c, B:91:0x01a6, B:94:0x01af, B:97:0x01b9, B:100:0x01c3, B:80:0x0178, B:72:0x0148, B:7:0x0011, B:9:0x0021, B:14:0x003b, B:16:0x0047, B:19:0x005a, B:21:0x0060, B:22:0x0064, B:23:0x0067, B:25:0x006f, B:30:0x0089, B:32:0x0095, B:35:0x00a8, B:37:0x00ae, B:38:0x00b2, B:39:0x00b5, B:41:0x00bd, B:45:0x00cf, B:46:0x00d9, B:48:0x00e1, B:52:0x00f3, B:55:0x00ff, B:57:0x0113, B:58:0x0117, B:123:0x0271, B:125:0x0275), top: B:141:0x0001, inners: #0, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x024d A[Catch: all -> 0x0283, TryCatch #2 {, blocks: (B:3:0x0001, B:5:0x000b, B:59:0x011a, B:61:0x0120, B:63:0x012c, B:65:0x0131, B:69:0x013d, B:71:0x0141, B:73:0x014e, B:77:0x0163, B:79:0x016b, B:82:0x0184, B:83:0x018e, B:118:0x0264, B:120:0x0268, B:121:0x026e, B:122:0x0270, B:128:0x027e, B:129:0x027f, B:130:0x0280, B:111:0x01dc, B:112:0x01f2, B:113:0x01f6, B:114:0x020d, B:115:0x0229, B:116:0x023b, B:117:0x024d, B:85:0x0192, B:88:0x019c, B:91:0x01a6, B:94:0x01af, B:97:0x01b9, B:100:0x01c3, B:80:0x0178, B:72:0x0148, B:7:0x0011, B:9:0x0021, B:14:0x003b, B:16:0x0047, B:19:0x005a, B:21:0x0060, B:22:0x0064, B:23:0x0067, B:25:0x006f, B:30:0x0089, B:32:0x0095, B:35:0x00a8, B:37:0x00ae, B:38:0x00b2, B:39:0x00b5, B:41:0x00bd, B:45:0x00cf, B:46:0x00d9, B:48:0x00e1, B:52:0x00f3, B:55:0x00ff, B:57:0x0113, B:58:0x0117, B:123:0x0271, B:125:0x0275), top: B:141:0x0001, inners: #0, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0268 A[Catch: all -> 0x0283, TryCatch #2 {, blocks: (B:3:0x0001, B:5:0x000b, B:59:0x011a, B:61:0x0120, B:63:0x012c, B:65:0x0131, B:69:0x013d, B:71:0x0141, B:73:0x014e, B:77:0x0163, B:79:0x016b, B:82:0x0184, B:83:0x018e, B:118:0x0264, B:120:0x0268, B:121:0x026e, B:122:0x0270, B:128:0x027e, B:129:0x027f, B:130:0x0280, B:111:0x01dc, B:112:0x01f2, B:113:0x01f6, B:114:0x020d, B:115:0x0229, B:116:0x023b, B:117:0x024d, B:85:0x0192, B:88:0x019c, B:91:0x01a6, B:94:0x01af, B:97:0x01b9, B:100:0x01c3, B:80:0x0178, B:72:0x0148, B:7:0x0011, B:9:0x0021, B:14:0x003b, B:16:0x0047, B:19:0x005a, B:21:0x0060, B:22:0x0064, B:23:0x0067, B:25:0x006f, B:30:0x0089, B:32:0x0095, B:35:0x00a8, B:37:0x00ae, B:38:0x00b2, B:39:0x00b5, B:41:0x00bd, B:45:0x00cf, B:46:0x00d9, B:48:0x00e1, B:52:0x00f3, B:55:0x00ff, B:57:0x0113, B:58:0x0117, B:123:0x0271, B:125:0x0275), top: B:141:0x0001, inners: #0, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x026e A[Catch: all -> 0x0283, TryCatch #2 {, blocks: (B:3:0x0001, B:5:0x000b, B:59:0x011a, B:61:0x0120, B:63:0x012c, B:65:0x0131, B:69:0x013d, B:71:0x0141, B:73:0x014e, B:77:0x0163, B:79:0x016b, B:82:0x0184, B:83:0x018e, B:118:0x0264, B:120:0x0268, B:121:0x026e, B:122:0x0270, B:128:0x027e, B:129:0x027f, B:130:0x0280, B:111:0x01dc, B:112:0x01f2, B:113:0x01f6, B:114:0x020d, B:115:0x0229, B:116:0x023b, B:117:0x024d, B:85:0x0192, B:88:0x019c, B:91:0x01a6, B:94:0x01af, B:97:0x01b9, B:100:0x01c3, B:80:0x0178, B:72:0x0148, B:7:0x0011, B:9:0x0021, B:14:0x003b, B:16:0x0047, B:19:0x005a, B:21:0x0060, B:22:0x0064, B:23:0x0067, B:25:0x006f, B:30:0x0089, B:32:0x0095, B:35:0x00a8, B:37:0x00ae, B:38:0x00b2, B:39:0x00b5, B:41:0x00bd, B:45:0x00cf, B:46:0x00d9, B:48:0x00e1, B:52:0x00f3, B:55:0x00ff, B:57:0x0113, B:58:0x0117, B:123:0x0271, B:125:0x0275), top: B:141:0x0001, inners: #0, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0141 A[Catch: all -> 0x0283, TryCatch #2 {, blocks: (B:3:0x0001, B:5:0x000b, B:59:0x011a, B:61:0x0120, B:63:0x012c, B:65:0x0131, B:69:0x013d, B:71:0x0141, B:73:0x014e, B:77:0x0163, B:79:0x016b, B:82:0x0184, B:83:0x018e, B:118:0x0264, B:120:0x0268, B:121:0x026e, B:122:0x0270, B:128:0x027e, B:129:0x027f, B:130:0x0280, B:111:0x01dc, B:112:0x01f2, B:113:0x01f6, B:114:0x020d, B:115:0x0229, B:116:0x023b, B:117:0x024d, B:85:0x0192, B:88:0x019c, B:91:0x01a6, B:94:0x01af, B:97:0x01b9, B:100:0x01c3, B:80:0x0178, B:72:0x0148, B:7:0x0011, B:9:0x0021, B:14:0x003b, B:16:0x0047, B:19:0x005a, B:21:0x0060, B:22:0x0064, B:23:0x0067, B:25:0x006f, B:30:0x0089, B:32:0x0095, B:35:0x00a8, B:37:0x00ae, B:38:0x00b2, B:39:0x00b5, B:41:0x00bd, B:45:0x00cf, B:46:0x00d9, B:48:0x00e1, B:52:0x00f3, B:55:0x00ff, B:57:0x0113, B:58:0x0117, B:123:0x0271, B:125:0x0275), top: B:141:0x0001, inners: #0, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0148 A[Catch: all -> 0x0283, TryCatch #2 {, blocks: (B:3:0x0001, B:5:0x000b, B:59:0x011a, B:61:0x0120, B:63:0x012c, B:65:0x0131, B:69:0x013d, B:71:0x0141, B:73:0x014e, B:77:0x0163, B:79:0x016b, B:82:0x0184, B:83:0x018e, B:118:0x0264, B:120:0x0268, B:121:0x026e, B:122:0x0270, B:128:0x027e, B:129:0x027f, B:130:0x0280, B:111:0x01dc, B:112:0x01f2, B:113:0x01f6, B:114:0x020d, B:115:0x0229, B:116:0x023b, B:117:0x024d, B:85:0x0192, B:88:0x019c, B:91:0x01a6, B:94:0x01af, B:97:0x01b9, B:100:0x01c3, B:80:0x0178, B:72:0x0148, B:7:0x0011, B:9:0x0021, B:14:0x003b, B:16:0x0047, B:19:0x005a, B:21:0x0060, B:22:0x0064, B:23:0x0067, B:25:0x006f, B:30:0x0089, B:32:0x0095, B:35:0x00a8, B:37:0x00ae, B:38:0x00b2, B:39:0x00b5, B:41:0x00bd, B:45:0x00cf, B:46:0x00d9, B:48:0x00e1, B:52:0x00f3, B:55:0x00ff, B:57:0x0113, B:58:0x0117, B:123:0x0271, B:125:0x0275), top: B:141:0x0001, inners: #0, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0192 A[Catch: all -> 0x0283, TryCatch #2 {, blocks: (B:3:0x0001, B:5:0x000b, B:59:0x011a, B:61:0x0120, B:63:0x012c, B:65:0x0131, B:69:0x013d, B:71:0x0141, B:73:0x014e, B:77:0x0163, B:79:0x016b, B:82:0x0184, B:83:0x018e, B:118:0x0264, B:120:0x0268, B:121:0x026e, B:122:0x0270, B:128:0x027e, B:129:0x027f, B:130:0x0280, B:111:0x01dc, B:112:0x01f2, B:113:0x01f6, B:114:0x020d, B:115:0x0229, B:116:0x023b, B:117:0x024d, B:85:0x0192, B:88:0x019c, B:91:0x01a6, B:94:0x01af, B:97:0x01b9, B:100:0x01c3, B:80:0x0178, B:72:0x0148, B:7:0x0011, B:9:0x0021, B:14:0x003b, B:16:0x0047, B:19:0x005a, B:21:0x0060, B:22:0x0064, B:23:0x0067, B:25:0x006f, B:30:0x0089, B:32:0x0095, B:35:0x00a8, B:37:0x00ae, B:38:0x00b2, B:39:0x00b5, B:41:0x00bd, B:45:0x00cf, B:46:0x00d9, B:48:0x00e1, B:52:0x00f3, B:55:0x00ff, B:57:0x0113, B:58:0x0117, B:123:0x0271, B:125:0x0275), top: B:141:0x0001, inners: #0, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x019c A[Catch: all -> 0x0283, TryCatch #2 {, blocks: (B:3:0x0001, B:5:0x000b, B:59:0x011a, B:61:0x0120, B:63:0x012c, B:65:0x0131, B:69:0x013d, B:71:0x0141, B:73:0x014e, B:77:0x0163, B:79:0x016b, B:82:0x0184, B:83:0x018e, B:118:0x0264, B:120:0x0268, B:121:0x026e, B:122:0x0270, B:128:0x027e, B:129:0x027f, B:130:0x0280, B:111:0x01dc, B:112:0x01f2, B:113:0x01f6, B:114:0x020d, B:115:0x0229, B:116:0x023b, B:117:0x024d, B:85:0x0192, B:88:0x019c, B:91:0x01a6, B:94:0x01af, B:97:0x01b9, B:100:0x01c3, B:80:0x0178, B:72:0x0148, B:7:0x0011, B:9:0x0021, B:14:0x003b, B:16:0x0047, B:19:0x005a, B:21:0x0060, B:22:0x0064, B:23:0x0067, B:25:0x006f, B:30:0x0089, B:32:0x0095, B:35:0x00a8, B:37:0x00ae, B:38:0x00b2, B:39:0x00b5, B:41:0x00bd, B:45:0x00cf, B:46:0x00d9, B:48:0x00e1, B:52:0x00f3, B:55:0x00ff, B:57:0x0113, B:58:0x0117, B:123:0x0271, B:125:0x0275), top: B:141:0x0001, inners: #0, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01a6 A[Catch: all -> 0x0283, TryCatch #2 {, blocks: (B:3:0x0001, B:5:0x000b, B:59:0x011a, B:61:0x0120, B:63:0x012c, B:65:0x0131, B:69:0x013d, B:71:0x0141, B:73:0x014e, B:77:0x0163, B:79:0x016b, B:82:0x0184, B:83:0x018e, B:118:0x0264, B:120:0x0268, B:121:0x026e, B:122:0x0270, B:128:0x027e, B:129:0x027f, B:130:0x0280, B:111:0x01dc, B:112:0x01f2, B:113:0x01f6, B:114:0x020d, B:115:0x0229, B:116:0x023b, B:117:0x024d, B:85:0x0192, B:88:0x019c, B:91:0x01a6, B:94:0x01af, B:97:0x01b9, B:100:0x01c3, B:80:0x0178, B:72:0x0148, B:7:0x0011, B:9:0x0021, B:14:0x003b, B:16:0x0047, B:19:0x005a, B:21:0x0060, B:22:0x0064, B:23:0x0067, B:25:0x006f, B:30:0x0089, B:32:0x0095, B:35:0x00a8, B:37:0x00ae, B:38:0x00b2, B:39:0x00b5, B:41:0x00bd, B:45:0x00cf, B:46:0x00d9, B:48:0x00e1, B:52:0x00f3, B:55:0x00ff, B:57:0x0113, B:58:0x0117, B:123:0x0271, B:125:0x0275), top: B:141:0x0001, inners: #0, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01af A[Catch: all -> 0x0283, TryCatch #2 {, blocks: (B:3:0x0001, B:5:0x000b, B:59:0x011a, B:61:0x0120, B:63:0x012c, B:65:0x0131, B:69:0x013d, B:71:0x0141, B:73:0x014e, B:77:0x0163, B:79:0x016b, B:82:0x0184, B:83:0x018e, B:118:0x0264, B:120:0x0268, B:121:0x026e, B:122:0x0270, B:128:0x027e, B:129:0x027f, B:130:0x0280, B:111:0x01dc, B:112:0x01f2, B:113:0x01f6, B:114:0x020d, B:115:0x0229, B:116:0x023b, B:117:0x024d, B:85:0x0192, B:88:0x019c, B:91:0x01a6, B:94:0x01af, B:97:0x01b9, B:100:0x01c3, B:80:0x0178, B:72:0x0148, B:7:0x0011, B:9:0x0021, B:14:0x003b, B:16:0x0047, B:19:0x005a, B:21:0x0060, B:22:0x0064, B:23:0x0067, B:25:0x006f, B:30:0x0089, B:32:0x0095, B:35:0x00a8, B:37:0x00ae, B:38:0x00b2, B:39:0x00b5, B:41:0x00bd, B:45:0x00cf, B:46:0x00d9, B:48:0x00e1, B:52:0x00f3, B:55:0x00ff, B:57:0x0113, B:58:0x0117, B:123:0x0271, B:125:0x0275), top: B:141:0x0001, inners: #0, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01b9 A[Catch: all -> 0x0283, TryCatch #2 {, blocks: (B:3:0x0001, B:5:0x000b, B:59:0x011a, B:61:0x0120, B:63:0x012c, B:65:0x0131, B:69:0x013d, B:71:0x0141, B:73:0x014e, B:77:0x0163, B:79:0x016b, B:82:0x0184, B:83:0x018e, B:118:0x0264, B:120:0x0268, B:121:0x026e, B:122:0x0270, B:128:0x027e, B:129:0x027f, B:130:0x0280, B:111:0x01dc, B:112:0x01f2, B:113:0x01f6, B:114:0x020d, B:115:0x0229, B:116:0x023b, B:117:0x024d, B:85:0x0192, B:88:0x019c, B:91:0x01a6, B:94:0x01af, B:97:0x01b9, B:100:0x01c3, B:80:0x0178, B:72:0x0148, B:7:0x0011, B:9:0x0021, B:14:0x003b, B:16:0x0047, B:19:0x005a, B:21:0x0060, B:22:0x0064, B:23:0x0067, B:25:0x006f, B:30:0x0089, B:32:0x0095, B:35:0x00a8, B:37:0x00ae, B:38:0x00b2, B:39:0x00b5, B:41:0x00bd, B:45:0x00cf, B:46:0x00d9, B:48:0x00e1, B:52:0x00f3, B:55:0x00ff, B:57:0x0113, B:58:0x0117, B:123:0x0271, B:125:0x0275), top: B:141:0x0001, inners: #0, #6 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized java.lang.String m_a(android.content.Context r12, java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 674
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.heytap.openid.sdk.m_b.m_a(android.content.Context, java.lang.String):java.lang.String");
    }

    public void m_b(Context context, String str) {
        if (this.m_a.containsKey(str)) {
            m_e m_eVar = this.m_a.get(str);
            if (m_eVar.m_a(str)) {
                return;
            }
            String m_a = this.m_b ? m_c.m_b.m_a.m_a(context, str) : m_f.m_b.m_a.m_a(context, str);
            long currentTimeMillis = System.currentTimeMillis() + m_a.m_d(str);
            if (m_a.equals("") || m_a == "") {
                return;
            }
            m_eVar.m_a = m_a;
            m_eVar.m_b = currentTimeMillis;
            m_a.m_a(context, m_eVar, str);
        } else if (str.equals("OUID") || str.equals("OUID_STATUS") || str == "OUID" || str == "OUID_STATUS") {
            String m_a2 = this.m_b ? m_c.m_b.m_a.m_a(context, str) : m_f.m_b.m_a.m_a(context, str);
            long currentTimeMillis2 = System.currentTimeMillis() + JDReminderNewUtils.REMINDER_DURATION_TIME_MAX;
            if (m_a2.equals("") || m_a2 == "") {
                return;
            }
            this.m_a.put(str, new m_e(m_a2, currentTimeMillis2));
        }
    }
}
