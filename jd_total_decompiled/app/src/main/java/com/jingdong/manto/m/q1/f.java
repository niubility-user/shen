package com.jingdong.manto.m.q1;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.sdk.api.IBizDaojia;
import com.jingdong.manto.sdk.api.ITrackReport;
import com.jingdong.manto.utils.MantoLog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class f extends com.jingdong.manto.message.c {
    public static final Parcelable.Creator<f> CREATOR = new a();

    /* renamed from: c  reason: collision with root package name */
    public int f13576c = 1;
    public e0 d;

    /* renamed from: e  reason: collision with root package name */
    public d0 f13577e;

    /* renamed from: f  reason: collision with root package name */
    public int f13578f;

    /* renamed from: g  reason: collision with root package name */
    public String f13579g;

    /* renamed from: h  reason: collision with root package name */
    public String f13580h;

    /* renamed from: i  reason: collision with root package name */
    public String f13581i;

    /* renamed from: j  reason: collision with root package name */
    public String f13582j;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<f> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final f createFromParcel(Parcel parcel) {
            return new f(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final f[] newArray(int i2) {
            return new f[i2];
        }
    }

    public f() {
    }

    public f(Parcel parcel) {
        a(parcel);
    }

    private void a(JSONObject jSONObject, Map map) {
        JSONArray optJSONArray = jSONObject.optJSONArray("savePageInfoWithSKU");
        IBizDaojia iBizDaojia = (IBizDaojia) com.jingdong.a.n(IBizDaojia.class);
        if (iBizDaojia != null) {
            iBizDaojia.sendOrderData(com.jingdong.a.g(), optJSONArray, map);
            return;
        }
        this.f13580h = "fail";
        this.f13581i = "IBizDaojia not registered!";
    }

    @Override // com.jingdong.manto.message.c
    public void a(Parcel parcel) {
        this.f13580h = parcel.readString();
        this.f13578f = parcel.readInt();
        this.f13576c = parcel.readInt();
        this.f13581i = parcel.readString();
        this.f13579g = parcel.readString();
        this.f13582j = parcel.readString();
    }

    @Override // com.jingdong.manto.message.c
    public void b() {
        ITrackReport iTrackReport = (ITrackReport) com.jingdong.a.n(ITrackReport.class);
        if (iTrackReport == null) {
            this.f13580h = "fail";
            this.f13581i = "ITrackReport not registered!";
            g();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.f13579g);
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.optString(next));
            }
            try {
                String optString = jSONObject.optString("map");
                if (optString != null) {
                    JSONObject jSONObject2 = new JSONObject(optString);
                    Iterator<String> keys2 = jSONObject2.keys();
                    while (keys2.hasNext()) {
                        String next2 = keys2.next();
                        hashMap2.put(next2, jSONObject2.optString(next2));
                    }
                }
            } catch (Exception unused) {
            }
            this.f13580h = IMantoBaseModule.SUCCESS;
            int i2 = this.f13576c;
            if (i2 != 1) {
                if (i2 == 2) {
                    iTrackReport.sendClickData(com.jingdong.a.g(), hashMap, hashMap2);
                } else if (i2 == 3) {
                    iTrackReport.sendExposureData(com.jingdong.a.g(), hashMap, hashMap2);
                } else if (i2 == 4) {
                    a(jSONObject, hashMap2);
                } else if (i2 == 5) {
                    iTrackReport.sendJDOrderInfo(com.jingdong.a.g(), hashMap, hashMap2);
                }
                g();
            }
            iTrackReport.sendPagePv(com.jingdong.a.g(), hashMap, hashMap2);
            g();
        } catch (Exception e2) {
            this.f13580h = "fail";
            this.f13581i = "parse json error: " + e2;
            g();
        }
    }

    @Override // com.jingdong.manto.message.c
    public void c() {
        super.c();
        a();
        e0 e0Var = this.d;
        if (e0Var == null || !e0Var.f()) {
            return;
        }
        MantoLog.d("trackReport", "" + this.f13580h);
        if (IMantoBaseModule.SUCCESS.equals(this.f13580h)) {
            this.d.a(this.f13578f, this.f13577e.putErrMsg(IMantoBaseModule.SUCCESS, null, this.f13582j));
            return;
        }
        this.d.a(this.f13578f, this.f13577e.putErrMsg("fail:" + this.f13581i, null, this.f13582j));
    }

    @Override // com.jingdong.manto.message.c, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f13580h);
        parcel.writeInt(this.f13578f);
        parcel.writeInt(this.f13576c);
        parcel.writeString(this.f13581i);
        parcel.writeString(this.f13579g);
        parcel.writeString(this.f13582j);
    }
}
