package com.heytap.openid.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.heytap.openid.sdk.m_c;
import com.heytap.openid.sdk.m_f;
import com.jingdong.common.utils.JDReminderNewUtils;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import jd.wjlogin_sdk.util.e;
import org.apache.commons.codec.CharEncoding;

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
    */
    public synchronized String m_a(Context context, String str) {
        String str2;
        m_e m_eVar;
        String str3;
        HashMap<String, m_e> hashMap = this.m_a;
        char c2 = 0;
        if (hashMap.isEmpty() || !hashMap.containsKey(str)) {
            try {
                SharedPreferences sharedPreferences = context.getSharedPreferences("cache", 0);
                if (!hashMap.containsKey(e.d)) {
                    String string = sharedPreferences.getString(e.d, null);
                    long j2 = sharedPreferences.getLong("GUID_TIME", 0L);
                    String string2 = sharedPreferences.getString("GUID_IV", null);
                    if (string != null && j2 != 0 && string2 != null) {
                        try {
                            byte[] m_a = m_a.m_a(m_a.m_a("U3RkSWRBcHBLZXk="), string, string2);
                            if (m_a != null) {
                                hashMap.put(e.d, new m_e(new String(m_a, CharEncoding.ISO_8859_1), j2));
                            }
                        } catch (UnsupportedEncodingException e2) {
                            if (e2.getMessage() != null) {
                                e2.getMessage();
                            } else {
                                e2.getLocalizedMessage();
                            }
                        }
                    }
                }
                if (!hashMap.containsKey("APID")) {
                    String string3 = sharedPreferences.getString("APID", null);
                    long j3 = sharedPreferences.getLong("APID_TIME", 0L);
                    String string4 = sharedPreferences.getString("APID_IV", null);
                    if (string3 != null && j3 != 0 && string4 != null) {
                        try {
                            byte[] m_a2 = m_a.m_a(m_a.m_a("U3RkSWRBcHBLZXk="), string3, string4);
                            if (m_a2 != null) {
                                hashMap.put("APID", new m_e(new String(m_a2, CharEncoding.ISO_8859_1), j3));
                            }
                        } catch (UnsupportedEncodingException e3) {
                            if (e3.getMessage() != null) {
                                e3.getMessage();
                            } else {
                                e3.getLocalizedMessage();
                            }
                        }
                    }
                }
                if (!hashMap.containsKey("DUID")) {
                    String string5 = sharedPreferences.getString("DUID", null);
                    long j4 = sharedPreferences.getLong("DUID_TIME", 0L);
                    if (string5 != null && j4 != 0) {
                        hashMap.put("DUID", new m_e(string5, j4));
                    }
                }
                if (!hashMap.containsKey("AUID")) {
                    String string6 = sharedPreferences.getString("AUID", null);
                    long j5 = sharedPreferences.getLong("AUID_TIME", 0L);
                    if (string6 != null && j5 != 0) {
                        hashMap.put("AUID", new m_e(string6, j5));
                    }
                }
            } catch (IllegalStateException e4) {
                if (("1020:" + e4.getMessage()) != null) {
                    e4.getMessage();
                } else {
                    e4.getLocalizedMessage();
                }
            }
        }
        if (hashMap.containsKey(str)) {
            m_e m_eVar2 = hashMap.get(str);
            if (m_eVar2.m_a(str)) {
                str2 = m_eVar2.m_a;
                if (!str2.equals("") || str2 == "") {
                    str2 = !this.m_b ? m_c.m_b.m_a.m_a(context, str) : m_f.m_b.m_a.m_a(context, str);
                    long currentTimeMillis = System.currentTimeMillis() + m_a.m_d(str);
                    if (!str2.equals("") || str2 == "") {
                        m_eVar = null;
                    } else if (this.m_a.containsKey(str)) {
                        m_eVar = this.m_a.get(str);
                        m_eVar.m_a = str2;
                        m_eVar.m_b = currentTimeMillis;
                    } else {
                        m_eVar = new m_e(str2, currentTimeMillis);
                        this.m_a.put(str, m_eVar);
                    }
                    switch (str.hashCode()) {
                        case 2015626:
                            if (str.equals("APID")) {
                                c2 = 5;
                                break;
                            }
                            c2 = '\uffff';
                            break;
                        case 2020431:
                            if (str.equals("AUID")) {
                                c2 = 4;
                                break;
                            }
                            c2 = '\uffff';
                            break;
                        case 2109804:
                            if (str.equals("DUID")) {
                                c2 = 3;
                                break;
                            }
                            c2 = '\uffff';
                            break;
                        case 2199177:
                            if (str.equals(e.d)) {
                                break;
                            }
                            c2 = '\uffff';
                            break;
                        case 2437505:
                            if (str.equals("OUID")) {
                                c2 = 2;
                                break;
                            }
                            c2 = '\uffff';
                            break;
                        case 572132464:
                            if (str.equals("OUID_STATUS")) {
                                c2 = 1;
                                break;
                            }
                            c2 = '\uffff';
                            break;
                        default:
                            c2 = '\uffff';
                            break;
                    }
                    if (c2 != 0) {
                        m_a.m_a(context, m_eVar, e.d);
                        m_b(context, "APID");
                        m_b(context, "OUID");
                        m_b(context, "AUID");
                        str3 = "OUID_STATUS";
                    } else if (c2 == 1) {
                        m_b(context, e.d);
                        m_b(context, "APID");
                        m_b(context, "OUID");
                        str3 = "AUID";
                    } else if (c2 == 2) {
                        m_b(context, e.d);
                        m_b(context, "APID");
                        m_b(context, "OUID_STATUS");
                        str3 = "AUID";
                    } else if (c2 == 3) {
                        m_a.m_a(context, m_eVar, "DUID");
                        m_b(context, e.d);
                        m_b(context, "APID");
                        m_b(context, "OUID");
                        m_b(context, "OUID_STATUS");
                        str3 = "AUID";
                    } else if (c2 == 4) {
                        m_a.m_a(context, m_eVar, "AUID");
                        m_b(context, e.d);
                        m_b(context, "APID");
                        m_b(context, "OUID");
                        str3 = "OUID_STATUS";
                    } else if (c2 == 5) {
                        m_a.m_a(context, m_eVar, "APID");
                        m_b(context, e.d);
                        m_b(context, "AUID");
                        m_b(context, "OUID");
                        str3 = "OUID_STATUS";
                    } else if (this.m_b) {
                        m_c.m_b.m_a.m_a(context);
                    } else {
                        m_f m_fVar = m_f.m_b.m_a;
                        synchronized (m_fVar) {
                            try {
                                if (m_fVar.m_a != null) {
                                    context.unbindService(m_fVar.m_e);
                                    m_fVar.m_a = null;
                                }
                            } catch (Exception unused) {
                            }
                        }
                    }
                    m_b(context, str3);
                    if (this.m_b) {
                    }
                }
            }
        }
        str2 = "";
        if (!str2.equals("")) {
        }
        if (!this.m_b) {
        }
        long currentTimeMillis2 = System.currentTimeMillis() + m_a.m_d(str);
        if (str2.equals("")) {
        }
        m_eVar = null;
        switch (str.hashCode()) {
            case 2015626:
                break;
            case 2020431:
                break;
            case 2109804:
                break;
            case 2199177:
                break;
            case 2437505:
                break;
            case 572132464:
                break;
        }
        if (c2 != 0) {
        }
        m_b(context, str3);
        if (this.m_b) {
        }
        return str2;
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
