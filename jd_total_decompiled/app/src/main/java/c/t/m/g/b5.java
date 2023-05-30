package c.t.m.g;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.format.DateFormat;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class b5 {

    /* renamed from: e  reason: collision with root package name */
    public static b5 f317e;

    /* renamed from: f  reason: collision with root package name */
    public static boolean f318f;
    public final File a;
    public boolean b;

    /* renamed from: c  reason: collision with root package name */
    public Handler f319c;
    public HandlerThread d;

    /* loaded from: classes.dex */
    public class a implements Runnable {
    }

    /* loaded from: classes.dex */
    public final class b extends Handler {
        public File a;

        public b(Looper looper) {
            super(looper);
            this.a = a();
        }

        public /* synthetic */ b(b5 b5Var, Looper looper, a aVar) {
            this(looper);
        }

        public final File a() {
            File file = b5.this.a;
            if (!file.exists()) {
                file.mkdirs();
            }
            return new File(file, "dexlog");
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            File file = this.a;
            if (file == null || !"dexlog".equals(file.getName())) {
                this.a = a();
            }
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(this.a, true));
                bufferedOutputStream.write(message.obj.toString().getBytes("GBK"));
                bufferedOutputStream.flush();
            } catch (IOException unused) {
                this.a = null;
            }
        }
    }

    public b5(Context context, File file) {
        this.a = file;
        boolean z = file != null && (file.exists() || file.mkdirs());
        this.b = z;
        if (z) {
            HandlerThread handlerThread = new HandlerThread("log_worker", 10);
            this.d = handlerThread;
            handlerThread.start();
            this.f319c = new b(this, this.d.getLooper(), null);
        }
        if (f318f) {
            new StringBuilder("log dir=").append(file);
            if (this.b) {
                return;
            }
            new StringBuilder("init failed: mPrepared=").append(this.b);
        }
    }

    public static b5 a() {
        return f317e;
    }

    public static b5 b(Context context, File file) {
        if (f317e == null) {
            synchronized (b5.class) {
                if (f317e == null) {
                    f317e = new b5(context, file);
                }
            }
        }
        return f317e;
    }

    public void d(String str, int i2, String str2) {
        if (e()) {
            this.f319c.obtainMessage(1, DateFormat.format("yyyy-MM-dd kk:mm:ss", System.currentTimeMillis()) + ":" + str + ":" + str2 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).sendToTarget();
        }
    }

    public final boolean e() {
        return this.b && this.f319c != null;
    }
}
