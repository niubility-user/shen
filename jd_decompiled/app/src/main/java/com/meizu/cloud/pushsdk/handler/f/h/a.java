package com.meizu.cloud.pushsdk.handler.f.h;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.utils.LangUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.d.i;
import com.meizu.cloud.pushsdk.e.b.c;
import com.meizu.cloud.pushsdk.handler.f.h.b;
import com.meizu.cloud.pushsdk.platform.message.BasicPushStatus;
import java.io.File;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class a {
    private static final String[] b = {"^MEIZU17(Pro)*$", "^MEIZU18(Pro)*$"};

    /* renamed from: c  reason: collision with root package name */
    private static final String[] f15985c = {"^.+$", "^.+$"};
    private com.meizu.cloud.pushsdk.handler.f.h.b a;

    /* loaded from: classes14.dex */
    private static class b {
        private static a a = new a();
    }

    private a() {
    }

    public static a a() {
        return b.a;
    }

    private synchronized com.meizu.cloud.pushsdk.handler.f.h.b b(Context context) {
        DebugLogger.i("PushConfig", "getPushConfigInfo start, mPushConfigInfo = " + this.a);
        com.meizu.cloud.pushsdk.handler.f.h.b bVar = this.a;
        if (bVar != null && bVar.i()) {
            DebugLogger.i("PushConfig", "getPushConfigInfo have cache and effective time, return directly");
            return this.a;
        }
        if (this.a == null) {
            com.meizu.cloud.pushsdk.handler.f.h.b j2 = j(context);
            this.a = j2;
            if (j2 != null) {
                DebugLogger.i("PushConfig", "getPushConfigInfo to load, mPushConfigInfo = " + this.a);
                return this.a;
            }
        }
        com.meizu.cloud.pushsdk.handler.f.h.b l2 = l(context);
        this.a = l2;
        if (l2 != null) {
            DebugLogger.i("PushConfig", "getPushConfigInfo to network, mPushConfigInfo = " + this.a);
            return this.a;
        }
        this.a = i();
        DebugLogger.i("PushConfig", "getPushConfigInfo to default, mPushConfigInfo = " + this.a);
        return this.a;
    }

    private com.meizu.cloud.pushsdk.handler.f.h.b c(JSONObject jSONObject) {
        JSONArray jSONArray;
        JSONArray jSONArray2;
        JSONArray jSONArray3;
        DebugLogger.i("PushConfig", "analysis config jsonObjectValue = " + jSONObject);
        if (jSONObject == null) {
            return null;
        }
        try {
            com.meizu.cloud.pushsdk.handler.f.h.b bVar = new com.meizu.cloud.pushsdk.handler.f.h.b();
            if (jSONObject.has("requestTime")) {
                bVar.c(jSONObject.getLong("requestTime"));
            }
            if (jSONObject.has("intervalHour")) {
                bVar.b(jSONObject.getInt("intervalHour"));
            }
            if (jSONObject.has("shieldPackage") && (jSONArray3 = jSONObject.getJSONArray("shieldPackage")) != null) {
                for (int i2 = 0; i2 < jSONArray3.length(); i2++) {
                    bVar.e(jSONArray3.getString(i2));
                }
            }
            if (jSONObject.has("whitePackage") && (jSONArray2 = jSONObject.getJSONArray("whitePackage")) != null) {
                for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                    bVar.g(jSONArray2.getString(i3));
                }
            }
            if (jSONObject.has("shieldConfig") && (jSONArray = jSONObject.getJSONArray("shieldConfig")) != null) {
                for (int i4 = 0; i4 < jSONArray.length(); i4++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i4);
                    if (jSONObject2 != null && jSONObject2.has(CustomThemeConstance.NAVI_MODEL) && jSONObject2.has("os")) {
                        bVar.d(new b.a(jSONObject2.getString(CustomThemeConstance.NAVI_MODEL), jSONObject2.getString("os")));
                    }
                }
            }
            return bVar;
        } catch (Exception e2) {
            DebugLogger.e("PushConfig", "analysis config error, " + e2.getMessage());
            e2.printStackTrace();
            return null;
        }
    }

    private void d(Context context, JSONObject jSONObject) {
        DebugLogger.i("PushConfig", "save local config jsonObjectValue = " + jSONObject);
        String jSONObject2 = jSONObject.toString();
        File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        if (externalFilesDir != null) {
            com.meizu.cloud.pushsdk.notification.g.a.f(externalFilesDir.getPath() + "/push_config", jSONObject2);
        }
    }

    private boolean f(com.meizu.cloud.pushsdk.handler.f.h.b bVar) {
        if (bVar != null && bVar.a() != null) {
            String a = i.a("ro.product.model");
            String a2 = i.a("ro.build.display.id");
            if (!TextUtils.isEmpty(a) && !TextUtils.isEmpty(a2)) {
                for (int i2 = 0; i2 < bVar.a().size(); i2++) {
                    b.a aVar = bVar.a().get(i2);
                    if (aVar != null && !TextUtils.isEmpty(aVar.a()) && !TextUtils.isEmpty(aVar.b()) && h(aVar.a(), a) && h(aVar.b(), a2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean g(com.meizu.cloud.pushsdk.handler.f.h.b bVar, String str) {
        if (!TextUtils.isEmpty(str) && bVar != null && bVar.f() != null) {
            for (int i2 = 0; i2 < bVar.f().size(); i2++) {
                String str2 = bVar.f().get(i2);
                if (!TextUtils.isEmpty(str2) && h(str2, str)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean h(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        String replace = str2.toLowerCase().replace(LangUtils.SINGLE_SPACE, "");
        if (lowerCase.startsWith("^") || lowerCase.endsWith("$")) {
            boolean matches = Pattern.compile(lowerCase).matcher(replace).matches();
            DebugLogger.i("PushConfig", lowerCase + " matches " + replace + " is " + matches);
            return matches;
        } else if (lowerCase.equalsIgnoreCase(replace)) {
            DebugLogger.i("PushConfig", lowerCase + " equalsIgnoreCase " + replace + " is true");
            return true;
        } else {
            return false;
        }
    }

    private com.meizu.cloud.pushsdk.handler.f.h.b i() {
        com.meizu.cloud.pushsdk.handler.f.h.b bVar = new com.meizu.cloud.pushsdk.handler.f.h.b();
        bVar.c(System.currentTimeMillis());
        bVar.b(2);
        bVar.e("^com\\.(meizu|flyme)(\\..+)+$");
        String[] strArr = b;
        String str = strArr[0];
        String[] strArr2 = f15985c;
        bVar.d(new b.a(str, strArr2[0]));
        bVar.d(new b.a(strArr[1], strArr2[1]));
        return bVar;
    }

    private com.meizu.cloud.pushsdk.handler.f.h.b j(Context context) {
        com.meizu.cloud.pushsdk.handler.f.h.b c2 = c(m(context));
        if (c2 == null || !c2.i()) {
            return null;
        }
        return c2;
    }

    private boolean k(com.meizu.cloud.pushsdk.handler.f.h.b bVar, String str) {
        if (!TextUtils.isEmpty(str) && bVar != null && bVar.h() != null) {
            for (int i2 = 0; i2 < bVar.h().size(); i2++) {
                String str2 = bVar.h().get(i2);
                if (!TextUtils.isEmpty(str2) && h(str2, str)) {
                    return true;
                }
            }
        }
        return false;
    }

    private com.meizu.cloud.pushsdk.handler.f.h.b l(Context context) {
        c j2 = com.meizu.cloud.pushsdk.e.a.b(PushConstants.GET_PUSH_CONFIG).c().j();
        if (j2 == null) {
            DebugLogger.e("PushConfig", "network request config fail");
            return null;
        }
        JSONObject jSONObject = (JSONObject) j2.e();
        DebugLogger.i("PushConfig", "network request config result is:" + j2.e());
        if (jSONObject != null) {
            try {
                if (jSONObject.has("code") && BasicPushStatus.SUCCESS_CODE.equals(jSONObject.getString("code")) && jSONObject.has("value")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("value");
                    if (jSONObject2 == null) {
                        DebugLogger.e("PushConfig", "network request config fail");
                        return null;
                    }
                    jSONObject2.put("requestTime", System.currentTimeMillis());
                    d(context, jSONObject2);
                    return c(jSONObject2);
                }
            } catch (Exception e2) {
                DebugLogger.e("PushConfig", "network request config error, " + e2.getMessage());
                e2.printStackTrace();
                return null;
            }
        }
        DebugLogger.e("PushConfig", "network request config fail");
        return null;
    }

    private JSONObject m(Context context) {
        File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        if (externalFilesDir == null) {
            return null;
        }
        try {
            String e2 = com.meizu.cloud.pushsdk.notification.g.a.e(externalFilesDir.getPath() + "/push_config");
            if (!TextUtils.isEmpty(e2)) {
                return new JSONObject(e2);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    public boolean e(Context context, String str) {
        String str2;
        com.meizu.cloud.pushsdk.handler.f.h.b b2 = b(context);
        if (b2 == null) {
            str2 = "check message effective, pushConfigInfo is null";
        } else if (!k(b2, str)) {
            if (g(b2, str) && f(b2)) {
                DebugLogger.i("PushConfig", "check message effective, matching shield package success");
                return false;
            }
            return true;
        } else {
            str2 = "check message effective, matching white package success";
        }
        DebugLogger.i("PushConfig", str2);
        return true;
    }
}
