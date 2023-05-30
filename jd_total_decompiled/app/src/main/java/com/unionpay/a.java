package com.unionpay;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.unionpay.utils.UPUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes11.dex */
final class a implements Handler.Callback {
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003d, code lost:
        if (r11 == false) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x014f, code lost:
        if (r11 == false) goto L49;
     */
    @Override // android.os.Handler.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean handleMessage(Message message) {
        boolean z;
        String str;
        Context q;
        String str2;
        Context q2;
        String str3;
        String str4;
        Context q3;
        String str5;
        String str6;
        String str7;
        boolean z2;
        JSONArray b;
        Context q4;
        String str8;
        String str9;
        Context q5;
        JSONArray jSONArray;
        int i2;
        Handler handler;
        boolean z3;
        String str10 = "0";
        switch (message.what) {
            case 1001:
                UPPayAssistEx.j();
                q5 = UPPayAssistEx.q();
                jSONArray = UPPayAssistEx.ab;
                i2 = UPPayAssistEx.S;
                UPPayAssistEx.a(q5, jSONArray, i2);
                break;
            case 1002:
                try {
                    if (message.obj != null) {
                        JSONObject jSONObject = new JSONObject((String) message.obj);
                        String a = com.unionpay.utils.i.a(jSONObject, "sign");
                        int i3 = 0;
                        try {
                            str9 = UPPayAssistEx.O;
                            i3 = Integer.parseInt(str9);
                        } catch (Exception unused) {
                        }
                        String str11 = new String(Base64.decode(jSONObject.getString("configs"), 2));
                        String str12 = "";
                        String str13 = jSONObject.has("sePayConf") ? new String(Base64.decode(jSONObject.getString("sePayConf"), 2)) : "";
                        if (!TextUtils.isEmpty(str13)) {
                            str12 = str13;
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append(str11);
                        sb.append(str12);
                        str = UPPayAssistEx.L;
                        sb.append(str);
                        String b2 = com.unionpay.utils.b.b(UPUtils.a(sb.toString()));
                        String forConfig = UPUtils.forConfig(i3, a);
                        if (!TextUtils.isEmpty(forConfig) && forConfig.equals(b2)) {
                            q = UPPayAssistEx.q();
                            StringBuilder sb2 = new StringBuilder("configs");
                            str2 = UPPayAssistEx.G;
                            sb2.append(str2);
                            UPUtils.a(q, (String) message.obj, sb2.toString());
                            q2 = UPPayAssistEx.q();
                            str3 = UPPayAssistEx.O;
                            StringBuilder sb3 = new StringBuilder("mode");
                            str4 = UPPayAssistEx.G;
                            sb3.append(str4);
                            UPUtils.a(q2, str3, sb3.toString());
                            q3 = UPPayAssistEx.q();
                            str5 = UPPayAssistEx.L;
                            StringBuilder sb4 = new StringBuilder("or");
                            str6 = UPPayAssistEx.G;
                            sb4.append(str6);
                            UPUtils.a(q3, str5, sb4.toString());
                            str7 = UPPayAssistEx.E;
                            if (!TextUtils.isEmpty(str7)) {
                                q4 = UPPayAssistEx.q();
                                StringBuilder sb5 = new StringBuilder("se_configs");
                                str8 = UPPayAssistEx.E;
                                sb5.append(str8);
                                UPUtils.a(q4, str12, sb5.toString());
                            }
                            z2 = UPPayAssistEx.T;
                            if (!z2) {
                                b = UPPayAssistEx.b(new JSONArray(str11), "sort");
                                JSONArray unused2 = UPPayAssistEx.ab = b;
                                UPPayAssistEx.d(str12);
                            }
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                z = UPPayAssistEx.T;
                break;
            case 1003:
                handler = UPPayAssistEx.W;
                handler.removeMessages(1004);
                try {
                    Object obj = message.obj;
                    if (obj instanceof Integer) {
                        if (((Integer) obj).intValue() == 1) {
                            str10 = "1";
                        }
                    }
                } catch (Exception unused3) {
                }
                z3 = UPPayAssistEx.U;
                break;
            case 1004:
                com.unionpay.utils.j.c("uppay", "QUERY_CAPACITY_TIME_OUT");
                UPPayAssistEx.m();
                UPPayAssistEx.c(str10);
                break;
        }
        return true;
    }
}
