package com.tencent.mapsdk.internal;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.tencent.map.tools.json.JsonParser;
import com.tencent.map.tools.json.JsonUtils;
import com.tencent.map.tools.net.NetResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class mf extends AsyncTask<Object, Void, Void> {

    /* renamed from: g */
    public static final int f16877g = 10000;
    private Handler a;
    private String b;

    /* renamed from: c */
    private String f16878c;
    private d d;

    /* renamed from: e */
    private c f16879e;

    /* renamed from: f */
    private Handler f16880f = new a(Looper.myLooper());

    /* loaded from: classes9.dex */
    public class a extends Handler {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Looper looper) {
            super(looper);
            mf.this = r1;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 10000) {
                return;
            }
            new mf(mf.this.a, mf.this.b, mf.this.f16878c, mf.this.d).execute(new Object[0]);
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Runnable {
        public final /* synthetic */ JSONObject a;

        public b(JSONObject jSONObject) {
            mf.this = r1;
            this.a = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (mf.this.d != null) {
                mf.this.d.a(mf.this.f16879e, this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class c implements JsonParser {
        public r5 a;
        public JSONArray b;

        /* renamed from: c */
        public JSONObject f16881c;
        public rf d;

        /* renamed from: e */
        public int f16882e = gh.r;

        @Override // com.tencent.map.tools.json.JsonParser
        public void parse(JSONObject jSONObject) {
            JSONObject optJSONObject;
            if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject("cfg")) == null) {
                return;
            }
            this.b = optJSONObject.optJSONObject("custom_map_style").optJSONArray("style_list");
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("indoor_map");
            if (optJSONObject2 != null) {
                int optInt = optJSONObject2.optInt("enable", -1);
                int optInt2 = optJSONObject2.optInt("type", -1);
                if (optInt != -1 && optInt2 != -1) {
                    this.a = new r5(optInt, optInt2, optJSONObject2.optJSONArray("building_list"));
                }
            }
            JSONObject optJSONObject3 = optJSONObject.optJSONObject("custom_layer");
            if (optJSONObject3 != null) {
                this.d = (rf) JsonUtils.parseToModel(optJSONObject3, rf.class, new Object[0]);
            }
            JSONObject optJSONObject4 = optJSONObject.optJSONObject("event_map");
            if (optJSONObject4 != null) {
                this.f16882e = optJSONObject4.optInt("enable", gh.r);
            }
            this.f16881c = optJSONObject.optJSONObject("data_layer");
        }
    }

    /* loaded from: classes9.dex */
    public interface d {
        void a(c cVar, JSONObject jSONObject);
    }

    public mf(Handler handler, String str, String str2, d dVar) {
        this.a = handler;
        this.d = dVar;
        this.b = TextUtils.isEmpty(str) ? "" : str;
        this.f16878c = TextUtils.isEmpty(str2) ? "" : str2;
    }

    private void a(int i2, String str) {
        ArrayList arrayList = new ArrayList(4);
        arrayList.add("\u817e\u8baf\u5730\u56fe\u9274\u6743\u5931\u8d25\uff0c\u8bf7\u8bbf\u95ee lbs.qq.com \u68c0\u67e5 key \u914d\u7f6e");
        arrayList.add("\u9519\u8bef\u7801\uff1a" + i2);
        arrayList.add("\u9519\u8bef\u4fe1\u606f\uff1a" + str);
        StringBuilder sb = new StringBuilder(1024);
        sb.append("Auth Fail:\n");
        char[] cArr = new char[81];
        Arrays.fill(cArr, '*');
        cArr[80] = '\n';
        sb.append(cArr);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append((String) it.next());
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        sb.append(cArr);
        sb.toString();
    }

    private void a(JSONObject jSONObject) {
        this.f16879e = (c) JsonUtils.parseToModel(jSONObject.optJSONObject("detail"), c.class, new Object[0]);
        this.a.post(new b(jSONObject));
    }

    private String b() {
        String G = b7.G();
        return TextUtils.isEmpty(G) ? "0" : "wifi".equals(G) ? "2" : "1";
    }

    private void b(JSONObject jSONObject) {
        String str;
        int i2;
        JSONObject optJSONObject = jSONObject.optJSONObject("info");
        if (optJSONObject != null) {
            i2 = optJSONObject.optInt("error");
            str = optJSONObject.optString("msg");
        } else {
            str = "";
            i2 = 0;
        }
        if (i2 == 0) {
            b7.D = 0;
            return;
        }
        a(i2, str);
        b7.D = i2 < -400 ? -1 : 1;
    }

    private void c() {
        if (this.a != null) {
            u5 u5Var = new u5();
            u5Var.a = 3;
            this.a.sendMessage(this.a.obtainMessage(3, u5Var));
        }
    }

    @Override // android.os.AsyncTask
    /* renamed from: a */
    public Void doInBackground(Object... objArr) {
        boolean a2 = a();
        if (b7.D == 2) {
            this.f16880f.sendEmptyMessageDelayed(10000, 10000L);
        }
        if (a2) {
            return null;
        }
        c();
        return null;
    }

    public boolean a() {
        String str;
        f3 f3Var = (f3) l2.a(f3.class);
        if (f3Var == null) {
            return false;
        }
        NetResponse checkAuth = ((t2) f3Var.d()).checkAuth(b7.t(), this.b, b7.N(), this.f16878c, b7.y(), b7.A(), b7.I(), b7.M(), b7.E(), b7.O(), b());
        byte[] bArr = new byte[0];
        if (checkAuth != null) {
            str = checkAuth.charset;
            byte[] bArr2 = checkAuth.data;
            if (bArr2 != null) {
                bArr = bArr2;
            }
        } else {
            str = "utf-8";
        }
        b7.E = Calendar.getInstance().get(1);
        b7.F = Calendar.getInstance().get(2);
        b7.G = Calendar.getInstance().get(5);
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr, str));
            a(jSONObject);
            b(jSONObject);
            return b7.D == 0;
        } catch (Exception unused) {
            return false;
        }
    }
}
