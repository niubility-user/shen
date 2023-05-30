package com.jingdong.manto.m.b1;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.launch.LaunchParam;
import com.jingdong.manto.launch.f;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.message.MantoAcrossMessage;
import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.MantoThreadUtils;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b extends d0 {

    /* loaded from: classes15.dex */
    public class a implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ int b;

        /* renamed from: c */
        final /* synthetic */ h f13288c;
        final /* synthetic */ int d;

        /* renamed from: e */
        final /* synthetic */ String f13289e;

        /* renamed from: f */
        final /* synthetic */ String f13290f;

        /* renamed from: g */
        final /* synthetic */ String f13291g;

        /* renamed from: com.jingdong.manto.m.b1.b$a$a */
        /* loaded from: classes15.dex */
        class C0545a implements MantoAcrossMessage.Listener {
            C0545a() {
                a.this = r1;
            }

            @Override // com.jingdong.manto.message.MantoAcrossMessage.Listener
            public void onCalled(Object obj) {
                if (obj instanceof f) {
                    f fVar = (f) obj;
                    if (a.this.a.equals(fVar.a)) {
                        a aVar = a.this;
                        if (aVar.b == fVar.f13252c) {
                            aVar.f13288c.h().p().b(this);
                            a aVar2 = a.this;
                            aVar2.f13288c.a(aVar2.d, b.this.putErrMsg(fVar.b ? IMantoBaseModule.SUCCESS : "fail", null, aVar2.f13289e));
                        }
                    }
                }
            }
        }

        a(String str, int i2, h hVar, int i3, String str2, String str3, String str4) {
            b.this = r1;
            this.a = str;
            this.b = i2;
            this.f13288c = hVar;
            this.d = i3;
            this.f13289e = str2;
            this.f13290f = str3;
            this.f13291g = str4;
        }

        @Override // java.lang.Runnable
        public void run() {
            C0546b c0546b = new C0546b();
            c0546b.f13293c = this.a;
            c0546b.f13294e = this.b;
            if (!c0546b.f()) {
                this.f13288c.a(this.d, b.this.putErrMsg("fail precondition error", null, this.f13289e));
                return;
            }
            C0546b.EnumC0547b a = C0546b.EnumC0547b.a(c0546b.f13294e);
            if (a == null) {
                a = C0546b.EnumC0547b.FAIL;
            }
            if (C0546b.EnumC0547b.OK != a) {
                this.f13288c.a(this.d, b.this.putErrMsg(a.a, null, this.f13289e));
                return;
            }
            this.f13288c.h().p().a((MantoAcrossMessage.Listener) new C0545a());
            if (this.f13288c.p() == null || !(this.f13288c.p() instanceof Activity) || this.f13288c.p().isFinishing()) {
                return;
            }
            n pageView = c0.getPageView(this.f13288c);
            String r = pageView != null ? pageView.r() : "";
            com.jingdong.manto.i.d dVar = new com.jingdong.manto.i.d();
            dVar.a = this.f13288c.a();
            dVar.f13093e = this.f13290f;
            dVar.b = 1;
            dVar.f13092c = r;
            LaunchParam launchParam = new LaunchParam();
            launchParam.sourcePath = null;
            launchParam.appId = this.a;
            launchParam.launchPath = this.f13291g;
            launchParam.debugType = String.valueOf(this.b);
            launchParam.version = -1;
            launchParam.launchReferrer = dVar;
            com.jingdong.a.o(launchParam);
        }
    }

    /* renamed from: com.jingdong.manto.m.b1.b$b */
    /* loaded from: classes15.dex */
    public static final class C0546b extends com.jingdong.manto.message.c {
        public static final Parcelable.Creator<C0546b> CREATOR = new a();

        /* renamed from: c */
        private String f13293c;
        private int d;

        /* renamed from: e */
        private int f13294e = EnumC0547b.FAIL.ordinal();

        /* renamed from: com.jingdong.manto.m.b1.b$b$a */
        /* loaded from: classes15.dex */
        class a implements Parcelable.Creator<C0546b> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public C0546b createFromParcel(Parcel parcel) {
                return new C0546b(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public C0546b[] newArray(int i2) {
                return new C0546b[i2];
            }
        }

        /* renamed from: com.jingdong.manto.m.b1.b$b$b */
        /* loaded from: classes15.dex */
        public enum EnumC0547b {
            FAIL("fail"),
            FAIL_MORE_THAN_ONE_TASK("fail can not launch more than 1 mini program"),
            OK(IMantoBaseModule.SUCCESS);
            
            public final String a;

            EnumC0547b(String str) {
                this.a = str;
            }

            public static EnumC0547b a(int i2) {
                for (EnumC0547b enumC0547b : values()) {
                    if (i2 == enumC0547b.ordinal()) {
                        return enumC0547b;
                    }
                }
                return null;
            }
        }

        C0546b() {
        }

        C0546b(Parcel parcel) {
            a(parcel);
        }

        @Override // com.jingdong.manto.message.c
        public final void a(Parcel parcel) {
            this.f13293c = parcel.readString();
            this.d = parcel.readInt();
            this.f13294e = parcel.readInt();
        }

        @Override // com.jingdong.manto.message.c
        public final void b() {
            this.f13294e = EnumC0547b.OK.ordinal();
        }

        @Override // com.jingdong.manto.message.c, android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // com.jingdong.manto.message.c, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.f13293c);
            parcel.writeInt(this.d);
            parcel.writeInt(this.f13294e);
        }
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        String str2;
        String optString = jSONObject.optString("appId", null);
        if (TextUtils.isEmpty(optString)) {
            str2 = "fail:invalid data";
        } else if (!optString.equals(hVar.a())) {
            String optString2 = jSONObject.optString("path", null);
            MantoThreadUtils.runOnUIThread(new a(optString, (TextUtils.equals(hVar.h().r.f13082e, "2") || jSONObject.optBoolean("isDev", false)) ? 2 : 1, hVar, i2, str, jSONObject.optString("extraData", null), optString2));
            return;
        } else {
            str2 = "fail target appId is the same";
        }
        hVar.a(i2, putErrMsg(str2, null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "launchMiniProgram";
    }
}
