package com.jingdong.manto.o;

import android.app.Activity;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jingdong.manto.o.m;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.sdk.api.ICustomMenuInterface;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes16.dex */
public class b extends m {
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private String f13888c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f13889e;

    /* renamed from: f  reason: collision with root package name */
    private int f13890f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public static class a extends com.jingdong.manto.message.c {
        public static final Parcelable.Creator<a> CREATOR = new C0643a();

        /* renamed from: c  reason: collision with root package name */
        private String f13891c;
        private String d;

        /* renamed from: e  reason: collision with root package name */
        private String f13892e;

        /* renamed from: f  reason: collision with root package name */
        private int f13893f;

        /* renamed from: g  reason: collision with root package name */
        private String f13894g;

        /* renamed from: com.jingdong.manto.o.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes16.dex */
        class C0643a implements Parcelable.Creator<a> {
            C0643a() {
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

        a(Parcel parcel) {
            a(parcel);
        }

        a(String str, String str2, String str3, int i2, String str4) {
            this.f13891c = str;
            this.f13892e = str2;
            this.d = str3;
            this.f13893f = i2;
            this.f13894g = str4;
        }

        @Override // com.jingdong.manto.message.c
        public void a(Parcel parcel) {
            this.f13891c = parcel.readString();
            this.f13892e = parcel.readString();
            this.d = parcel.readString();
            this.f13893f = parcel.readInt();
            this.f13894g = parcel.readString();
        }

        @Override // com.jingdong.manto.message.c
        public void b() {
            ICustomMenuInterface.CustomData customData = new ICustomMenuInterface.CustomData();
            customData.appId = this.f13891c;
            customData.name = this.f13892e;
            customData.apptype = this.d;
            customData.jsonStr = this.f13894g;
            ICustomMenuInterface iCustomMenuInterface = (ICustomMenuInterface) com.jingdong.a.n(ICustomMenuInterface.class);
            if (iCustomMenuInterface == null) {
                return;
            }
            iCustomMenuInterface.onMenuClicked(com.jingdong.manto.c.a(), customData, this.f13893f, this.f13891c);
        }

        @Override // com.jingdong.manto.message.c, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.f13891c);
            parcel.writeString(this.f13892e);
            parcel.writeString(this.d);
            parcel.writeInt(this.f13893f);
            parcel.writeString(this.f13894g);
        }
    }

    public b(boolean z, int i2, String str, int i3, boolean z2, int i4) {
        super(i2 + 100);
        this.b = false;
        this.f13888c = "";
        this.d = -1;
        this.f13889e = false;
        this.b = z;
        this.f13888c = str;
        this.d = i3;
        this.f13889e = z2;
        this.f13890f = i4;
    }

    @Override // com.jingdong.manto.o.m
    public void a(Activity activity, com.jingdong.manto.q.n nVar, String str, n nVar2) {
        PkgDetailEntity pkgDetailEntity = nVar.h().f13016h;
        if (pkgDetailEntity == null) {
            return;
        }
        com.jingdong.manto.m.n0.a aVar = null;
        List<com.jingdong.manto.m.n0.a> m2 = nVar.m();
        if (m2 != null) {
            Iterator<com.jingdong.manto.m.n0.a> it = m2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                com.jingdong.manto.m.n0.a next = it.next();
                if (next.b + 100 == this.a) {
                    aVar = next;
                    break;
                }
            }
        }
        String str2 = aVar != null ? aVar.a : "{}";
        if (this.b) {
            new a(pkgDetailEntity.appId, pkgDetailEntity.name, pkgDetailEntity.type, this.d, str2).e();
            return;
        }
        ICustomMenuInterface.CustomData customData = new ICustomMenuInterface.CustomData();
        customData.appId = str;
        customData.name = pkgDetailEntity.name;
        customData.apptype = pkgDetailEntity.type;
        customData.jsonStr = str2;
        ICustomMenuInterface iCustomMenuInterface = (ICustomMenuInterface) com.jingdong.a.n(ICustomMenuInterface.class);
        if (iCustomMenuInterface == null) {
            return;
        }
        iCustomMenuInterface.onMenuClicked(activity, customData, this.d, pkgDetailEntity.appId);
    }

    @Override // com.jingdong.manto.o.m
    public void a(Context context, com.jingdong.manto.q.n nVar, com.jingdong.manto.widget.j.c cVar, String str, m.a aVar) {
        n nVar2 = nVar.p().get(this.a);
        if (nVar2 == null) {
            return;
        }
        if (TextUtils.isEmpty(this.f13888c)) {
            this.f13888c = "undefined";
        }
        List<com.jingdong.manto.m.n0.a> m2 = nVar.m();
        if (m2 != null) {
            Iterator<com.jingdong.manto.m.n0.a> it = m2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                com.jingdong.manto.m.n0.a next = it.next();
                if (next.b + 100 == this.a) {
                    this.f13889e = next.f13454c;
                    break;
                }
            }
        }
        nVar2.b = this.f13889e;
        cVar.a(nVar2.f13908c, this.f13888c, context.getResources().getDrawable(this.f13890f)).a(true);
    }
}
