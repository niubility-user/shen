package com.jingdong.aura.wrapper;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.widget.Toast;
import com.jingdong.aura.R;
import com.jingdong.aura.a.c.l;
import com.jingdong.aura.core.nativelib.AuraNative;
import com.jingdong.aura.core.util.h;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import java.io.File;
import l.b.a.e;

/* loaded from: classes4.dex */
public class b implements e {

    /* renamed from: c  reason: collision with root package name */
    public static String f12261c = "";
    static HandlerC0401b d = new HandlerC0401b();

    /* renamed from: e  reason: collision with root package name */
    private static final com.jingdong.aura.core.util.l.b f12262e = com.jingdong.aura.core.util.l.c.a("SecBundleListener");
    private Handler a;
    private HandlerThread b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements Runnable {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(l.a, R.string.aura_bundlebroken, 0).show();
            com.jingdong.aura.a.b.e.a(this.a, "\u7ec4\u4ef6\u635f\u574f,\u8bf7\u91cd\u65b0\u5b89\u88c5", "ApkUtils.initHostKey_1", null);
            b.d.sendEmptyMessageDelayed(0, Final.FIVE_SECOND);
        }
    }

    /* renamed from: com.jingdong.aura.wrapper.b$b  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public static class HandlerC0401b extends Handler {
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Process.killProcess(Process.myPid());
        }
    }

    /* loaded from: classes4.dex */
    private final class c extends Handler {
        public c(b bVar, Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            File b;
            if (message != null) {
                b.f12262e.a("SecurityCheckHandler\uff1ahandleMessage\uff1a" + message.obj);
                String str = (String) message.obj;
                if (!TextUtils.isEmpty(str) && (b = com.jingdong.aura.a.b.a.b().b(str)) != null) {
                    b.a(b, str);
                }
                b.f12262e.a("SecurityCheckHandler\uff1ahandleMessage\uff1aend " + message.obj);
            }
        }
    }

    public b() {
        this.b = null;
        HandlerThread handlerThread = new HandlerThread("Check bundle security");
        this.b = handlerThread;
        handlerThread.start();
        this.a = new c(this, this.b.getLooper());
    }

    @Override // l.b.a.e
    public void bundleChanged(l.b.a.a aVar) {
        int type = aVar.getType();
        if (type == 1 || type == 8) {
            Message obtain = Message.obtain();
            obtain.obj = aVar.getBundle().b();
            this.a.sendMessage(obtain);
        }
    }

    public static synchronized void a(File file, String str) {
        synchronized (b.class) {
            if (!file.exists()) {
                f12262e.d("verityBundleSign error!! bundleFile not exits.bundleFile:" + file.getAbsolutePath() + " location:" + str);
                return;
            }
            com.jingdong.aura.core.util.l.b bVar = f12262e;
            bVar.a("verityBundleSign\uff1abundleFile:" + file.getAbsolutePath() + " location:" + str);
            if (com.jingdong.aura.a.b.a.b().c() != null) {
                if (!com.jingdong.aura.a.b.a.b().c().a(file.getAbsolutePath())) {
                    a(str);
                }
            } else if (!a(file) && !AuraNative.a(file.getAbsolutePath())) {
                com.jingdong.aura.a.b.e.a(str, "verityBundleSign", str, "verityBundleSign fail:" + file.getAbsolutePath() + " root state:" + com.jingdong.aura.wrapper.a.b(), "verityBundleSign", null);
                a(str);
            } else {
                bVar.a(str + " verityBundleSign pass!");
            }
        }
    }

    private static boolean a(File file) {
        return h.a(com.jingdong.aura.wrapper.a.a(file.getAbsolutePath()), f12261c);
    }

    private static void a(String str) {
        new Handler(Looper.getMainLooper()).post(new a(str));
    }
}
