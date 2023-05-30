package com.jingdong.manto.message;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.Toast;
import com.jingdong.manto.R;
import com.jingdong.manto.pkg.PkgManager;
import com.jingdong.manto.pkg.db.entity.PkgCollectEntity;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.pkg.ipc.MantoPkgUpdate;
import com.jingdong.manto.sdk.api.IDeepDarkManager;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoThreadUtils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/* loaded from: classes15.dex */
public class MantoAcrossMessage extends com.jingdong.manto.message.c {
    public static final Parcelable.Creator<MantoAcrossMessage> CREATOR = new a();

    /* renamed from: c  reason: collision with root package name */
    private final Set<Listener> f13851c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public int f13852e;

    /* renamed from: f  reason: collision with root package name */
    String f13853f;

    /* renamed from: g  reason: collision with root package name */
    Parcelable f13854g;

    /* loaded from: classes15.dex */
    public interface Listener {
        void onCalled(Object obj);
    }

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<MantoAcrossMessage> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final MantoAcrossMessage createFromParcel(Parcel parcel) {
            return new MantoAcrossMessage(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final MantoAcrossMessage[] newArray(int i2) {
            return new MantoAcrossMessage[i2];
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b implements PkgManager.PkgFavoCallBack {

        /* loaded from: classes15.dex */
        class a implements Runnable {
            a(b bVar) {
            }

            @Override // java.lang.Runnable
            public void run() {
                Context a = com.jingdong.manto.c.a();
                Toast.makeText(a, a.getString(R.string.manto_favo_succ), 0).show();
            }
        }

        b() {
        }

        @Override // com.jingdong.manto.pkg.PkgManager.PkgFavoCallBack
        public void onError(Throwable th) {
            MantoLog.e("MantoAcrossMessage", "handlePkgFavoInMain favoPkg failed " + th.toString());
        }

        @Override // com.jingdong.manto.pkg.PkgManager.PkgFavoCallBack
        public void onSuccess() {
            MantoThreadUtils.runOnUIThread(new a(this));
            MantoLog.d("MantoAcrossMessage", "handlePkgFavoInMain favoPkg success");
            MantoAcrossMessageCenter.notifyMainListeners(MantoAcrossMessage.this.f13854g);
            MantoAcrossMessage.this.g();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class c implements PkgManager.PkgFavoCallBack {

        /* loaded from: classes15.dex */
        class a implements Runnable {
            a(c cVar) {
            }

            @Override // java.lang.Runnable
            public void run() {
                Context a = com.jingdong.manto.c.a();
                Toast.makeText(a, a.getString(R.string.manto_unfavo_succ), 0).show();
            }
        }

        c() {
        }

        @Override // com.jingdong.manto.pkg.PkgManager.PkgFavoCallBack
        public void onError(Throwable th) {
            MantoLog.e("MantoAcrossMessage", "handlePkgFavoInMain unFavoPkg failed " + th.toString());
        }

        @Override // com.jingdong.manto.pkg.PkgManager.PkgFavoCallBack
        public void onSuccess() {
            MantoThreadUtils.runOnUIThread(new a(this));
            MantoLog.d("MantoAcrossMessage", "handlePkgFavoInMain unFavoPkg success");
            MantoAcrossMessageCenter.notifyMainListeners(MantoAcrossMessage.this.f13854g);
            MantoAcrossMessage.this.g();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class d implements Runnable {
        final /* synthetic */ List a;
        final /* synthetic */ Object b;

        d(MantoAcrossMessage mantoAcrossMessage, List list, Object obj) {
            this.a = list;
            this.b = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((Listener) it.next()).onCalled(this.b);
            }
        }
    }

    public MantoAcrossMessage() {
        this.f13851c = new HashSet();
    }

    private MantoAcrossMessage(Parcel parcel) {
        this.f13851c = new HashSet();
        a(parcel);
    }

    /* synthetic */ MantoAcrossMessage(Parcel parcel, a aVar) {
        this(parcel);
    }

    private void a(com.jingdong.manto.k.b bVar) {
        IDeepDarkManager iDeepDarkManager = (IDeepDarkManager) com.jingdong.a.n(IDeepDarkManager.class);
        if (iDeepDarkManager != null) {
            int curreentDeepDarkMode = iDeepDarkManager.getCurreentDeepDarkMode();
            com.jingdong.manto.k.a.b().a(curreentDeepDarkMode);
            bVar.a = curreentDeepDarkMode;
            g();
        }
    }

    private void a(Object obj) {
        if (obj != null) {
            LinkedList linkedList = new LinkedList();
            synchronized (this.f13851c) {
                Iterator<Listener> it = this.f13851c.iterator();
                while (it.hasNext()) {
                    linkedList.add(it.next());
                }
            }
            com.jingdong.manto.sdk.thread.a.b(new d(this, linkedList, obj));
        }
    }

    private void h() {
        MantoPkgUpdate mantoPkgUpdate = (MantoPkgUpdate) this.f13854g;
        MantoPkgUpdate.UpdateAction updateAction = mantoPkgUpdate.action;
        if (updateAction == MantoPkgUpdate.UpdateAction.FAVO) {
            PkgDetailEntity pkgDetailEntity = mantoPkgUpdate.detailEntity;
            PkgManager.favoPkg(new PkgCollectEntity(pkgDetailEntity.appId, pkgDetailEntity.type, pkgDetailEntity.name, pkgDetailEntity.logo, pkgDetailEntity.favorite, System.currentTimeMillis()), new b());
        } else if (updateAction == MantoPkgUpdate.UpdateAction.UNFAVO) {
            PkgDetailEntity pkgDetailEntity2 = mantoPkgUpdate.detailEntity;
            PkgManager.unFavoPkg(new PkgCollectEntity(pkgDetailEntity2.appId, pkgDetailEntity2.type, pkgDetailEntity2.name, pkgDetailEntity2.logo, pkgDetailEntity2.favorite, System.currentTimeMillis()), new c());
        }
    }

    @Override // com.jingdong.manto.message.c
    public final void a(Parcel parcel) {
        Class<?> cls;
        this.f13852e = parcel.readInt();
        this.d = parcel.readString();
        if (parcel.readInt() == 1) {
            try {
                String readString = parcel.readString();
                this.f13853f = readString;
                if (TextUtils.isEmpty(readString) || (cls = Class.forName(this.f13853f)) == null) {
                    return;
                }
                this.f13854g = parcel.readParcelable(cls.getClassLoader());
            } catch (Exception e2) {
                MantoLog.v("MantoAcrossMessage", "getFromParcel :", e2);
            }
        }
    }

    public void a(Parcelable parcelable) {
        MantoLog.d("MantoAcrossMessage", "sendToMain, Code " + hashCode() + " appId " + this.d);
        this.f13852e = 6;
        this.f13853f = parcelable.getClass().getName();
        this.f13854g = parcelable;
        d();
    }

    public void a(Listener listener) {
        synchronized (this.f13851c) {
            this.f13851c.add(listener);
            MantoLog.d("MantoAcrossMessage", "registListener size " + this.f13851c.size() + ", Code " + hashCode() + ", listener " + listener.hashCode() + ", appId " + this.d);
        }
    }

    public void a(String str) {
        MantoLog.d("MantoAcrossMessage", "registAndPostTask, Code " + hashCode() + " appId " + str);
        this.f13852e = 1;
        this.d = str;
        d();
    }

    @Override // com.jingdong.manto.message.c
    public final void b() {
        MantoLog.d("MantoAcrossMessage", "runInMain, actionType " + this.f13852e + ", Code " + hashCode() + ", parcelableObject " + this.f13854g + " appId " + this.d);
        int i2 = this.f13852e;
        if (i2 == 1) {
            MantoAcrossMessageCenter.register(this);
        } else if (i2 == 2) {
            MantoAcrossMessageCenter.unregister(this);
        } else if (i2 != 6) {
        } else {
            Parcelable parcelable = this.f13854g;
            if (parcelable instanceof MantoPkgUpdate) {
                h();
            } else if (parcelable instanceof com.jingdong.manto.k.b) {
                a((com.jingdong.manto.k.b) parcelable);
            } else {
                MantoAcrossMessageCenter.notifyMainListeners(parcelable);
            }
        }
    }

    public void b(Listener listener) {
        if (listener != null) {
            synchronized (this.f13851c) {
                this.f13851c.remove(listener);
                MantoLog.d("MantoAcrossMessage", "unRegistListener size " + this.f13851c.size() + ", Code " + hashCode() + ", listener " + listener.hashCode() + ", appId " + this.d);
            }
        }
    }

    public void b(String str) {
        MantoLog.d("MantoAcrossMessage", "unRegistAndPostTask, Code " + hashCode() + " appId " + str);
        this.f13852e = 2;
        this.d = str;
        d();
    }

    @Override // com.jingdong.manto.message.c
    public final void c() {
        MantoLog.d("MantoAcrossMessage", "runInSub, actionType " + this.f13852e + ", Code " + hashCode() + ", parcelableObject " + this.f13854g + ", appId " + this.d);
        int i2 = this.f13852e;
        if (i2 == 5) {
            a((Object) this.f13854g);
        } else if (i2 != 6) {
        } else {
            Parcelable parcelable = this.f13854g;
            if (parcelable instanceof MantoPkgUpdate) {
                a((Object) parcelable);
            } else if (parcelable instanceof com.jingdong.manto.k.b) {
                com.jingdong.manto.k.a.b().a(((com.jingdong.manto.k.b) this.f13854g).a);
            }
        }
    }

    @Override // com.jingdong.manto.message.c, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f13852e);
        parcel.writeString(this.d);
        if (TextUtils.isEmpty(this.f13853f) || this.f13854g == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcel.writeString(this.f13853f);
        parcel.writeParcelable(this.f13854g, i2);
    }
}
