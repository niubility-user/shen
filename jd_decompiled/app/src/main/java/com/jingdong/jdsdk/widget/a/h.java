package com.jingdong.jdsdk.widget.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/* loaded from: classes14.dex */
final class h extends Handler {
    private volatile Queue<CharSequence> a;
    private volatile boolean b;

    /* renamed from: c  reason: collision with root package name */
    private final Toast f12954c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(Toast toast) {
        super(Looper.getMainLooper());
        this.f12954c = toast;
        this.a = new ArrayBlockingQueue(3);
    }

    private static int c(CharSequence charSequence) {
        return charSequence.length() > 20 ? 3500 : 2000;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(CharSequence charSequence) {
        if ((this.a.isEmpty() || !this.a.contains(charSequence)) && !this.a.offer(charSequence)) {
            this.a.poll();
            this.a.offer(charSequence);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        if (this.b) {
            this.b = false;
            sendEmptyMessage(3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d() {
        if (this.b) {
            return;
        }
        this.b = true;
        sendEmptyMessageDelayed(1, 300L);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        int i2 = message.what;
        if (i2 == 1) {
            CharSequence peek = this.a.peek();
            if (peek != null) {
                this.f12954c.setText(peek);
                this.f12954c.show();
                sendEmptyMessageDelayed(2, c(peek) + 300);
                return;
            }
            this.b = false;
        } else if (i2 != 2) {
            if (i2 != 3) {
                return;
            }
            this.b = false;
            this.a.clear();
            this.f12954c.cancel();
        } else {
            this.a.poll();
            if (!this.a.isEmpty()) {
                sendEmptyMessage(1);
            } else {
                this.b = false;
            }
        }
    }
}
