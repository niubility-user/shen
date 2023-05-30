package com.jingdong.manto.m;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.utils.MantoStringUtils;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class q extends d0 {

    /* loaded from: classes15.dex */
    public static class a extends com.jingdong.manto.message.c {
        public static final Parcelable.Creator<a> CREATOR = new C0604a();

        /* renamed from: c  reason: collision with root package name */
        String f13557c;
        private String d;

        /* renamed from: e  reason: collision with root package name */
        com.jingdong.manto.h f13558e;

        /* renamed from: f  reason: collision with root package name */
        int f13559f;

        /* renamed from: g  reason: collision with root package name */
        private int f13560g;

        /* renamed from: h  reason: collision with root package name */
        c0 f13561h;

        /* renamed from: i  reason: collision with root package name */
        private int f13562i;

        /* renamed from: j  reason: collision with root package name */
        private int f13563j;

        /* renamed from: k  reason: collision with root package name */
        private String f13564k;

        /* renamed from: com.jingdong.manto.m.q$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class C0604a implements Parcelable.Creator<a> {
            C0604a() {
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
        class b extends IMantoHttpListener {
            b() {
            }

            @Override // com.jingdong.manto.network.common.IMantoHttpListener
            public void onError(JSONObject jSONObject, Throwable th) {
                super.onError(jSONObject, th);
                if (jSONObject != null) {
                    try {
                        a.this.f13560g = 2;
                        JSONObject optJSONObject = jSONObject.optJSONObject("errors");
                        a.this.f13563j = Integer.valueOf(optJSONObject.optString("code")).intValue();
                        a.this.g();
                    } catch (Exception unused) {
                        a.this.f13560g = 0;
                        a.this.g();
                    }
                }
            }

            @Override // com.jingdong.manto.network.common.IMantoHttpListener
            public void onSuccess(JSONObject jSONObject) {
                JSONObject optJSONObject;
                String optString = jSONObject.optString("code");
                try {
                    if (MantoStringUtils.isEmpty(optString) || !"0".equals(optString) || (optJSONObject = jSONObject.optJSONObject("data")) == null) {
                        return;
                    }
                    a.this.f13562i = Integer.valueOf(optJSONObject.optString("is_valid")).intValue();
                    a.this.f13560g = 1;
                    a.this.g();
                } catch (Exception unused) {
                    a.this.f13560g = 0;
                    a.this.g();
                }
            }
        }

        public a() {
        }

        public a(Parcel parcel) {
            a(parcel);
        }

        @Override // com.jingdong.manto.message.c
        public final void a(Parcel parcel) {
            this.f13562i = parcel.readInt();
            this.f13563j = parcel.readInt();
            this.f13560g = parcel.readInt();
            this.f13559f = parcel.readInt();
            this.f13557c = parcel.readString();
            this.d = parcel.readString();
            this.f13564k = parcel.readString();
        }

        @Override // com.jingdong.manto.message.c
        public final void b() {
            MantoJDHttpHandler.commit(new com.jingdong.manto.network.mantorequests.h(this.f13557c), new b());
        }

        @Override // com.jingdong.manto.message.c
        public final void c() {
            com.jingdong.manto.h hVar;
            int i2;
            String putErrMsg;
            HashMap hashMap = new HashMap();
            int i3 = this.f13560g;
            if (i3 != 1) {
                hashMap.put("errCode", i3 != 2 ? "-1" : Integer.valueOf(this.f13563j));
                hVar = this.f13558e;
                i2 = this.f13559f;
                putErrMsg = this.f13561h.putErrMsg("fail", hashMap, this.f13564k);
            } else {
                hashMap.put("expireIn", Integer.valueOf(this.f13562i));
                hVar = this.f13558e;
                i2 = this.f13559f;
                putErrMsg = this.f13561h.putErrMsg(IMantoBaseModule.SUCCESS, hashMap, this.f13564k);
            }
            hVar.a(i2, putErrMsg);
            a();
        }

        @Override // com.jingdong.manto.message.c, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f13562i);
            parcel.writeInt(this.f13563j);
            parcel.writeInt(this.f13560g);
            parcel.writeInt(this.f13559f);
            parcel.writeString(this.f13557c);
            parcel.writeString(this.d);
            parcel.writeString(this.f13564k);
        }
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        a aVar = new a();
        com.jingdong.manto.i.c cVar = hVar.h().r;
        if (cVar != null) {
            aVar.d = cVar.f13082e;
        }
        aVar.f13561h = this;
        aVar.f13558e = hVar;
        aVar.f13559f = i2;
        aVar.f13557c = hVar.a();
        aVar.f13564k = str;
        aVar.d();
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "refreshSession";
    }
}
