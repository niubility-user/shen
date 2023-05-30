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

        /* JADX WARN: Removed duplicated region for block: B:75:0x00cb  */
        /* JADX WARN: Removed duplicated region for block: B:79:? A[RETURN, SYNTHETIC] */
        @Override // com.jingdong.manto.message.c
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void c() {
            /*
                r5 = this;
                r5.a()
                com.jingdong.manto.m.e0 r0 = r5.f13316c
                if (r0 == 0) goto Lf6
                boolean r0 = r0.f()
                if (r0 != 0) goto Lf
                goto Lf6
            Lf:
                r0 = 0
                android.os.Bundle r1 = r5.f13318f
                java.lang.String r2 = ""
                java.lang.String r3 = "updateLoginStatus"
                if (r1 == 0) goto L3d
                boolean r0 = r1.containsKey(r3)
                if (r0 == 0) goto L35
                android.os.Bundle r0 = r5.f13318f
                r1 = 0
                boolean r0 = r0.getBoolean(r3, r1)
                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                com.jingdong.manto.m.e0 r1 = r5.f13316c
                com.jingdong.manto.f r1 = r1.h()
                boolean r0 = r0.booleanValue()
                r1.z = r0
            L35:
                android.os.Bundle r0 = r5.f13318f
                java.lang.String r1 = "requestJSApi"
                java.lang.String r0 = r0.getString(r1, r2)
            L3d:
                boolean r1 = android.text.TextUtils.isEmpty(r0)
                if (r1 == 0) goto Ld7
                android.os.Bundle r0 = r5.f13318f
                boolean r0 = r0.containsKey(r3)
                if (r0 == 0) goto L50
                android.os.Bundle r0 = r5.f13318f
                r0.remove(r3)
            L50:
                android.os.Bundle r0 = r5.f13318f
                java.util.Map r0 = com.jingdong.manto.utils.MantoUtils.formatBundle(r0)
                if (r0 != 0) goto L5e
                java.util.HashMap r0 = new java.util.HashMap
                r1 = 1
                r0.<init>(r1)
            L5e:
                java.lang.String r1 = r5.f13317e
                java.lang.String r3 = "ok"
                boolean r1 = r3.equals(r1)
                if (r1 == 0) goto L74
            L68:
                com.jingdong.manto.m.e0 r1 = r5.f13316c
                int r2 = r5.f13322j
            L6c:
                java.lang.String r0 = r5.a(r3, r0)
                r1.a(r2, r0)
                goto Lc7
            L74:
                java.lang.String r1 = r5.f13317e
                java.lang.String r3 = "fail"
                boolean r1 = r3.equals(r1)
                if (r1 == 0) goto L96
                com.jingdong.manto.m.e0 r1 = r5.f13316c
                int r2 = r5.f13322j
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "fail:"
                r3.append(r4)
                java.lang.String r4 = r5.f13321i
                r3.append(r4)
                java.lang.String r3 = r3.toString()
                goto L6c
            L96:
                java.lang.String r1 = r5.f13317e
                java.lang.String r3 = "cancel"
                boolean r1 = r3.equals(r1)
                if (r1 == 0) goto La1
                goto L68
            La1:
                com.jingdong.manto.m.e0 r1 = r5.f13316c
                int r3 = r5.f13322j
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                r4.append(r2)
                java.lang.String r2 = r5.f13317e
                r4.append(r2)
                java.lang.String r2 = ":"
                r4.append(r2)
                java.lang.String r2 = r5.f13321i
                r4.append(r2)
                java.lang.String r2 = r4.toString()
                java.lang.String r0 = r5.a(r2, r0)
                r1.a(r3, r0)
            Lc7:
                com.jingdong.manto.jsapi.base.e r0 = r5.d
                if (r0 == 0) goto Lf6
                com.jingdong.manto.m.e0 r0 = r5.f13316c
                com.jingdong.manto.q.n r0 = com.jingdong.manto.m.c0.getPageView(r0)
                com.jingdong.manto.jsapi.base.e r1 = r5.d
                com.jingdong.manto.m.d1.c.a(r0, r1)
                goto Lf6
            Ld7:
                com.jingdong.manto.m.e0 r1 = r5.f13316c
                com.jingdong.manto.m.k0 r1 = r1.d()
                android.os.Bundle r2 = r5.f13318f
                java.util.Map r2 = com.jingdong.manto.utils.MantoUtils.formatBundle(r2)
                com.jingdong.manto.utils.MantoUtils.mapToJson(r2)
                org.json.JSONObject r3 = new org.json.JSONObject
                r3.<init>(r2)
                java.lang.String r2 = r3.toString()
                int r3 = r5.f13322j
                java.lang.String r4 = r5.f13324l
                r1.a(r0, r2, r3, r4)
            Lf6:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.m.d1.a.C0554a.c():void");
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
