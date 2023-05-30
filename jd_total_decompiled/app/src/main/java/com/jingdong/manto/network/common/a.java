package com.jingdong.manto.network.common;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.manto.utils.MantoLog;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class a implements IMantoServerRequester {
    private static IMantoServerRequester a = new a();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.network.common.a$a  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public class C0642a implements Callback {
        final /* synthetic */ IMantoServerRequester.CallBack a;

        C0642a(a aVar, IMantoServerRequester.CallBack callBack) {
            this.a = callBack;
        }

        @Override // okhttp3.Callback
        public void onFailure(Call call, IOException iOException) {
            IMantoServerRequester.CallBack callBack = this.a;
            if (callBack != null) {
                callBack.onError(iOException);
            }
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, Response response) {
            if (response == null || !response.isSuccessful() || this.a == null) {
                return;
            }
            try {
                String string = response.body().string();
                MantoLog.d("DefaultServerRequester", string);
                JSONObject jSONObject = new JSONObject(string);
                JSONObject optJSONObject = jSONObject.optJSONObject("error");
                if (optJSONObject != null) {
                    this.a.onError(new IllegalStateException(optJSONObject.toString()));
                } else {
                    this.a.onSuccess(jSONObject);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements Callback {
        final /* synthetic */ IMantoServerRequester.CallBack a;

        b(a aVar, IMantoServerRequester.CallBack callBack) {
            this.a = callBack;
        }

        @Override // okhttp3.Callback
        public void onFailure(Call call, IOException iOException) {
            IMantoServerRequester.CallBack callBack = this.a;
            if (callBack != null) {
                callBack.onError(iOException);
            }
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, Response response) {
            if (response == null || !response.isSuccessful() || this.a == null) {
                return;
            }
            try {
                String string = response.body().string();
                MantoLog.d("DefaultServerRequester", string);
                JSONObject jSONObject = new JSONObject(string);
                JSONObject optJSONObject = jSONObject.optJSONObject("error");
                if (optJSONObject != null) {
                    this.a.onError(new IllegalStateException(optJSONObject.toString()));
                } else {
                    this.a.onSuccess(jSONObject);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static IMantoServerRequester a() {
        return a;
    }

    private void a(boolean z, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, String str3, IMantoServerRequester.CallBack callBack) {
        String str4;
        StringBuilder sb = new StringBuilder(str);
        if (z) {
            sb.append("?");
            if (!TextUtils.isEmpty(str2)) {
                sb.append("functionId=");
                sb.append(str2);
            }
        } else {
            if (!TextUtils.isEmpty(str2)) {
                sb.append("/");
                sb.append(str2);
            }
            sb.append("?");
        }
        if (jSONObject != null) {
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    String optString = jSONObject.optString(next);
                    String encode = URLEncoder.encode(next);
                    String encode2 = URLEncoder.encode(optString);
                    sb.append(ContainerUtils.FIELD_DELIMITER);
                    sb.append(encode);
                    sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                    sb.append(encode2);
                }
            } catch (Exception unused) {
            }
        }
        if (jSONObject2 != null) {
            str4 = jSONObject2.toString();
            sb.append("&body=");
            sb.append(str4);
        } else {
            str4 = "";
        }
        sb.append("&sign=");
        sb.append(d.a(z, str2, jSONObject, str4));
        com.jingdong.manto.p.a.b().a(30000).newCall(new Request.Builder().addHeader("Cookie", str3).url(sb.toString()).get().build()).enqueue(new b(this, callBack));
    }

    private void b(boolean z, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, String str3, IMantoServerRequester.CallBack callBack) {
        StringBuilder sb = new StringBuilder(str);
        if (z) {
            sb.append("?");
            if (!TextUtils.isEmpty(str2)) {
                sb.append("functionId=");
                sb.append(str2);
            }
        } else {
            if (!TextUtils.isEmpty(str2)) {
                sb.append("/");
                sb.append(str2);
            }
            sb.append("?");
        }
        if (jSONObject != null) {
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    String optString = jSONObject.optString(next);
                    String encode = URLEncoder.encode(next);
                    String encode2 = URLEncoder.encode(optString);
                    sb.append(ContainerUtils.FIELD_DELIMITER);
                    sb.append(encode);
                    sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                    sb.append(encode2);
                }
            } catch (Exception unused) {
            }
        }
        String jSONObject3 = jSONObject2 != null ? jSONObject2.toString() : "";
        sb.append("&sign=");
        sb.append(d.a(z, str2, jSONObject, jSONObject3));
        com.jingdong.manto.p.a.b().a(30000).newCall(new Request.Builder().addHeader("Cookie", str3).url(sb.toString()).post(new FormBody.Builder().add("body", jSONObject3).build()).build()).enqueue(new C0642a(this, callBack));
    }

    @Override // com.jingdong.manto.sdk.api.IMantoServerRequester
    public void request(boolean z, String str, String str2, String str3, JSONObject jSONObject, JSONObject jSONObject2, String str4, IMantoServerRequester.CallBack callBack) {
        if (TextUtils.equals(IMantoServerRequester.GET, str)) {
            a(z, str2, str3, jSONObject, jSONObject2, str4, callBack);
        } else {
            b(z, str2, str3, jSONObject, jSONObject2, str4, callBack);
        }
    }
}
