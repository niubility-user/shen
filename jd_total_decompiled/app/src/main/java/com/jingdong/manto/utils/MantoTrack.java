package com.jingdong.manto.utils;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.manto.sdk.api.ITrackReport;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class MantoTrack {
    private static ITrackReport a = (ITrackReport) com.jingdong.a.n(ITrackReport.class);
    private static final b b = b.h();

    /* loaded from: classes16.dex */
    public static class b extends com.jingdong.manto.message.c {
        public static final Parcelable.Creator<b> CREATOR = new a();

        /* renamed from: f */
        private static volatile b f14296f;

        /* renamed from: c */
        public int f14297c;
        public Map d;

        /* renamed from: e */
        public Map f14298e;

        /* loaded from: classes16.dex */
        class a implements Parcelable.Creator<b> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public b createFromParcel(Parcel parcel) {
                b bVar = new b();
                bVar.a(parcel);
                return bVar;
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public b[] newArray(int i2) {
                return new b[i2];
            }
        }

        private b() {
            this.f14297c = 0;
        }

        static /* synthetic */ b h() {
            return i();
        }

        private static b i() {
            if (f14296f == null) {
                synchronized (b.class) {
                    if (f14296f == null) {
                        f14296f = new b();
                    }
                }
            }
            return f14296f;
        }

        @Override // com.jingdong.manto.message.c
        public void a(Parcel parcel) {
            this.f14297c = parcel.readInt();
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            parcel.readMap(hashMap, b.class.getClassLoader());
            parcel.readMap(hashMap2, b.class.getClassLoader());
            this.d = hashMap;
            this.f14298e = hashMap2;
        }

        @Override // com.jingdong.manto.message.c
        public void b() {
            ITrackReport iTrackReport = (ITrackReport) com.jingdong.a.n(ITrackReport.class);
            if (iTrackReport == null) {
                MantoLog.d("trace", "ITrackReport not impl.");
                return;
            }
            int i2 = this.f14297c;
            if (i2 == 1) {
                iTrackReport.sendClickData(MantoProcessUtil.getContext(), this.d, this.f14298e);
            } else if (i2 != 2) {
                iTrackReport.sendPagePv(MantoProcessUtil.getContext(), this.d, this.f14298e);
            } else {
                iTrackReport.sendExposureData(MantoProcessUtil.getContext(), this.d, this.f14298e);
            }
        }

        @Override // com.jingdong.manto.message.c, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.jingdong.manto.message.c, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f14297c);
            parcel.writeMap(this.d);
            parcel.writeMap(this.f14298e);
        }
    }

    public static void sendCommonDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, HashMap<String, String> hashMap) {
        if (a == null) {
            a = (ITrackReport) com.jingdong.a.n(ITrackReport.class);
        }
        if (a != null) {
            HashMap hashMap2 = new HashMap(4);
            hashMap2.put("eventId", str2);
            hashMap2.put("eventName", str);
            hashMap2.put("eventParam", str3);
            hashMap2.put("pageID", str7);
            hashMap2.put("page_name", str4);
            hashMap2.put("pageParam", str5);
            try {
                JSONObject jSONObject = new JSONObject(str6);
                jSONObject.put("host_id", com.jingdong.a.b);
                str6 = jSONObject.toString();
            } catch (Exception unused) {
            }
            hashMap2.put("jsonParam", str6);
            if (MantoProcessUtil.isMainProcess()) {
                a.sendClickData(context, hashMap2, hashMap);
                return;
            }
            b bVar = b;
            bVar.f14297c = 1;
            bVar.d = hashMap2;
            bVar.f14298e = hashMap;
            bVar.e();
        }
    }

    public static void sendCommonDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, HashMap<String, String> hashMap, String str8, String str9) {
        if (a == null) {
            a = (ITrackReport) com.jingdong.a.n(ITrackReport.class);
        }
        if (a != null) {
            HashMap hashMap2 = new HashMap(4);
            hashMap2.put("eventId", str2);
            hashMap2.put("eventName", str);
            hashMap2.put("eventParam", str3);
            hashMap2.put("pageID", str7);
            hashMap2.put("page_name", str4);
            hashMap2.put("pageParam", str5);
            hashMap2.put("page_no", str8);
            hashMap2.put("order_lvl", str9);
            try {
                JSONObject jSONObject = new JSONObject(str6);
                jSONObject.put("host_id", com.jingdong.a.b);
                str6 = jSONObject.toString();
            } catch (Exception unused) {
            }
            hashMap2.put("jsonParam", str6);
            if (MantoProcessUtil.isMainProcess()) {
                a.sendClickData(context, hashMap2, hashMap);
                return;
            }
            b bVar = b;
            bVar.f14297c = 1;
            bVar.d = hashMap2;
            bVar.f14298e = hashMap;
            bVar.e();
        }
    }

    public static void sendExposureData(Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap) {
        if (a == null) {
            a = (ITrackReport) com.jingdong.a.n(ITrackReport.class);
        }
        if (a != null) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("eid", str);
            hashMap2.put("ela", str2);
            hashMap2.put("ctp", str3);
            hashMap2.put("par", str4);
            hashMap2.put("page_id", str6);
            try {
                JSONObject jSONObject = new JSONObject(str5);
                jSONObject.put("host_id", com.jingdong.a.b);
                str5 = jSONObject.toString();
            } catch (Exception unused) {
            }
            hashMap2.put("jsonParam", str5);
            if (MantoProcessUtil.isMainProcess()) {
                a.sendExposureData(context, hashMap2, hashMap);
                return;
            }
            b bVar = b;
            bVar.f14297c = 2;
            bVar.d = hashMap2;
            bVar.f14298e = hashMap;
            bVar.e();
        }
    }

    public static void sendPagePv(Context context, String str, String str2, String str3, HashMap<String, String> hashMap) {
        if (a == null) {
            a = (ITrackReport) com.jingdong.a.n(ITrackReport.class);
        }
        if (a != null) {
            HashMap hashMap2 = new HashMap(4);
            try {
                JSONObject jSONObject = new JSONObject(str2);
                jSONObject.put("host_id", com.jingdong.a.b);
                str2 = jSONObject.toString();
            } catch (Exception unused) {
            }
            hashMap2.put("page_id", str3);
            hashMap2.put("page_name", str);
            hashMap2.put("page_param", str2);
            if (MantoProcessUtil.isMainProcess()) {
                a.sendPagePv(context, hashMap2, hashMap);
                return;
            }
            b bVar = b;
            bVar.f14297c = 0;
            bVar.d = hashMap2;
            bVar.f14298e = hashMap;
            bVar.e();
        }
    }
}
