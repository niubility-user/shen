package com.jingdong.manto.k;

import com.jingdong.manto.message.MantoAcrossMessage;
import com.jingdong.manto.message.MantoAcrossMessageCenter;
import com.jingdong.manto.message.ProcessMessageManager;
import com.jingdong.manto.sdk.api.IDeepDarkManager;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoProcessUtil;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes15.dex */
public class a implements MantoAcrossMessage.Listener {

    /* renamed from: c */
    private static final String f13230c = "com.jingdong.manto.k.a";
    private static volatile a d;
    private int a;
    private CopyOnWriteArrayList<b> b = new CopyOnWriteArrayList<>();

    /* renamed from: com.jingdong.manto.k.a$a */
    /* loaded from: classes15.dex */
    public class C0535a implements IDeepDarkManager.IDeepDarkLisener {
        C0535a() {
            a.this = r1;
        }

        @Override // com.jingdong.manto.sdk.api.IDeepDarkManager.IDeepDarkLisener
        public void deepDarkModeChanged(int i2) {
            a.this.a = i2;
            com.jingdong.manto.k.b bVar = new com.jingdong.manto.k.b();
            bVar.a = i2;
            MantoAcrossMessageCenter.notifyCommonData(bVar);
            a.this.onCalled(bVar);
        }
    }

    /* loaded from: classes15.dex */
    public interface b {
        void onDeepModeChanged(int i2);
    }

    private a() {
    }

    public static a b() {
        if (d == null) {
            synchronized (a.class) {
                if (d == null) {
                    d = new a();
                }
            }
        }
        return d;
    }

    public int a() {
        return this.a;
    }

    public int a(int i2) {
        this.a = i2;
        return i2;
    }

    public void a(b bVar) {
        if (!this.b.contains(bVar)) {
            this.b.add(bVar);
        }
        bVar.onDeepModeChanged(this.a);
    }

    public void b(b bVar) {
        this.b.remove(bVar);
    }

    public void c() {
        if (!MantoProcessUtil.isMainProcess()) {
            ProcessMessageManager.getInstance().registListener(this);
            ProcessMessageManager.getInstance().sendMessageToMain(new com.jingdong.manto.k.b());
            return;
        }
        IDeepDarkManager iDeepDarkManager = (IDeepDarkManager) com.jingdong.a.n(IDeepDarkManager.class);
        if (iDeepDarkManager == null) {
            return;
        }
        iDeepDarkManager.registerDeepDarkListener(new C0535a());
    }

    @Override // com.jingdong.manto.message.MantoAcrossMessage.Listener
    public void onCalled(Object obj) {
        if (obj instanceof com.jingdong.manto.k.b) {
            this.a = ((com.jingdong.manto.k.b) obj).a;
            MantoLog.d(f13230c, "onCalled MantoDeepDarkMessage:" + this.a + ", " + this.b.size());
            Iterator<b> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().onDeepModeChanged(this.a);
            }
        }
    }
}
