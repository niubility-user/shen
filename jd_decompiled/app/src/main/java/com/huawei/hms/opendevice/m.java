package com.huawei.hms.opendevice;

import android.content.Context;
import android.text.TextUtils;
import com.eclipsesource.v8.Platform;
import com.huawei.hms.android.HwBuildEx;
import com.huawei.hms.android.SystemUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.PackageManagerHelper;
import com.huawei.hms.utils.Util;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class m {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class a extends Thread {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;

        a(Context context, String str) {
            this.a = context;
            this.b = str;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (d.b()) {
                if (m.b(this.a)) {
                    return;
                }
                String a = com.huawei.hms.opendevice.b.a(this.a);
                if (!TextUtils.isEmpty(a)) {
                    if (!m.d(this.a, a, this.b)) {
                        HMSLog.d("ReportAaidToken", "This time need not report.");
                        return;
                    }
                    String string = g.e.a.h.a.b(this.a).getString("region");
                    if (TextUtils.isEmpty(string)) {
                        HMSLog.i("ReportAaidToken", "The data storage region is empty.");
                        return;
                    }
                    String a2 = k.a(this.a, "com.huawei.hms.opendevicesdk", "ROOT", null, string);
                    if (TextUtils.isEmpty(a2)) {
                        return;
                    }
                    String c2 = m.c(this.a, a, this.b);
                    m.b(this.a, g.a(this.a, a2 + "/rest/appdata/v1/aaid/report", c2, (Map<String, String>) null), a, this.b);
                    return;
                }
                HMSLog.w("ReportAaidToken", "AAID is empty.");
                return;
            }
            HMSLog.d("ReportAaidToken", "Not HW Phone.");
        }
    }

    /* loaded from: classes12.dex */
    public enum b {
        MOBILE("1"),
        PC("2"),
        TABLET("3"),
        TV("4"),
        SOUNDBOX("5"),
        GLASS("6"),
        WATCH("7"),
        VEHICLE("8"),
        OFFICE_DEVICE("9"),
        IOT_DEVICES("10"),
        HEALTHY("11"),
        ENTERTAINMENT("12"),
        TRANSPORT_DEVICES("13");
        
        private String a;

        b(String str) {
            this.a = str;
        }

        public String a() {
            return this.a;
        }
    }

    /* loaded from: classes12.dex */
    public enum c {
        IOS("ios"),
        ANDROID("android"),
        HARMONY("harmony"),
        WINDOWS(Platform.WINDOWS),
        EMBED("embed"),
        OTHERS("others");
        
        private String a;

        c(String str) {
            this.a = str;
        }

        public String a() {
            return this.a;
        }
    }

    public static String c(Context context, String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("timezone", TimeZone.getDefault().getID());
            jSONObject2.put("country", SystemUtils.getLocalCountry());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("agent_version", new PackageManagerHelper(context).getPackageVersionName("com.huawei.android.pushagent"));
            jSONObject3.put("hms_version", String.valueOf(Util.getHmsVersion(context)));
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("dev_type", b.MOBILE.a());
            jSONObject4.put("dev_sub_type", SignUpTable.TB_COLUMN_PHONE);
            jSONObject4.put("os_type", c.ANDROID.a());
            jSONObject4.put("os_version", String.valueOf(HwBuildEx.VERSION.EMUI_SDK_INT));
            jSONObject.put("id", UUID.randomUUID().toString());
            jSONObject.put(JshopConst.JSKEY_IS_GLOBAL, jSONObject2);
            jSONObject.put("push_agent", jSONObject3);
            jSONObject.put("hardware", jSONObject4);
            jSONObject.put("aaid", str);
            jSONObject.put("token", str2);
            jSONObject.put("app_id", g.e.a.h.a.b(context).getString("client/app_id"));
            jSONObject.put("region", g.e.a.h.a.b(context).getString("region"));
            return jSONObject.toString();
        } catch (JSONException unused) {
            HMSLog.e("ReportAaidToken", "Catch JSONException.");
            return null;
        }
    }

    public static boolean d(Context context, String str, String str2) {
        i a2 = i.a(context);
        if (!a2.containsKey("reportAaidAndToken")) {
            HMSLog.d("ReportAaidToken", "It hasn't been reported, this time needs report.");
            return true;
        }
        if (TextUtils.isEmpty(a2.getString("reportAaidAndToken"))) {
            HMSLog.w("ReportAaidToken", "It has been reported, but report value is empty, this time needs report.");
            return true;
        }
        return !r4.equals(n.a(str2 + str, MessageDigestAlgorithms.SHA_256));
    }

    public static boolean b(Context context) {
        int packageVersionCode = new PackageManagerHelper(context).getPackageVersionCode("com.huawei.android.pushagent");
        HMSLog.d("ReportAaidToken", "NC version code: " + packageVersionCode);
        return (90101400 <= packageVersionCode && packageVersionCode < 100000000) || packageVersionCode >= 100001301;
    }

    public static void a(Context context, String str) {
        new a(context, str).start();
    }

    public static void b(Context context, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            HMSLog.e("ReportAaidToken", "Https response is empty.");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("ret", 256);
            if (optInt == 0) {
                boolean saveString = i.a(context).saveString("reportAaidAndToken", n.a(str3 + str2, MessageDigestAlgorithms.SHA_256));
                StringBuilder sb = new StringBuilder();
                sb.append("Report success ");
                sb.append(saveString ? "and save success." : "but save failure.");
                HMSLog.d("ReportAaidToken", sb.toString());
                return;
            }
            HMSLog.e("ReportAaidToken", "Https response body's ret code: " + optInt + ", error message: " + jSONObject.optString("msg"));
        } catch (JSONException unused) {
            HMSLog.e("ReportAaidToken", "Has JSONException.");
        } catch (Exception unused2) {
            HMSLog.e("ReportAaidToken", "Exception occur.");
        }
    }
}
