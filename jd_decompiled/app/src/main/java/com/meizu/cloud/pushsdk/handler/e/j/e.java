package com.meizu.cloud.pushsdk.handler.e.j;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.EtModelMaker;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.handler.MzPushMessage;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class e {
    private int a;
    private String b = String.valueOf(-1);

    /* renamed from: c  reason: collision with root package name */
    private String f15965c = "";
    private String d = "";

    /* renamed from: e  reason: collision with root package name */
    private int f15966e = -1;

    /* renamed from: f  reason: collision with root package name */
    private String f15967f = "";

    /* loaded from: classes14.dex */
    public static class a {
        public String a;
        public String b;

        /* renamed from: c  reason: collision with root package name */
        String f15968c;

        public a(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (!jSONObject.isNull("code")) {
                    b(jSONObject.getString("code"));
                }
                if (!jSONObject.isNull("message")) {
                    c(jSONObject.getString("message"));
                }
                if (jSONObject.isNull("value")) {
                    return;
                }
                d(jSONObject.getString("value"));
            } catch (JSONException e2) {
                DebugLogger.e("SecurityMessage", "covert json error " + e2.getMessage());
            }
        }

        public String a() {
            return this.f15968c;
        }

        public void b(String str) {
            this.a = str;
        }

        public void c(String str) {
            this.b = str;
        }

        public void d(String str) {
            this.f15968c = str;
        }

        public String toString() {
            return "PublicKeyStatus{code='" + this.a + "', message='" + this.b + "', publicKey='" + this.f15968c + "'}";
        }
    }

    private static e b(String str) {
        e eVar = new e();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.isNull(PushConstants.PUSH_NOTIFICATION_CREATE_TIMES_TAMP)) {
                eVar.g(jSONObject.getInt(PushConstants.PUSH_NOTIFICATION_CREATE_TIMES_TAMP));
            }
            if (!jSONObject.isNull("ti")) {
                eVar.l(jSONObject.getString("ti"));
            }
            if (!jSONObject.isNull("tl")) {
                eVar.n(jSONObject.getString("tl"));
            }
            if (!jSONObject.isNull(EtModelMaker.KEY_CONT)) {
                eVar.h(jSONObject.getString(EtModelMaker.KEY_CONT));
            }
            if (!jSONObject.isNull("ct")) {
                eVar.d(jSONObject.getInt("ct"));
            }
            if (!jSONObject.isNull("pm")) {
                eVar.j(jSONObject.getString("pm"));
            }
        } catch (Exception e2) {
            DebugLogger.e("SecurityMessage", "parse decryptSign error " + e2.getMessage());
        }
        return eVar;
    }

    public static String c(MessageV3 messageV3) {
        JSONObject jSONObject;
        boolean isEmpty;
        String notificationMessage = messageV3.getNotificationMessage();
        String str = null;
        try {
            try {
                if (!TextUtils.isEmpty(notificationMessage)) {
                    try {
                        JSONObject jSONObject2 = new JSONObject(notificationMessage).getJSONObject("data");
                        if (!jSONObject2.isNull("extra")) {
                            JSONObject jSONObject3 = jSONObject2.getJSONObject("extra");
                            if (!jSONObject3.isNull("se")) {
                                str = jSONObject3.getString("se");
                            }
                        }
                    } catch (JSONException e2) {
                        DebugLogger.e("SecurityMessage", "parse notification message error " + e2.getMessage());
                        if (TextUtils.isEmpty(null)) {
                            jSONObject = new JSONObject(notificationMessage);
                        }
                    }
                    if (isEmpty) {
                        jSONObject = new JSONObject(notificationMessage);
                        jSONObject.getString("se");
                    }
                }
            } finally {
                if (TextUtils.isEmpty(null)) {
                    try {
                        new JSONObject(notificationMessage).getString("se");
                    } catch (Exception unused) {
                    }
                }
            }
        } catch (Exception unused2) {
        }
        DebugLogger.i("SecurityMessage", "encrypt message " + str);
        return str;
    }

    public static boolean e(String str, MessageV3 messageV3) {
        String str2;
        e b = b(str);
        DebugLogger.e("SecurityMessage", "securityMessage " + b);
        if (System.currentTimeMillis() / 1000 > b.m()) {
            str2 = "message expire";
        } else if (!messageV3.getTitle().contains(b.o())) {
            str2 = "invalid title";
        } else if (!messageV3.getContent().contains(b.f())) {
            str2 = "invalid content";
        } else if (!String.valueOf(-1).equals(b.k()) && !b.k().equals(messageV3.getTaskId())) {
            str2 = "invalid taskId";
        } else if (b.a() != -1) {
            int a2 = b.a();
            if (a2 == 1) {
                if (!messageV3.getActivity().contains(b.i())) {
                    str2 = "invalid click activity";
                }
                return true;
            } else if (a2 == 2) {
                if (!messageV3.getWebUrl().contains(b.i())) {
                    str2 = "invalid web url";
                }
                return true;
            } else {
                if (a2 == 3 && !MzPushMessage.fromMessageV3(messageV3).getSelfDefineContentString().contains(b.i())) {
                    str2 = "invalid self define";
                }
                return true;
            }
        } else {
            str2 = "invalid click type";
        }
        DebugLogger.e("SecurityMessage", str2);
        return false;
    }

    public int a() {
        return this.f15966e;
    }

    public void d(int i2) {
        this.f15966e = i2;
    }

    public String f() {
        return this.d;
    }

    public void g(int i2) {
        this.a = i2;
    }

    public void h(String str) {
        this.d = str;
    }

    public String i() {
        return this.f15967f;
    }

    public void j(String str) {
        this.f15967f = str;
    }

    public String k() {
        return this.b;
    }

    public void l(String str) {
        this.b = str;
    }

    public int m() {
        return this.a;
    }

    public void n(String str) {
        this.f15965c = str;
    }

    public String o() {
        return this.f15965c;
    }

    public String toString() {
        return "SecurityMessage{timestamp=" + this.a + ", taskId='" + this.b + "', title='" + this.f15965c + "', content='" + this.d + "', clickType=" + this.f15966e + ", params='" + this.f15967f + "'}";
    }
}
