package com.jingdong.manto.o;

import android.app.Activity;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.R;
import com.jingdong.manto.o.m;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.sdk.api.IHostMenuInterface;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoTrack;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class f extends m {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public static class a extends com.jingdong.manto.message.c {
        public static final Parcelable.Creator<a> CREATOR = new C0645a();

        /* renamed from: c  reason: collision with root package name */
        private int f13897c;
        private int d;

        /* renamed from: e  reason: collision with root package name */
        private int f13898e;

        /* renamed from: f  reason: collision with root package name */
        private int f13899f;

        /* renamed from: g  reason: collision with root package name */
        private int f13900g;

        /* renamed from: h  reason: collision with root package name */
        private com.jingdong.manto.widget.j.c f13901h;

        /* renamed from: i  reason: collision with root package name */
        private m.a f13902i;

        /* renamed from: com.jingdong.manto.o.f$a$a  reason: collision with other inner class name */
        /* loaded from: classes16.dex */
        class C0645a implements Parcelable.Creator<a> {
            C0645a() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a  reason: merged with bridge method [inline-methods] */
            public a createFromParcel(Parcel parcel) {
                return new a(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a  reason: merged with bridge method [inline-methods] */
            public a[] newArray(int i2) {
                return new a[i2];
            }
        }

        /* loaded from: classes16.dex */
        class b implements IHostMenuInterface.RedMsgCallBack {
            b() {
            }

            @Override // com.jingdong.manto.sdk.api.IHostMenuInterface.RedMsgCallBack
            public void onMsgRead(int i2, int i3) {
                a.this.f13900g = i2;
                a.this.f13899f = i3;
                a.this.g();
            }
        }

        a(int i2, int i3, int i4) {
            this.f13897c = i2;
            this.d = i3;
            this.f13898e = i4;
        }

        a(Parcel parcel) {
            a(parcel);
        }

        @Override // com.jingdong.manto.message.c
        public void a(Parcel parcel) {
            this.f13897c = parcel.readInt();
            this.d = parcel.readInt();
            this.f13898e = parcel.readInt();
            this.f13899f = parcel.readInt();
            this.f13900g = parcel.readInt();
        }

        @Override // com.jingdong.manto.message.c
        public void b() {
            IHostMenuInterface iHostMenuInterface = (IHostMenuInterface) com.jingdong.a.n(IHostMenuInterface.class);
            if (iHostMenuInterface == null) {
                return;
            }
            iHostMenuInterface.getRedMsg(com.jingdong.manto.c.a(), new b());
        }

        @Override // com.jingdong.manto.message.c
        public void c() {
            com.jingdong.manto.widget.j.c cVar = this.f13901h;
            if (cVar == null || this.f13902i == null) {
                return;
            }
            cVar.a(this.f13897c, this.d, this.f13898e, this.f13900g == 1, this.f13899f).a(true);
            this.f13902i.a();
        }

        @Override // com.jingdong.manto.message.c, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f13897c);
            parcel.writeInt(this.d);
            parcel.writeInt(this.f13898e);
            parcel.writeInt(this.f13899f);
            parcel.writeInt(this.f13900g);
        }
    }

    public f() {
        super(1);
    }

    @Override // com.jingdong.manto.o.m
    public void a(Activity activity, com.jingdong.manto.q.n nVar, String str, n nVar2) {
        com.jingdong.manto.f h2 = nVar.h();
        if (h2 == null || h2.f13016h == null) {
            return;
        }
        PkgDetailEntity pkgDetailEntity = nVar.h().f13016h;
        IHostMenuInterface iHostMenuInterface = (IHostMenuInterface) com.jingdong.a.n(IHostMenuInterface.class);
        if (iHostMenuInterface == null) {
            return;
        }
        iHostMenuInterface.jumpToMsgCenter(activity);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("vapp_type", pkgDetailEntity.type);
            jSONObject.put("venderId", pkgDetailEntity.venderId);
            jSONObject.put("url", nVar.r());
        } catch (JSONException e2) {
            MantoLog.e(DYConstants.DY_TRACK, e2);
        }
        MantoTrack.sendCommonDataWithExt(com.jingdong.manto.c.a(), "\u6d88\u606f", "applets_message", pkgDetailEntity.appId, "\u80f6\u56ca\u83dc\u5355\u5f39\u7a97", "", jSONObject.toString(), "applets_capsule", null);
    }

    @Override // com.jingdong.manto.o.m
    public void a(Context context, com.jingdong.manto.q.n nVar, com.jingdong.manto.widget.j.c cVar, String str, m.a aVar) {
        com.jingdong.manto.f h2;
        PkgDetailEntity pkgDetailEntity;
        n nVar2 = nVar.p().get(this.a);
        if (nVar2 == null || (h2 = nVar.h()) == null || (pkgDetailEntity = h2.f13016h) == null || TextUtils.isEmpty(pkgDetailEntity.venderId)) {
            return;
        }
        int i2 = nVar2.f13908c;
        int i3 = R.string.manto_page_menu_msg;
        int i4 = R.drawable.manto_menu_msg;
        cVar.a(i2, i3, i4).a(true);
        a aVar2 = new a(nVar2.f13908c, i3, i4);
        aVar2.f13901h = cVar;
        aVar2.f13902i = aVar;
        aVar2.d();
    }
}
