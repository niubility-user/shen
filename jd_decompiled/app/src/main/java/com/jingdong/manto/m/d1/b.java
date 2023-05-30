package com.jingdong.manto.m.d1;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener;
import com.jingdong.manto.jsapi.openmodule.OpenJsApiManager;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.o.n;
import com.jingdong.manto.utils.MantoUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b extends d {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes15.dex */
    public static class a extends com.jingdong.manto.message.c {
        public static final Parcelable.Creator<a> CREATOR = new C0556a();

        /* renamed from: c  reason: collision with root package name */
        public String f13327c;
        public Bundle d;

        /* renamed from: e  reason: collision with root package name */
        public String f13328e;

        /* renamed from: f  reason: collision with root package name */
        public Bundle f13329f;

        /* renamed from: g  reason: collision with root package name */
        public String f13330g;

        /* renamed from: h  reason: collision with root package name */
        public String f13331h;

        /* renamed from: i  reason: collision with root package name */
        public String f13332i;

        /* renamed from: j  reason: collision with root package name */
        public int f13333j;

        /* renamed from: k  reason: collision with root package name */
        public e0 f13334k;

        /* renamed from: l  reason: collision with root package name */
        public com.jingdong.manto.jsapi.base.e f13335l;

        /* renamed from: com.jingdong.manto.m.d1.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class C0556a implements Parcelable.Creator<a> {
            C0556a() {
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

        public a() {
        }

        public a(Parcel parcel) {
            a(parcel);
        }

        private final void a(Bundle bundle) {
            int i2 = this.f13333j;
            Map<String, IMantoBaseModule> sApiMap = i2 == 0 ? OpenJsApiManager.getSApiMap() : i2 == 1 ? OpenJsApiManager.getPApiMap() : i2 == 2 ? OpenJsApiManager.getWApiMap() : null;
            if (sApiMap != null && sApiMap.containsKey(this.f13331h)) {
                sApiMap.get(this.f13331h).handleMethodSync(this.f13332i, null, bundle);
            }
        }

        @Override // com.jingdong.manto.message.c
        public final void a(Parcel parcel) {
            this.f13327c = parcel.readString();
            this.f13328e = parcel.readString();
            this.d = parcel.readBundle();
            this.f13330g = parcel.readString();
            this.f13329f = parcel.readBundle();
            this.f13332i = parcel.readString();
            this.f13333j = parcel.readInt();
            this.f13331h = parcel.readString();
        }

        @Override // com.jingdong.manto.message.c
        public void b() {
            a(this.f13329f);
        }

        @Override // com.jingdong.manto.message.c
        public final void c() {
            if (this.f13335l != null) {
                d.a(c0.getPageView(this.f13334k), this.f13335l);
            }
        }

        @Override // com.jingdong.manto.message.c, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.f13327c);
            parcel.writeString(this.f13328e);
            parcel.writeBundle(this.d);
            parcel.writeString(this.f13330g);
            parcel.writeBundle(this.f13329f);
            parcel.writeString(this.f13332i);
            parcel.writeInt(this.f13333j);
            parcel.writeString(this.f13331h);
        }
    }

    public b(i iVar) {
        super(iVar);
    }

    public final String a(e0 e0Var, Bundle bundle, MantoCore mantoCore, int i2) {
        n nVar;
        bundle.putString("appid", e0Var.a());
        if (e0Var.h().f13016h != null) {
            bundle.putString("type", e0Var.h().f13016h.type);
        }
        bundle.putString(IMantoBaseModule.APP_UNIQUEID_ID_KEY, e0Var.c());
        if (bundle.getBoolean(IMantoBaseModule.ADD_EXTRAS_DATA)) {
            bundle.putString(IMantoBaseModule.EXTRAS_DATA, e0Var.h().r.f13090m);
        }
        com.jingdong.manto.q.n pageView = c0.getPageView(e0Var);
        bundle.putBoolean(IMantoBaseModule.SHARE_SUPPORT_KEY, (pageView == null || (nVar = pageView.p().get(5)) == null) ? false : nVar.a.a("user_clicked_share_btn", true));
        bundle.putInt(IMantoBaseModule.COMPONENT_HASHCODE, e0Var.hashCode());
        bundle.putBoolean(IMantoBaseModule.CARD_MODE, e0Var.h().u());
        MantoLifecycleLisener addLifecycleLisener = this.b.c().addLifecycleLisener(getJsApiName(), bundle);
        com.jingdong.manto.jsapi.base.e a2 = (addLifecycleLisener == null || pageView == null) ? null : d.a(pageView, addLifecycleLisener);
        a aVar = new a();
        aVar.f13328e = e0Var.a();
        aVar.f13329f = bundle;
        aVar.f13333j = i2;
        aVar.f13332i = getJsApiName();
        aVar.f13335l = a2;
        aVar.f13334k = e0Var;
        aVar.f13331h = this.b.c().getModuleName();
        aVar.f();
        Bundle bundle2 = aVar.f13329f;
        String string = bundle2 != null ? bundle2.getString("message", "error") : "";
        Bundle bundle3 = aVar.f13329f;
        if (bundle3 == null || bundle3.getBundle(IMantoBaseModule.BUNDLE_REAL_RESULT) == null) {
            return putErrMsg("fail:" + string, null);
        }
        Map<String, ? extends Object> formatBundle = MantoUtils.formatBundle(aVar.f13329f.getBundle(IMantoBaseModule.BUNDLE_REAL_RESULT));
        if (formatBundle == null) {
            formatBundle = new HashMap<>(1);
        }
        String string2 = aVar.f13329f.getString(IMantoBaseModule.ERROR_CODE, "1");
        if ("1".equals(string2)) {
            formatBundle.remove(IMantoBaseModule.ERROR_CODE);
            return putErrMsg(IMantoBaseModule.SUCCESS, formatBundle);
        } else if ("0".equals(string2)) {
            return putErrMsg("fail:" + string, formatBundle);
        } else if ("-1".equals(string2)) {
            return putErrMsg("cancel", formatBundle);
        } else {
            return putErrMsg(aVar.f13329f.getString("result", "fail") + ":" + string, formatBundle);
        }
    }

    @Override // com.jingdong.manto.m.d1.d
    protected String a(e0 e0Var, JSONObject jSONObject, int i2) {
        MantoCore core = getCore(e0Var);
        if (core == null) {
            return putErrMsg("fail", null);
        }
        Bundle initData = jSONObject != null ? this.b.c().initData(this.b.a(), core, jSONObject) : null;
        if (initData == null) {
            initData = new Bundle();
        }
        return a(e0Var, initData, core, 0);
    }
}
