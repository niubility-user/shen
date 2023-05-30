package com.jingdong.manto.m;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.manto.jsapi.auth.tools.AuthInfo;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.manto.utils.MantoLog;
import com.tencent.connect.common.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class j extends d0 {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes15.dex */
    public static final class a extends com.jingdong.manto.message.c {
        public static final Parcelable.Creator<a> CREATOR = new C0572a();

        /* renamed from: c  reason: collision with root package name */
        public com.jingdong.manto.h f13385c;
        private String d;

        /* renamed from: e  reason: collision with root package name */
        public int f13386e;

        /* renamed from: f  reason: collision with root package name */
        public String f13387f;

        /* renamed from: g  reason: collision with root package name */
        public String f13388g;

        /* renamed from: com.jingdong.manto.m.j$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class C0572a implements Parcelable.Creator<a> {
            C0572a() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a  reason: merged with bridge method [inline-methods] */
            public final a createFromParcel(Parcel parcel) {
                return new a(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a  reason: merged with bridge method [inline-methods] */
            public final a[] newArray(int i2) {
                return new a[i2];
            }
        }

        /* loaded from: classes15.dex */
        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                JSONObject jSONObject = new JSONObject();
                try {
                    if (TextUtils.isEmpty(a.this.d)) {
                        a.this.d = "[]";
                    }
                    JSONArray jSONArray = new JSONArray(a.this.d);
                    for (AuthInfo authInfo : com.jingdong.manto.b.k().a(a.this.f13387f)) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put(Constants.PARAM_SCOPE, authInfo.scope);
                        jSONObject2.put(XView2Constants.STATE, authInfo.state.value());
                        jSONObject2.put("desc", authInfo.description);
                        jSONArray.put(jSONObject2);
                    }
                    jSONObject.put("errMsg", a.this.f13388g + ":ok");
                    jSONObject.put("authSetting", jSONArray);
                } catch (JSONException unused) {
                    MantoLog.e("JsApiGetSetting", "set json error!");
                    try {
                        jSONObject.put("errMsg", a.this.f13388g + ":fail");
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                a aVar = a.this;
                aVar.f13385c.a(aVar.f13386e, jSONObject.toString());
            }
        }

        /* loaded from: classes15.dex */
        class c extends IMantoHttpListener {
            c() {
            }

            @Override // com.jingdong.manto.network.common.IMantoHttpListener
            public void onError(JSONObject jSONObject, Throwable th) {
                super.onError(jSONObject, th);
                a.this.g();
            }

            @Override // com.jingdong.manto.network.common.IMantoHttpListener
            public void onSuccess(JSONObject jSONObject) {
                JSONObject optJSONObject;
                JSONArray optJSONArray;
                if (jSONObject != null && jSONObject.optJSONObject("data") != null && (optJSONObject = jSONObject.optJSONObject("data")) != null && (optJSONArray = optJSONObject.optJSONArray(ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID)) != null) {
                    JSONArray jSONArray = new JSONArray();
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        try {
                            JSONObject jSONObject2 = (JSONObject) optJSONArray.opt(i2);
                            if (!"0".equals(jSONObject2.optString(Constants.PARAM_SCOPE))) {
                                JSONObject jSONObject3 = new JSONObject();
                                jSONObject3.put(Constants.PARAM_SCOPE, "scope." + jSONObject2.optString("key"));
                                jSONObject3.put(XView2Constants.STATE, jSONObject2.optInt(Constants.PARAM_SCOPE, 1));
                                jSONObject3.put("desc", jSONObject2.optString("word"));
                                jSONArray.put(jSONObject3);
                            }
                        } catch (Exception e2) {
                            MantoLog.e("JsApiGetSetting", String.format("parse json failed : %s", e2.getMessage()));
                        }
                    }
                    a.this.d = jSONArray.toString();
                }
                a.this.g();
            }
        }

        public a() {
        }

        public a(Parcel parcel) {
            a(parcel);
        }

        @Override // com.jingdong.manto.message.c
        public final void a(Parcel parcel) {
            this.d = parcel.readString();
            this.f13387f = parcel.readString();
            this.f13386e = parcel.readInt();
            this.f13388g = parcel.readString();
        }

        @Override // com.jingdong.manto.message.c
        public final void b() {
            ILogin iLogin = (ILogin) com.jingdong.a.n(ILogin.class);
            if (iLogin == null || !iLogin.hasLogin()) {
                g();
            }
            MantoJDHttpHandler.commit(new com.jingdong.manto.network.mantorequests.b0(this.f13387f), new c());
        }

        @Override // com.jingdong.manto.message.c
        public final void c() {
            a();
            MantoLog.i("JsApiGetSetting", "runInClientProcess");
            com.jingdong.manto.b.d().diskIO().execute(new b());
        }

        @Override // com.jingdong.manto.message.c, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.d);
            parcel.writeString(this.f13387f);
            parcel.writeInt(this.f13386e);
            parcel.writeString(this.f13388g);
        }
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        a aVar = new a();
        aVar.f13387f = hVar.a();
        aVar.f13386e = i2;
        aVar.f13385c = hVar;
        aVar.f13388g = str;
        aVar.d();
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "getSetting";
    }
}
