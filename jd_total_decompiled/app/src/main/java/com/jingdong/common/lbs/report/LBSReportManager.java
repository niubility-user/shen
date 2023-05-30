package com.jingdong.common.lbs.report;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.google.common.net.HttpHeaders;
import com.jdpay.net.http.HTTP;
import com.jingdong.common.lbs.utils.AESUtil;
import com.jingdong.common.lbs.utils.a;
import com.jingdong.common.lbs.utils.c;
import com.jingdong.common.lbs.utils.d;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class LBSReportManager {
    public static final String HOST_CCF = "https://ccflbs.m.jd.com/lbsconfig";
    public static final String HOST_CCF_BETA = "http://ccf.m.jd.care/lbsconfig";
    public static final String HOST_M_2 = "https://lbsgw.m.jd.com/m2";
    public static final String HOST_M_2_BETA = "http://beta-lbsapi.m.jd.com/m2";
    public static final String KEY_CCF_CONFIG = "ccfConfig";
    private static LBSReportManager lbsReportManager;
    private JSONObject mConfigObj;
    private long mLastCCFTime = 0;
    private boolean useCCF = true;

    private int getCCFInterval() {
        JSONObject jSONObject = this.mConfigObj;
        if (jSONObject != null) {
            return jSONObject.optInt("syncIntvl", 300);
        }
        return 300;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getConfigVer() {
        JSONObject jSONObject = this.mConfigObj;
        return jSONObject != null ? jSONObject.optString("configVer", "") : "";
    }

    public static LBSReportManager getInstance() {
        LBSReportManager lBSReportManager;
        LBSReportManager lBSReportManager2 = lbsReportManager;
        if (lBSReportManager2 != null) {
            return lBSReportManager2;
        }
        synchronized (LBSReportManager.class) {
            if (lbsReportManager == null) {
                lbsReportManager = new LBSReportManager();
            }
            lBSReportManager = lbsReportManager;
        }
        return lBSReportManager;
    }

    private int getLBSLogSwitch() {
        JSONObject jSONObject = this.mConfigObj;
        if (jSONObject != null) {
            return jSONObject.optInt("lbsnewreportswitch", 0);
        }
        return 0;
    }

    private List<String> getNoReportBusinessIDList() {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = this.mConfigObj;
        if (jSONObject != null) {
            try {
                JSONArray optJSONArray = new JSONObject(jSONObject.optString("unreportlist", "{}")).optJSONArray(ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID);
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        arrayList.add(optJSONArray.getString(i2));
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    private void report(final String str, final String str2) {
        try {
            d.a();
            d.a(new Runnable() { // from class: com.jingdong.common.lbs.report.LBSReportManager.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        HashMap hashMap = new HashMap();
                        hashMap.put(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
                        String aESKey = AESUtil.getAESKey();
                        Uri.Builder builder = new Uri.Builder();
                        builder.appendQueryParameter("d", AESUtil.encrypt(str2, aESKey));
                        builder.appendQueryParameter("k", com.jingdong.common.lbs.utils.b.a(aESKey));
                        String encodedQuery = builder.build().getEncodedQuery();
                        a.C0428a c0428a = new a.C0428a();
                        c0428a.a = str;
                        c0428a.f12387c = hashMap;
                        c0428a.d = encodedQuery;
                        c0428a.f12388e = 1;
                        Pair<Integer, byte[]> a = c0428a.a().a();
                        if (((Integer) a.first).intValue() == 200) {
                            new String((byte[]) a.second);
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public String genCCFBody(String str) {
        try {
            String str2 = "";
            char[] charArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
            Random random = new Random();
            for (int i2 = 0; i2 < 9; i2++) {
                str2 = str2 + charArray[random.nextInt(charArray.length)];
            }
            return str2 + new String(Base64.encode(str.getBytes(), 2));
        } catch (Exception unused) {
            return str;
        }
    }

    public int getBusinessDistance() {
        JSONObject jSONObject = this.mConfigObj;
        if (jSONObject != null) {
            return jSONObject.optInt("businessdistance", 500);
        }
        return 0;
    }

    public int getBusinessInterval() {
        JSONObject jSONObject = this.mConfigObj;
        if (jSONObject != null) {
            return jSONObject.optInt("businessinterval", 600);
        }
        return 0;
    }

    public JSONObject getConfigObj() {
        JSONObject jSONObject = this.mConfigObj;
        return jSONObject == null ? new JSONObject() : jSONObject;
    }

    public void getLBSReportConfig() {
        try {
            if (isUseCCF() && System.currentTimeMillis() - this.mLastCCFTime >= getCCFInterval() * 1000) {
                this.mLastCCFTime = System.currentTimeMillis();
                d.a();
                d.a(new Runnable() { // from class: com.jingdong.common.lbs.report.LBSReportManager.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            HashMap hashMap = new HashMap();
                            hashMap.put(HttpHeaders.CONTENT_TYPE, HTTP.CONTENT_TYPE_JSON);
                            a aVar = new a();
                            aVar.a = LBSReportManager.this.getConfigVer();
                            String genCCFBody = LBSReportManager.this.genCCFBody(aVar.a());
                            a.C0428a c0428a = new a.C0428a();
                            c0428a.a = LBSReportManager.HOST_CCF;
                            c0428a.f12387c = hashMap;
                            c0428a.d = genCCFBody;
                            c0428a.f12388e = 1;
                            Pair<Integer, byte[]> a = c0428a.a().a();
                            if (((Integer) a.first).intValue() == 200) {
                                JSONObject jSONObject = new JSONObject(new String((byte[]) a.second));
                                if (jSONObject.length() != 0) {
                                    LBSReportManager.this.mConfigObj = jSONObject;
                                    c.a(LBSReportManager.KEY_CCF_CONFIG, LBSReportManager.this.mConfigObj.toString());
                                }
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public List<String> getNoLocationBusinessIDList() {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = this.mConfigObj;
        if (jSONObject != null) {
            try {
                JSONArray optJSONArray = new JSONObject(jSONObject.optString("unlocationlist", "{}")).optJSONArray(ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID);
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        arrayList.add(optJSONArray.getString(i2));
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    public int getSameAddressDistance() {
        JSONObject jSONObject = this.mConfigObj;
        if (jSONObject != null) {
            return jSONObject.optInt("SameAddressDistance", 100);
        }
        return 100;
    }

    public int getThreadSwitch() {
        JSONObject jSONObject = this.mConfigObj;
        if (jSONObject != null) {
            return jSONObject.optInt("threadswitch", 0);
        }
        return 0;
    }

    public void init() {
        try {
            getLBSReportConfig();
            String string = c.a().getString(KEY_CCF_CONFIG, "");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            this.mConfigObj = new JSONObject(string);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean isUseCCF() {
        return this.useCCF;
    }

    public void reportLBSLog(b bVar) {
        if (bVar == null) {
            return;
        }
        try {
            if (getLBSLogSwitch() != 1) {
                return;
            }
            if (getNoReportBusinessIDList().contains(TextUtils.isEmpty(bVar.a) ? "" : bVar.a)) {
                return;
            }
            report(HOST_M_2, bVar.a());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setConfig(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.length() != 0) {
                this.mConfigObj = jSONObject;
                c.a(KEY_CCF_CONFIG, jSONObject.toString());
            }
        } catch (Exception unused) {
        }
    }

    public void setUseCCF(boolean z) {
        this.useCCF = z;
    }
}
