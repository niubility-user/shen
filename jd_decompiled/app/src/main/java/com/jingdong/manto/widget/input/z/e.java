package com.jingdong.manto.widget.input.z;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Selection;

/* loaded from: classes16.dex */
public final class e {
    private final Handler a = new a(Looper.getMainLooper());
    public volatile g b;

    /* loaded from: classes16.dex */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (1000 == message.what) {
                String str = (String) message.obj;
                int i2 = message.arg1;
                if (e.this.b != null) {
                    e.this.b.a(str, i2);
                }
            }
        }
    }

    public final void a(CharSequence charSequence, boolean z) {
        if (charSequence != null) {
            Message obtainMessage = this.a.obtainMessage(1000, Selection.getSelectionEnd(charSequence), 0, charSequence.toString());
            this.a.removeMessages(1000);
            this.a.sendMessageDelayed(obtainMessage, z ? 150L : 0L);
        }
    }
}
