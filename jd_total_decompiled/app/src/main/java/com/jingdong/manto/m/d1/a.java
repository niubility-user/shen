package com.jingdong.manto.m.d1;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.jsapi.openmodule.OpenJsApiManager;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.m.k0;
import com.jingdong.manto.o.n;
import com.jingdong.manto.utils.MantoUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends c {

    /* renamed from: com.jingdong.manto.m.d1.a$a */
    /* loaded from: classes15.dex */
    public static class C0554a extends com.jingdong.manto.message.c {
        public static final Parcelable.Creator<C0554a> CREATOR = new C0555a();

        /* renamed from: c */
        public e0 f13316c;
        public com.jingdong.manto.jsapi.base.e d;

        /* renamed from: e */
        public String f13317e;

        /* renamed from: f */
        public Bundle f13318f;

        /* renamed from: g */
        public String f13319g;

        /* renamed from: h */
        public Bundle f13320h;

        /* renamed from: i */
        public String f13321i;

        /* renamed from: j */
        public int f13322j;

        /* renamed from: k */
        public String f13323k;

        /* renamed from: l */
        public String f13324l;

        /* renamed from: m */
        public String f13325m;

        /* renamed from: n */
        public int f13326n;
        public String o;

        /* renamed from: com.jingdong.manto.m.d1.a$a$a */
        /* loaded from: classes15.dex */
        class C0555a implements Parcelable.Creator<C0554a> {
            C0555a() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public final C0554a createFromParcel(Parcel parcel) {
                return new C0554a(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public final C0554a[] newArray(int i2) {
                return new C0554a[i2];
            }
        }

        /* renamed from: com.jingdong.manto.m.d1.a$a$b */
        /* loaded from: classes15.dex */
        class b implements MantoResultCallBack {
            b() {
                C0554a.this = r1;
            }

            @Override // com.jingdong.manto.jsapi.openmodule.MantoResultCallBack
            public void onCancel(Bundle bundle) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putString(IMantoBaseModule.ERROR_CODE, "-1");
                C0554a.this.a(bundle);
            }

            @Override // com.jingdong.manto.jsapi.openmodule.MantoResultCallBack
            public void onFailed(Bundle bundle) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
                C0554a.this.a(bundle);
            }

            @Override // com.jingdong.manto.jsapi.openmodule.MantoResultCallBack
            public void onSuccess(Bundle bundle) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putString(IMantoBaseModule.ERROR_CODE, "1");
                C0554a.this.a(bundle);
            }
        }

        public C0554a() {
        }

        public C0554a(Parcel parcel) {
            a(parcel);
        }

        private final String a(String str, Map<String, ? extends Object> map) {
            HashMap hashMap = new HashMap();
            hashMap.put("errMsg", this.f13324l + ":" + str);
            if (map != null) {
                if (map.containsKey("errMsg") && com.jingdong.a.a) {
                    throw new RuntimeException("api " + this.f13324l + ": Cant put errMsg in res!!!");
                }
                hashMap.putAll(map);
            }
            MantoUtils.mapToJson(hashMap);
            return new JSONObject(hashMap).toString();
        }

        public void a(Bundle bundle) {
            String str = "fail";
            if (bundle == null) {
                this.f13317e = "fail";
                this.f13321i = DYConstants.DY_NULL_STR;
                g();
                return;
            }
            String string = bundle.getString(IMantoBaseModule.ERROR_CODE, "1");
            bundle.remove(IMantoBaseModule.ERROR_CODE);
            this.f13318f = bundle;
            if ("1".equals(string)) {
                this.f13317e = IMantoBaseModule.SUCCESS;
                this.o = "0";
            } else {
                if (!"0".equals(string)) {
                    if ("-1".equals(string)) {
                        this.f13317e = "cancel";
                    } else {
                        str = bundle.getString("result", "fail");
                    }
                }
                this.f13317e = str;
                this.f13321i = bundle.getString("message", "error");
                this.o = bundle.getString("errCode", "-1");
            }
            g();
        }

        private final void a(Bundle bundle, MantoResultCallBack mantoResultCallBack) {
            Bundle bundle2;
            int i2 = this.f13326n;
            Map<String, IMantoBaseModule> sApiMap = i2 == 0 ? OpenJsApiManager.getSApiMap() : i2 == 1 ? OpenJsApiManager.getPApiMap() : i2 == 2 ? OpenJsApiManager.getWApiMap() : null;
            if (sApiMap == null) {
                bundle2 = new Bundle();
            } else if (sApiMap.containsKey(this.f13323k)) {
                sApiMap.get(this.f13323k).handleMethod(this.f13325m, null, bundle, mantoResultCallBack);
                return;
            } else {
                bundle2 = new Bundle();
            }
            mantoResultCallBack.onFailed(bundle2);
        }

        @Override // com.jingdong.manto.message.c
        public final void a(Parcel parcel) {
            this.f13317e = parcel.readString();
            this.f13322j = parcel.readInt();
            this.f13319g = parcel.readString();
            this.f13318f = parcel.readBundle();
            this.f13321i = parcel.readString();
            this.f13320h = parcel.readBundle();
            this.f13324l = parcel.readString();
            this.f13326n = parcel.readInt();
            this.f13323k = parcel.readString();
            this.f13325m = parcel.readString();
            this.o = parcel.readString();
        }

        @Override // com.jingdong.manto.message.c
        public void b() {
            a(this.f13320h, new b());
        }

        /* JADX WARN: Removed duplicated region for block: B:116:0x00cb  */
        /* JADX WARN: Removed duplicated region for block: B:120:? A[RETURN, SYNTHETIC] */
        @Override // com.jingdong.manto.message.c
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void c() {
            e0 e0Var;
            int i2;
            a();
            e0 e0Var2 = this.f13316c;
            if (e0Var2 == null || !e0Var2.f()) {
                return;
            }
            String str = null;
            Bundle bundle = this.f13318f;
            if (bundle != null) {
                if (bundle.containsKey(IMantoBaseModule.UPDATE_LOGIN_STATUS_KEY)) {
                    this.f13316c.h().z = Boolean.valueOf(this.f13318f.getBoolean(IMantoBaseModule.UPDATE_LOGIN_STATUS_KEY, false)).booleanValue();
                }
                str = this.f13318f.getString(IMantoBaseModule.REQUEST_JSAPI_KEY, "");
            }
            if (!TextUtils.isEmpty(str)) {
                k0 d = this.f13316c.d();
                Map formatBundle = MantoUtils.formatBundle(this.f13318f);
                MantoUtils.mapToJson(formatBundle);
                d.a(str, new JSONObject(formatBundle).toString(), this.f13322j, this.f13324l);
                return;
            }
            if (this.f13318f.containsKey(IMantoBaseModule.UPDATE_LOGIN_STATUS_KEY)) {
                this.f13318f.remove(IMantoBaseModule.UPDATE_LOGIN_STATUS_KEY);
            }
            Map<String, ? extends Object> formatBundle2 = MantoUtils.formatBundle(this.f13318f);
            if (formatBundle2 == null) {
                formatBundle2 = new HashMap<>(1);
            }
            String str2 = this.f13317e;
            String str3 = IMantoBaseModule.SUCCESS;
            if (!IMantoBaseModule.SUCCESS.equals(str2)) {
                if ("fail".equals(this.f13317e)) {
                    e0Var = this.f13316c;
                    i2 = this.f13322j;
                    str3 = "fail:" + this.f13321i;
                    e0Var.a(i2, a(str3, formatBundle2));
                    if (this.d != null) {
                        c.a(c0.getPageView(this.f13316c), this.d);
                        return;
                    }
                    return;
                }
                str3 = "cancel";
                if (!"cancel".equals(this.f13317e)) {
                    this.f13316c.a(this.f13322j, a("" + this.f13317e + ":" + this.f13321i, formatBundle2));
                    if (this.d != null) {
                    }
                }
            }
            e0Var = this.f13316c;
            i2 = this.f13322j;
            e0Var.a(i2, a(str3, formatBundle2));
            if (this.d != null) {
            }
        }

        @Override // com.jingdong.manto.message.c, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.f13317e);
            parcel.writeInt(this.f13322j);
            parcel.writeString(this.f13319g);
            parcel.writeBundle(this.f13318f);
            parcel.writeString(this.f13321i);
            parcel.writeBundle(this.f13320h);
            parcel.writeString(this.f13324l);
            parcel.writeInt(this.f13326n);
            parcel.writeString(this.f13323k);
            parcel.writeString(this.f13325m);
            parcel.writeString(this.o);
        }
    }

    public a(i iVar) {
        super(iVar);
    }

    private Bundle a(MantoCore mantoCore, JSONObject jSONObject) {
        return this.a.c().initData(this.a.a(), mantoCore, jSONObject);
    }

    @Override // com.jingdong.manto.m.d1.c
    protected void a(e0 e0Var, JSONObject jSONObject, int i2, int i3, String str) {
        n nVar;
        MantoCore core = getCore(e0Var);
        com.jingdong.manto.jsapi.base.e eVar = null;
        if (core == null) {
            e0Var.a(i2, putErrMsg("fail", null, str));
            return;
        }
        Bundle a = a(core, jSONObject);
        if (a == null) {
            a = new Bundle();
        }
        a.putString("appid", e0Var.a());
        if (e0Var.h().f13016h != null) {
            a.putString("type", e0Var.h().f13016h.type);
        }
        if (a.getBoolean(IMantoBaseModule.ADD_EXTRAS_DATA)) {
            a.putString(IMantoBaseModule.EXTRAS_DATA, e0Var.h().r.f13090m);
        }
        if (e0Var.h().r != null && !TextUtils.isEmpty(e0Var.h().r.p)) {
            a.putString(IMantoBaseModule.ACTION_ID, e0Var.h().r.p);
        }
        a.putBoolean(IMantoBaseModule.CARD_MODE, e0Var.h().u());
        a.putString(IMantoBaseModule.APP_UNIQUEID_ID_KEY, e0Var.c());
        com.jingdong.manto.q.n pageView = c0.getPageView(e0Var);
        a.putBoolean(IMantoBaseModule.SHARE_SUPPORT_KEY, (pageView == null || (nVar = pageView.p().get(5)) == null) ? false : nVar.a.a("user_clicked_share_btn", true));
        a.putInt(IMantoBaseModule.COMPONENT_HASHCODE, e0Var.hashCode());
        MantoLifecycleLisener addLifecycleLisener = this.a.c().addLifecycleLisener(getJsApiName(), a);
        if (addLifecycleLisener != null && pageView != null) {
            eVar = c.a(pageView, addLifecycleLisener);
        }
        C0554a c0554a = new C0554a();
        c0554a.f13319g = e0Var.a();
        c0554a.f13316c = e0Var;
        c0554a.f13322j = i2;
        c0554a.f13320h = a;
        c0554a.f13326n = i3;
        c0554a.f13325m = getJsApiName();
        c0554a.d = eVar;
        c0554a.f13324l = str;
        c0554a.f13323k = this.a.c().getModuleName();
        c0554a.d();
    }
}
