package com.jingdong.manto.message;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import com.jingdong.manto.a;
import com.jingdong.manto.utils.MantoLog;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes15.dex */
public class MantoAcrossProcessMain extends Service {

    /* renamed from: e */
    private static MantoAcrossProcessMain f13855e;

    /* renamed from: f */
    static final Set<com.jingdong.manto.message.a> f13856f = Collections.newSetFromMap(new ConcurrentHashMap());
    private final HashMap<String, IBinder.DeathRecipient> a = new HashMap<>();
    private final b b = new b();

    /* renamed from: c */
    private final Handler f13857c;
    private final Messenger d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a extends Handler {
        a(MantoAcrossProcessMain mantoAcrossProcessMain, Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            MantoLog.d("MantoAcrossProcessMain", "runInMain");
            c a = com.jingdong.manto.message.b.a(message.getData());
            if (a != null) {
                a.b();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b extends a.AbstractBinderC0502a {

        /* loaded from: classes15.dex */
        class a implements IBinder.DeathRecipient {
            final /* synthetic */ IBinder a;
            final /* synthetic */ String b;

            a(IBinder iBinder, String str) {
                b.this = r1;
                this.a = iBinder;
                this.b = str;
            }

            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                this.a.unlinkToDeath((IBinder.DeathRecipient) MantoAcrossProcessMain.this.a.get(this.b), 0);
                MantoAcrossProcessMain.this.a.remove(this.b);
                MantoAcrossProcessMain.b(this.b);
                MantoLog.e("MantoAcrossProcessMain", String.format("Sub Process Died: %s", this.b));
            }
        }

        b() {
            MantoAcrossProcessMain.this = r1;
        }

        @Override // com.jingdong.manto.a
        public final void a(Bundle bundle) {
            MantoLog.d("MantoAcrossProcessMain", "sendSync");
            c a2 = com.jingdong.manto.message.b.a(bundle);
            if (a2 != null) {
                a2.b();
            }
        }

        @Override // com.jingdong.manto.a
        public final void a(IBinder iBinder, String str) {
            try {
                a aVar = new a(iBinder, str);
                iBinder.linkToDeath(aVar, 0);
                MantoAcrossProcessMain.this.a.put(str, aVar);
                MantoAcrossProcessMain.a(str);
            } catch (Exception e2) {
                MantoLog.e("MantoAcrossProcessMain", "setDeathRecipient: %s", e2);
            }
        }

        @Override // com.jingdong.manto.a
        public final void b(Bundle bundle) {
            MantoLog.d("MantoAcrossProcessMain", "sendAsync");
            Message obtain = Message.obtain();
            obtain.setData(bundle);
            try {
                MantoAcrossProcessMain.a().d.send(obtain);
            } catch (Throwable th) {
                MantoLog.e("MantoAcrossProcessMain", th.toString());
            }
        }
    }

    public MantoAcrossProcessMain() {
        a aVar = new a(this, Looper.getMainLooper());
        this.f13857c = aVar;
        this.d = new Messenger(aVar);
    }

    public static MantoAcrossProcessMain a() {
        if (f13855e == null) {
            synchronized (MantoAcrossProcessMain.class) {
                if (f13855e == null) {
                    f13855e = new MantoAcrossProcessMain();
                }
            }
        }
        return f13855e;
    }

    public static void a(com.jingdong.manto.message.a aVar) {
        f13856f.add(aVar);
    }

    static void a(String str) {
        Iterator<com.jingdong.manto.message.a> it = f13856f.iterator();
        while (it.hasNext()) {
            it.next().b(str);
        }
    }

    public static boolean a(c cVar) {
        MantoLog.d("MantoAcrossProcessMain", "sendBackToSub");
        if (cVar.a == null) {
            return false;
        }
        Message obtain = Message.obtain();
        obtain.setData(com.jingdong.manto.message.b.a(cVar, false));
        try {
            cVar.a.send(obtain);
            return true;
        } catch (Exception e2) {
            MantoLog.e("MantoAcrossProcessMain", "", e2);
            return false;
        }
    }

    static void b(String str) {
        Iterator<com.jingdong.manto.message.a> it = f13856f.iterator();
        while (it.hasNext()) {
            it.next().a(str);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        MantoLog.d("MantoAcrossProcessMain", "onBind");
        return this.b;
    }
}
